package com.pzy.dao;

import com.pzy.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogId(Long blogId, Sort sort);



//    根据创建时间排序，最新的排上面
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
