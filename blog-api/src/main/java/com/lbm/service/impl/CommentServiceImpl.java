package com.lbm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lbm.common.cache.Cache;
import com.lbm.config.RedisKeyConfig;
import com.lbm.dao.entity.Comment;
import com.lbm.dao.mapper.CommentMapper;
import com.lbm.service.CommentService;
import com.lbm.service.RedisService;
import com.lbm.service.SysUserService;
import com.lbm.service.ThreadPoolService;
import com.lbm.util.UserThreadLocal;
import com.lbm.vo.CommentVo;
import com.lbm.vo.LoginVo;
import com.lbm.vo.UserVo;
import com.lbm.vo.params.CommentParams;
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


    @Override
    public List<CommentVo> commentsByArticleId(Long ArticleId) {
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
        if(commentParams.getToUserId() ==null){
            comment.setToUid(0L);
        }else {
            comment.setToUid(commentParams.getToUserId());
        }
        if(commentParams.getParent()==null){
            comment.setParentId(0L);
            comment.setLevel(1);
        }else {
            comment.setParentId(commentParams.getParent());
            comment.setLevel(2);
        }
        comment.setCreateDate(System.currentTimeMillis());
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
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        Long authorId = comment.getAuthorId();
        UserVo userVo =sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        //查找子评论
        Long parentID = comment.getId();
        List<CommentVo> CommentChildrens= this.findCommentByParentId(parentID);
        commentVo.setChildrens(CommentChildrens);
        //如果Level大于1，说明为子comment,则会有toUser属性
        if(comment.getLevel()>1){
            Long toUid = comment.getToUid();
            UserVo toUser = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUser);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentByParentId(Long parentID) {
        LambdaQueryWrapper<Comment> queryWrapper =new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getParentId, parentID);
        queryWrapper.eq(Comment::getLevel, 2);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = this.copyList(comments);
        return commentVos;

    }


}
