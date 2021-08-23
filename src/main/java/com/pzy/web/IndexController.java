package com.pzy.web;


import com.alibaba.fastjson.JSONObject;
import com.pzy.pojo.Blog;
import com.pzy.service.*;
import com.pzy.vo.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

/**
 * @author : pangzy
 * @date : 2021/6/11 10:03
 * @return : java.lang.String
 * 主页的显示内容，文章、分类，标签、推荐
 */
    @GetMapping("/")
    public String index(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));  //分页数据
        model.addAttribute("types",typeService.listTypeTop(7));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(7));
        model.addAttribute("blogCount",blogService.countBlog());
        model.addAttribute("commentCount",commentService.countComment());
        model.addAttribute("typeCount",typeService.typeCount());
        model.addAttribute("user",userService.getUser(1L));
        return "index";
    }


/**
 * @author : pangzy
 * @date : 2021/7/20 14:36
 * @return : java.lang.String
 * 侧边栏数据
 */
    @GetMapping("/slideInfo")
    public String _fragments(Model model){
        model.addAttribute("blogCount",blogService.countBlog());
        model.addAttribute("commentCount",commentService.countComment());
        model.addAttribute("typeCount",typeService.typeCount());
        model.addAttribute("user",userService.getUser(1L));
        return "_fragments.html";
    }

/**
 * @author : pangzy
 * @date : 2021/7/17 8:33
 * @return : java.lang.String
 * 个人用户页个人信息及分类数据
 */
    @GetMapping("/indexGetuserInfo")
    @ResponseBody
    public String indexGetUserInfo(){
        JSONObject result = new JSONObject();
        HashMap<String,Object> blogMap = new HashMap<>();
        HashMap<String,Object> commentMap = new HashMap<>();
        HashMap<String,Object> typeMap = new HashMap<>();

        Long blogCount = blogService.countBlog();
        Long commentCount = commentService.countComment();
        UserQuery user = userService.getUser(1L);
        Long typeCount = typeService.typeCount();

        blogMap.put("blogCount",blogCount);
        commentMap.put("commentCount",commentCount);
        typeMap.put("typeCount",typeCount);
        result.put("blog",blogMap);
        result.put("comment",commentMap);
        result.put("user",user);
        result.put("type",typeMap);

        return result.toString();
    }


/**
 * @author : pangzy
 * @date : 2021/6/11 10:02
 * @return : java.lang.String
 * 搜索内容
 */
    @PostMapping("/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"}
                            ,direction = Sort.Direction.DESC) Pageable pageable
                            , Model model
                            ,@RequestParam String query){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }



    //footer下面3条最新推荐
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        List<Blog> blogs = blogService.listRecommendBlogTop(4);
        model.addAttribute("newblogs", blogs);
        return "_fragments :: newblogList";
    }
}
