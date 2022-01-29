package com.lbm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.*;
import com.lbm.admin.entity.params.CreateArticleParam;
import com.lbm.admin.entity.vo.ArticleListVo;
import com.lbm.admin.mapper.ArticleMapper;
import com.lbm.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.common.Result;
import com.lbm.common.uitl.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    ArticleBodyService articleBodyService;
    @Autowired
    ArticleTagService articleTagService;

    @Override
    public Page<ArticleListVo> pageArticleVo(Page<ArticleListVo> articlePage) {
        Page<ArticleListVo> page = articleMapper.pageArticleVo(articlePage);
        return page;
    }


    @Override
    @Transactional
    public Result createArticle(CreateArticleParam articleParam) {
        //如果传回参数没有ID值，先赋予一个固定ID，用于通过参数检查
        articleParam.setId(StringUtils.isEmpty(articleParam.getId()) ? "11" : articleParam.getId());
        //请求参数没有发布值时默认赋予0
        articleParam.setIsPublish(articleParam.getIsPublish() == null ? 0 : articleParam.getIsPublish());
        if (ObjectUtil.checkObjFieldIsNull(articleParam)) {
            return Result.fail("请正确填写文章信息");
        }
        String categoryName = articleParam.getCategory();
        if (StringUtils.isEmpty(categoryName)) {
            return Result.fail("文章分类不能为空");
        }
        /**
         * 判断文章分类是否存在； 存在=无操作 不存在=创建
         */
        LambdaQueryWrapper<Category> categoryQueryWrapper = new LambdaQueryWrapper<>();
        categoryQueryWrapper.eq(Category::getCategoryName, categoryName);
        Category category = categoryService.getOne(categoryQueryWrapper);
        if (category == null) {
            category = new Category();
            category.setAvatar("/static/category/front.png");
            category.setCategoryName(categoryName);
            boolean saveCate = categoryService.save(category);
            if (!saveCate) {
                return Result.fail("创建文章分类失败");
            }
        }
        List<String> tags = articleParam.getTags();
        if (tags.isEmpty()) {
            return Result.fail("文章标签不能为空");
        }
        /**
         * 判断文章标签是否存在；并将标签名转化为标签id; 存在=无操作 不存在=创建
         */
        List<String> tagIdList = new ArrayList<>();
        for (String tag : tags) {
            LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tagLambdaQueryWrapper.eq(Tag::getTagName, tag);
            Tag tagRes = tagService.getOne(tagLambdaQueryWrapper);
            if (tagRes == null) {
                tagRes = new Tag();
                tagRes.setTagName(tag);
                tagRes.setAvatar("/static/tag/css.png");
                boolean tagSave = tagService.save(tagRes);
                if (!tagSave) {
                    return Result.fail("标签添加失败");
                }
            }
            tagIdList.add(tagRes.getId());
        }
        /**
         * 判断请求是需要新建文章还是修改文章
         */
        Article article = new Article();
        ArticleBody articleBody = new ArticleBody();
        if (articleParam.getId().equals("11")) {  //新建文章请求(请求参数为临时ID)
            articleMapper.insert(article);
            String articleBodyId = articleBodyService.createArticleBody(articleParam, article.getId());
            if(StringUtils.isEmpty(articleBodyId)){
                return Result.fail("文章体添加失败");
            }
            Boolean res = articleTagService.addMap(tagIdList, article.getId());
            if(!res){
                return Result.fail("文章标签添加失败");
            }
            article.setIsComment(1);
            article.setIsWeight(0);
        } else {    //修改文章请求
            article.setId(articleParam.getId()); //将id参数保存到实体类
            Boolean res = articleBodyService.updateArticleBody(articleParam); //更新文章体内容
            if(!res){
                return Result.fail("文章体修改失败");
            }
            Boolean res2 = articleTagService.newArticleTagHandler(tagIdList,article.getId()); //更新文章与标签映射表
            if(!res2){
                return Result.fail("文章标签添加失败");
            }
        }
        article.setBodyId(articleBody.getId());
        article.setCategoryId(category.getId());
        article.setImg(articleParam.getImg());
        article.setIsPublish(articleParam.getIsPublish());
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        int ins = articleMapper.updateById(article);
        if (ins != 1) {
            return Result.fail("文章添加失败");
        }
        return Result.success("文章添加成功");
    }

    @Override
    public Result getArticleInfo(String id) {
        Article article = articleMapper.selectById(id);
        ArticleBody body = articleBodyService.getById(article.getBodyId());
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, id);
        articleTagLambdaQueryWrapper.select(ArticleTag::getTagId);
        List<ArticleTag> articleTagList = articleTagService.list(articleTagLambdaQueryWrapper);
        List<String> tags = new ArrayList<>();
        articleTagList.forEach(articleTag -> {
            String tagId = articleTag.getTagId();
            Tag tag = tagService.getById(tagId);
            tags.add(tag.getTagName());
        });
        Category category = categoryService.getById(article.getCategoryId());
        CreateArticleParam articleParam = new CreateArticleParam();
        articleParam.setId(id);
        articleParam.setSummary(article.getSummary());
        articleParam.setCategory(category.getCategoryName());
        articleParam.setTitle(article.getTitle());
        articleParam.setImg(article.getImg());
        articleParam.setValue(body.getContent());
        articleParam.setTags(tags);
        articleParam.setWords(body.getWord());
        articleParam.setReadTime(body.getRedingTime());
        return Result.success(articleParam);
    }

}
