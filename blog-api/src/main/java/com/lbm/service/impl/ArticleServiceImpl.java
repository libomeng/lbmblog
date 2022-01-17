package com.lbm.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.lbm.common.cache.Cache;
import com.lbm.config.RedisKeyConfig;
import com.lbm.dao.dos.Archives;
import com.lbm.dao.entity.Article;
import com.lbm.dao.entity.ArticleBody;
import com.lbm.dao.entity.ArticleTag;
import com.lbm.dao.entity.SysUser;
import com.lbm.dao.mapper.ArticleBodyMapper;
import com.lbm.dao.mapper.ArticleMapper;
import com.lbm.dao.mapper.ArticleTagMapper;
import com.lbm.service.*;
import com.lbm.util.JacksonUtil;
import com.lbm.util.UserThreadLocal;
import com.lbm.vo.*;
import com.lbm.vo.params.ArticleParam;
import com.lbm.vo.params.ArticleSimpleParam;
import com.lbm.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

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


    /**
     * 项目启动时，获取所有文章阅读量写入Redis
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
        //从Redis中获取数据
        String key = RedisKeyConfig.ARTICLE_VO_LIST;
        List articleVoList = redisService.getListByKey(key, pageParams);
        if (articleVoList != null) {
            //更新首页文章的阅读数
            articleVoList = this.updateArticleView(articleVoList);
            return articleVoList;
        }
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        IPage<Article> iPage = articleMapper.listArticle(page, pageParams.getTagId(), pageParams.getCategoryId(), pageParams.getYear(), pageParams.getMonth());
        List<Article> records = iPage.getRecords();
        articleVoList = copyList(records);
        redisService.setListByKey(key, pageParams, articleVoList);
        return articleVoList;
    }

    //更新首页文章的阅读数
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
        //从redis中获取数据
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
        //从redis中获取数据
        String key = RedisKeyConfig.LIST_NEW_ARTICLES;
        List<ArticleVo> entity = redisService.getEntity(key, limit);
        if( entity != null){
            return Result.success(entity);
        }
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId,Article::getTitle,Article::getCreateDate);
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        redisService.setEntity(key,limit,articles);
        return Result.success(articles);
    }

    @Override
    public List<Archives> getArchivesCreateTimeList() {
        List<Archives> archives = articleMapper.getArchives();
        return archives;
    }

    @Override
    public ArticleVo findArticlesById(Long id) {
        //如果Redis中有数据，从Redis中获取
        String key = RedisKeyConfig.ARTICLE_VO;
        ArticleVo articleVoFromRedis = redisService.getEntity(key, id, ArticleVo.class);
        if (articleVoFromRedis != null) {
            Integer articleView = redisService.getArticleView(id);
            articleVoFromRedis.setViewCounts(articleView);
            //增加阅读数
            threadPoolService.updateArticleViewCount(id);
            return articleVoFromRedis;
        }
        Article article = articleMapper.selectById(id);
        ArticleVo articleVo = copy(article, true, true, true, true);
        redisService.setEntity(key, id, articleVo);
        //增加阅读数
        threadPoolService.updateArticleViewCount(id);
        return articleVo;
    }


    @Transactional
    @Override
    public Long createArticle(ArticleParam articleParam) {
        Article article = new Article();

        //先插入空数据，生成文章ID
        articleMapper.insert(article);
        Long articleId = article.getId();

        //给文章对象写入数据
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setViewCounts(0);
        article.setAuthorId(UserThreadLocal.get().getId());
        article.setBodyId(this.createArticleBody(articleParam.getBody(), articleId));
        article.setCategoryId(articleParam.getCategory().getId());
        article.setWeight(Article.Article_Common);
        article.setCreateDate(System.currentTimeMillis());
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, articleId);
        articleMapper.update(article, updateWrapper);

        //写入文章与标签关联表信息
        for (TagVo tag : articleParam.getTags()) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tag.getId());
            articleTag.setArticleId(articleId);
            articleTagMapper.insert(articleTag);
        }
        //删除最新文章缓存
        redisService.removeMap(RedisKeyConfig.LIST_NEW_ARTICLES);
        //删除文章浏览量缓存
        redisService.removeMap(RedisKeyConfig.ARTICLE_VIEW_MAP);
        this.saveArticleViewToRedis();
        return articleId;
    }

    @Override
    public Result getSimpleList(ArticleSimpleParam param) {
      List<Article>  articleList= articleMapper.getSimpleList(param.getTagId(),param.getCategoryId());
        List<ArticleSimpleVo> articleSimpleVoList = ArticleSimpleVo.copyList(articleList);
        return Result.success(articleSimpleVoList);
    }

    private Long createArticleBody(ArticleBodyParam body, Long articleId) {
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(body.getContent());
        articleBody.setContentHtml(body.getContentHtml());
        articleBody.setArticleId(articleId);
        articleBodyMapper.insert(articleBody);
        Long articleBodyId = articleBody.getId();

        return articleBodyId;

    }

    /**
     * 将文章集合转换为视图文章集合
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
     * 将Article转化为 ArticleVo
     *
     * @param article
     * @param isTag    是否添加标签
     * @param isAuthor 是否添加作者
     * @param isAuthor 是否添加文章体
     * @param isAuthor 是否添加文章分类
     * @return
     */
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
        //判断文章是否需要标签
        if (isTag) {
            Long articleId = article.getId();
            List<TagVo> tagVos = tagService.selectByTagId(articleId);
            articleVo.setTags(tagVos);
        }
        //判断文章是否需要作者标签
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            if (authorId == null) {
                authorId = 1L;
            }
            SysUser sysUser = sysUserService.findOneUser(authorId);
            articleVo.setAuthor(sysUser.getNickname());
        }
        if (isBody) {
            ArticleBody articleBody = findArticleBody(article.getBodyId());
            ArticleBodyVo articleBodyVo = new ArticleBodyVo();
            articleBodyVo.setContent(articleBody.getContent());
            articleVo.setBody(articleBodyVo);
        }
        if (isCategory) {
            Long categoryId = article.getCategoryId();
            CategoryVo categoryVo = categoryService.findCategoryById(categoryId);
            articleVo.setCategory(categoryVo);

        }
        return articleVo;
    }

    /**
     * 根据文章ID查询文章内容
     *
     * @param bodyId
     * @return
     */
    private ArticleBody findArticleBody(Long bodyId) {
        //从Redis获取数据
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
