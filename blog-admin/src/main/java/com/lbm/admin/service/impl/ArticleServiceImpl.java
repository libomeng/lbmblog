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
 *  服务实现类
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
       articleParam.setIsPublish(articleParam.getIsPublish()==null? 0:articleParam.getIsPublish());
        if(ObjectUtil.checkObjFieldIsNull(articleParam)){
            return Result.fail("请正确填写文章信息");
        }
        String categoryName = articleParam.getCategory();
        if(StringUtils.isEmpty(categoryName)){
            return Result.fail("文章分类不能为空");
        }
        LambdaQueryWrapper<Category> categoryQueryWrapper = new LambdaQueryWrapper<>();
        categoryQueryWrapper.eq(Category::getCategoryName,categoryName);
        Category category = categoryService.getOne(categoryQueryWrapper);
        if(category == null){
            category =new Category();
            category.setAvatar("/static/category/front.png");
            category.setCategoryName(categoryName);
            category.setCategoryName(categoryName);
            boolean saveCate = categoryService.save(category);
            if(!saveCate){
                return Result.fail("创建文章分类失败");
            }
        }
        List<String> tags = articleParam.getTags();
        if(tags.isEmpty()){
            return Result.fail("文章标签不能为空");
        }
        List<String> tagIdList = new ArrayList<>();
        for (String tag : tags) {
            LambdaQueryWrapper<Tag> tagLambdaQueryWrapper =new LambdaQueryWrapper<>();
            tagLambdaQueryWrapper.eq(Tag::getTagName,tag);
            Tag tagRes = tagService.getOne(tagLambdaQueryWrapper);
            if(tagRes == null){
                tagRes= new Tag();
                tagRes.setTagName(tag);
                tagRes.setAvatar("/static/tag/css.png");
                boolean tagSave = tagService.save(tagRes);
                if(!tagSave){
                    return Result.fail("标签添加失败");
                }
                tagIdList.add(tagRes.getId());
            }
        }
        Article article =new Article();
        articleMapper.insert(article);
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getValue());
        articleBody.setContentHtml(articleParam.getHtml());
        articleBody.setWord(articleParam.getWords());
        articleBody.setRedingTime(articleParam.getReadTime());
        boolean bodySave = articleBodyService.save(articleBody);
        if(!bodySave){
            return Result.fail("文章内容添加失败");
        }
        article.setBodyId(articleBody.getId());
        article.setCategoryId(category.getId());
        article.setIsComment(1);
        article.setIsPublish(articleParam.getIsPublish());
        article.setIsWeight(0);
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        Integer ins = articleMapper.updateById(article);
        if(ins!=1){
            return Result.fail("文章添加失败");
        }
        for (String tagId : tagIdList) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(tagId);
            boolean save = this.articleTagService.save(articleTag);
            if(!save){
                return Result.fail("标签添加失败");
            }
        }
        return Result.success("文章添加成功");
    }

}
