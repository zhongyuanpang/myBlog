package com.pzy.service.impl;

import com.pzy.dao.CommentRepository;
import com.pzy.pojo.Comment;
import com.pzy.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
//        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
//        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, Sort.by(Sort.Direction.DESC, "createTime"));
        return eachComment(comments);
    }


/**
 * @author : pangzy
 * @date : 2021/6/11 14:59
 * @return : com.pzy.pojo.Comment
 * 保存信息
 */
@Transactional
@Override
public Comment saveComment(Comment comment) {
    Long parentCommentId = comment.getParentComment().getId();
    if (parentCommentId != -1) {
        comment.setParentComment(commentRepository.getOne(parentCommentId));
    } else {
        comment.setParentComment(null);
    }
    comment.setCreateTime(new Date());
    return commentRepository.save(comment);
}

    @Override
    public Long countComment() {
        return commentRepository.count();
    }


    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        // 合并评论的各层子代到第一级集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replies = comment.getReplyComments();
            for(Comment reply : replies) {
                // find and save child comments in replies
                recursively(reply);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplies);
            //清除临时存放区
            tempReplies = new ArrayList<>();
        }
    }

    // get collections of all child nodes
    private List<Comment> tempReplies = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplies.add(comment);    //tempReplies is a collection of all parent nodes
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                recursively(reply);
            }
        }
    }
}
