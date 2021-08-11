package com.pzy.service;

import com.pzy.pojo.Comment;

import java.util.List;

public interface CommentService {
//    数据列表
    List<Comment> listCommentByBlogId(Long blogId);


//    保存评论
    Comment saveComment(Comment comment);

    Long countComment();

}
