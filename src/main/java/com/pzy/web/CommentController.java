package com.pzy.web;

import com.pzy.pojo.Comment;
import com.pzy.pojo.User;
import com.pzy.service.BlogService;
import com.pzy.service.CommentService;
import com.pzy.util.RandomAvatarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;


    //默认评论头像
//    @Autowired
//    @Value("${comment.avatar}")
//    private String avatar = "http://api.btstu.cn/sjtx/api.php?lx=c1&format=images";


    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

/**
 * @author : pangzy
 * @date : 2021/6/11 15:07
 * @return : java.lang.String
 * 提交评论信息方法
 */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            // 判断邮箱是否为qq邮箱，设置用户头像为qq头像
            if (comment.getEmail().trim().toLowerCase().contains("@qq.com")){
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(comment.getEmail());
                comment.setAvatar("http://q1.qlogo.cn/g?b=qq&nk="+m.replaceAll("").trim()+"&s=100");
            }else {
                comment.setAvatar(RandomAvatarUtils.randomAvatar());
            }
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
