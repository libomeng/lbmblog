package com.lbm.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lbm.api.dao.entity.Article;
import com.lbm.api.service.*;
import com.lbm.common.config.RedisKeyConfig;
import com.lbm.api.dao.entity.Comment;
import com.lbm.api.dao.mapper.CommentMapper;
import com.lbm.api.util.UserThreadLocal;
import com.lbm.api.vo.CommentVo;
import com.lbm.api.vo.UserVo;
import com.lbm.api.vo.params.CommentParams;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    ThreadPoolService threadPoolService;

    @Autowired
    RedisService redisService;

    @Autowired
    ArticleService articleService;
    @Override
    public List<CommentVo> commentsByArticleId(String ArticleId) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getId,ArticleId);
        articleLambdaQueryWrapper.select(Article::getIsComment);
        Article article = articleService.getOne(articleLambdaQueryWrapper);
        if(article.getIsComment()==0){
            return null;
        }
        //从redis中获取数据
        String key =RedisKeyConfig.COMMENT_VO_LIST;
        List listByKey = redisService.getListByKey(key, ArticleId);
        if(listByKey != null){
            return listByKey;
        }
        LambdaQueryWrapper<Comment> queryWrapper =new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getArticleId, ArticleId);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = this.copyList(comments);
        redisService.setListByKey(key,ArticleId,commentVos);
        return commentVos;
    }

    @Override
    public void createComment(CommentParams commentParams) {
        Comment comment =new Comment();
        comment.setContent(commentParams.getContent());
        comment.setArticleId(commentParams.getArticleId());
        if(StringUtils.isNotEmpty(commentParams.getToUserId())){
            comment.setToUid("0");
        }else {
            comment.setToUid(commentParams.getToUserId());
        }
        if(StringUtils.isNotEmpty(commentParams.getParent())){
            comment.setParentId("0");
            comment.setLevel(1);
        }else {
            comment.setParentId(commentParams.getParent());
            comment.setLevel(2);
        }
        comment.setAuthorId(UserThreadLocal.get().getId());
        commentMapper.insert(comment);
        //从数据库中重写加载文章评论数
        threadPoolService.updateArticleCommentCount(commentParams.getArticleId());
        //删除文章的评论缓存
        String key =RedisKeyConfig.COMMENT_VO_LIST;
        redisService.removeValueByHash(key,commentParams.getArticleId());
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList =new ArrayList<>();
        for (Comment comment : comments) {
            CommentVo commentVo = copy(comment);
            commentVoList.add(commentVo);
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment){
        /**
         * 1.复制comment现有的属性
         * 2.创建时间进行格式化
         * 3.查找userVo对象
         *
         */
        CommentVo commentVo =new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        String authorId = comment.getAuthorId();
        UserVo userVo =sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        //查找子评论
        String parentID = comment.getId();
        if(comment.getLevel()==1){
            List<CommentVo> CommentChildrens= this.findCommentByParentId(parentID);
            commentVo.setChildrens(CommentChildrens);
        }
        //如果Level大于1，说明为子comment,则会有toUser属性
        if(comment.getLevel()>1){
            String toUid = comment.getToUid();
            UserVo toUser = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUser);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentByParentId(String parentID) {
        LambdaQueryWrapper<Comment> queryWrapper =new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getParentId, parentID);
        queryWrapper.eq(Comment::getLevel, 2);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = this.copyList(comments);
        return commentVos;

    }
}
