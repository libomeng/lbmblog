package com.lbm.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.common.config.RedisKeyConfig;
import com.lbm.api.dao.dos.Archives;
import com.lbm.api.dao.entity.Article;
import com.lbm.api.dao.entity.ArticleBody;
import com.lbm.api.dao.entity.ArticleTag;
import com.lbm.api.dao.entity.SysUser;
import com.lbm.api.dao.mapper.ArticleBodyMapper;
import com.lbm.api.dao.mapper.ArticleMapper;
import com.lbm.api.dao.mapper.ArticleTagMapper;
import com.lbm.api.service.*;
import com.lbm.api.util.JacksonUtil;
import com.lbm.api.util.UserThreadLocal;
import com.lbm.api.vo.*;
import com.lbm.api.vo.params.ArticleParam;
import com.lbm.api.vo.params.ArticleSimpleParam;
import com.lbm.api.vo.params.PageParams;
import com.qiniu.util.Json;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagService tagService;
    @Autowired
    SysUserService sysUserService;

    @Autowired
    ArticleBodyMapper articleBodyMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ThreadPoolService threadPoolService;

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisTemplate jsonRedisTemplate;
    /**
     * ???????????????????????????????????????????????????Redis
     */
    @PostConstruct
    public void saveArticleViewToRedis() {
        String redisKey = RedisKeyConfig.ARTICLE_VIEW_MAP;
        if (redisService.isEmptyMap(redisKey)) {
            Map<String, Integer> map = this.getArticleViewMap();
            redisService.setMapByHash(redisKey, map);
        }

    }

    private Map<String, Integer> getArticleViewMap() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId, Article::getViewCounts);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        Map<String, Integer> map = new HashMap<>();
        for (Article article : articles) {
            map.put(article.getId().toString(), article.getViewCounts());
        }
        return map;


    }

    @Override
    public List<ArticleVo> findArticles(PageParams pageParams) {
        //???Redis???????????????
        String key = RedisKeyConfig.ARTICLE_VO_LIST;
        List articleVoList = redisService.getListByKey(key, pageParams);
        if (articleVoList != null) {
            //??????????????????????????????
            articleVoList = this.updateArticleView(articleVoList);
            return articleVoList;
        }
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        IPage<Article> iPage = articleMapper.listArticle(page, pageParams.getTagId(), pageParams.getCategoryId(), pageParams.getYear(), pageParams.getMonth());
        List<Article> articleList = iPage.getRecords();
        articleVoList = copyList(articleList);
        redisService.setListByKey(key, pageParams, articleVoList);
        jsonRedisTemplate.expire(key,1,TimeUnit.MINUTES);
        return articleVoList;
    }

    //??????????????????????????????
    private List<ArticleVo> updateArticleView(List articleVoList) {
        for (int i= 0;i < articleVoList.size();i++){
            ArticleVo articleVo = JacksonUtil.convertValue(articleVoList.get(i), ArticleVo.class);
            Integer articleView = redisService.getArticleView(articleVo.getId());
            articleVo.setViewCounts(articleView);
            articleVoList.set(i,articleVo);
        }
        return articleVoList;
    }

    @Override
    public List<HotArticleVo> listHotArticles(Integer limit) {
        //???redis???????????????
        String key = RedisKeyConfig.LIST_HOT_ARTICLES;
        List<HotArticleVo> entity = redisService.getEntity(key, limit);
        if( entity != null){
            return entity;
        }
        List<Article> articles = articleMapper.selectHotArticle(limit);
        List<HotArticleVo> hotArticleVos = copyToHotArticleVoList(articles);
        redisService.setEntity(key,limit,hotArticleVos);
        return hotArticleVos;
    }


    @Override
    public Result listNewArticles(int limit) {
        //???redis???????????????
        String key = RedisKeyConfig.LIST_NEW_ARTICLES;
        List<ArticleVo> entity = redisService.getEntity(key, limit);
        if( entity != null){
            return Result.success(entity);
        }
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId,Article::getTitle,Article::getGmtCreate);
        queryWrapper.orderByDesc(Article::getGmtCreate);
        queryWrapper.last("limit " + limit);
        //?????????vo??????
        List<Article> articles = articleMapper.selectList(queryWrapper);
        List<NewArticleVo> newArticleVos =new ArrayList<>();
        articles.forEach(article -> {
            newArticleVos.add(new NewArticleVo(article));
        });
        redisService.setEntity(key,limit,newArticleVos);
        return Result.success(newArticleVos);
    }

    @Override
    public List<Archives> getArchivesCreateTimeList() {
        List<Archives> archives = articleMapper.getArchives();
        return archives;
    }
    @Override
    public ArticleVo findArticlesById(String id) {
        //??????Redis??????????????????Redis?????????
        String key = RedisKeyConfig.ARTICLE_VO;
        ArticleVo articleVoFromRedis = redisService.getEntity(key, id, ArticleVo.class);
        if (articleVoFromRedis != null) {
            Integer articleView = redisService.getArticleView(id);
            articleVoFromRedis.setViewCounts(articleView);
            //???????????????
            threadPoolService.updateArticleViewCount(id);
            return articleVoFromRedis;
        }
        Article article = articleMapper.selectById(id);
        ArticleVo articleVo = copy(article, true, true, true, true);
        redisService.setEntity(key, id, articleVo);
        //???????????????
        threadPoolService.updateArticleViewCount(id);
        return articleVo;
    }
    @Transactional
    @Override
    public String createArticle(ArticleParam articleParam) {
        Article article = new Article();
        //?????????????????????????????????ID
        articleMapper.insert(article);
        String articleId = article.getId();
        //???????????????????????????
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setViewCounts(0);
        article.setAuthorId(UserThreadLocal.get().getId());
        article.setBodyId(this.createArticleBody(articleParam.getBody(), articleId));
        article.setCategoryId(articleParam.getCategory().getId());
        article.setIsWeight(0);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, articleId);
        articleMapper.update(article, updateWrapper);
        //????????????????????????????????????
        for (TagVo tag : articleParam.getTags()) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tag.getId());
            articleTag.setArticleId(articleId);
            articleTagMapper.insert(articleTag);
        }
        //????????????????????????
        redisService.removeMap(RedisKeyConfig.LIST_NEW_ARTICLES);
        //???????????????????????????
        redisService.removeMap(RedisKeyConfig.ARTICLE_VIEW_MAP);
        //??????????????????
        redisService.removeMap(RedisKeyConfig.ARTICLE_VO_LIST);
        this.saveArticleViewToRedis();
        return articleId;
    }

    @Override
    public Result getSimpleList(ArticleSimpleParam param) {
      List<Article>  articleList= articleMapper.getSimpleList(param.getTagId(),param.getCategoryId());
        List<ArticleSimpleVo> articleSimpleVoList = ArticleSimpleVo.copyList(articleList);
        return Result.success(articleSimpleVoList);
    }

    /**
     * ????????????????????????????????????remove?????????
     * @return ????????????
     */
    @Override
    public Integer articleCount() {
        Integer count =  articleMapper.articleCount();
        return count;
    }

    private String createArticleBody(ArticleBodyParam body, String articleId) {
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(body.getContent());
        articleBody.setContentHtml(body.getContentHtml());
        articleBody.setArticleId(articleId);
        articleBodyMapper.insert(articleBody);
        String articleBodyId = articleBody.getId();
        return articleBodyId;

    }

    /**
     * ??????????????????????????????????????????
     *
     * @param articles
     * @return
     */
    private List<ArticleVo> copyList(List<Article> articles) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articles) {
            ArticleVo articleVo = copy(article, true, true, true, true);
            articleVoList.add(articleVo);
        }
        return articleVoList;
    }

    /**
     * ???Article????????? ArticleVo
     *
     * @param article
     * @param isTag    ??????????????????
     * @param isAuthor ??????????????????
     * @param isAuthor ?????????????????????
     * @param isAuthor ????????????????????????
     * @return
     */
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setGmtCreate(new DateTime(article.getGmtCreate()).toDate());
        //??????????????????????????????
        if (isTag) {
            String articleId = article.getId();
            List<TagVo> tagVos = tagService.selectByTagId(articleId);
            articleVo.setTags(tagVos);
        }
        //????????????????????????????????????
        if (isAuthor) {
            String authorId = article.getAuthorId();
            if (authorId == null) {
                authorId = "1";
            }
            SysUser sysUser = sysUserService.findOneUser(authorId);
            articleVo.setAuthor(sysUser.getNickname());
        }
        if (isBody) {
            ArticleBody articleBody = findArticleBody(article.getBodyId());
            ArticleBodyVo articleBodyVo = new ArticleBodyVo();
            articleBodyVo.setContent(articleBody.getContent());
            articleBodyVo.setHtml((articleBody.getContentHtml()));
            articleVo.setBody(articleBodyVo);
        }
        if (isCategory) {
            String categoryId = article.getCategoryId();
            CategoryVo categoryVo = categoryService.findCategoryById(categoryId);
            articleVo.setCategory(categoryVo);

        }
        return articleVo;
    }

    /**
     * ????????????ID??????????????????
     *
     * @param bodyId
     * @return
     */
    private ArticleBody findArticleBody(String bodyId) {
        //???Redis????????????
        String key = RedisKeyConfig.ARTICLE_BODY;
        ArticleBody entity = redisService.getEntity(key, bodyId, ArticleBody.class);
        if(entity != null){
            return entity;
        }
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        redisService.setEntity(key,articleBody.getId(), articleBody);
        return articleBody;


    }


    private List<HotArticleVo> copyToHotArticleVoList(List<Article> articles) {
        List<HotArticleVo> hotArticleVos = new ArrayList<>();
        for (Article article : articles) {
            hotArticleVos.add(copyToHotArticleVo(article));
        }
        return hotArticleVos;
    }

    private HotArticleVo copyToHotArticleVo(Article article) {
        HotArticleVo hotArticleVo = new HotArticleVo();
        BeanUtils.copyProperties(article, hotArticleVo);
        return hotArticleVo;
    }


}
