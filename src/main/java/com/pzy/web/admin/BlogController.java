package com.pzy.web.admin;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.pzy.pojo.Blog;
import com.pzy.pojo.User;
import com.pzy.service.BlogService;
import com.pzy.service.TagService;
import com.pzy.service.TypeService;
import com.pzy.util.UploadGiteeImgBed;
import com.pzy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Value("http://m5gjng.natappfree.cc/upload/blog/")
    private String editUploadPath;


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(
            @PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable
                    , BlogQuery blog, Model model){
        //初始化分类
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

/**
 * @author : pangzy
 * @date : 2021/6/10 16:11
 * @return : java.lang.String
 * 搜索文章
 */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }



    @GetMapping("/blogs/input")
    public String input(Model model){
        //初始化分类
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

/**
 * @author : pangzy
 * @date : 2021/6/10 16:12
 * @return : java.lang.String
 * 编辑文章
 */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }


    private void setTypeAndTag(Model model) {
        // model.addAttribute("flags", flagService.listFlag());
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }


    //增加修改
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes , HttpSession session){
        blog.setUser((User) session.getAttribute("user"));  //设置user
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b ;
        //如果传来有id的话就更新数据、没有的话就保存
        if (blog.getId() == null){
            b = blogService.saveBlog(blog);
        }else{
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "Update fail");
        } else {
            attributes.addFlashAttribute("message", "Update success");
        }
        return REDIRECT_LIST;
    }

/**
 * @author : pangzy
 * @date : 2021/6/10 17:04
 * @return : null
 * 删除文章
 */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功!");
        return REDIRECT_LIST;
    }

/**
 * @author : pangzy
 * @date : 2021/7/19 18:24
 * @return : com.pzy.util.Result
 * 文章上传图片
 */

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @CrossOrigin(origins = {"*","null"})
    @ResponseBody
    public JSONObject uploadImg(@RequestParam(value = "editormd-image-file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        String originalFilname = file.getOriginalFilename();

        if(originalFilname == null){
            System.out.println("文件为空");
        }
        String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilname);

        //请求体封装
        Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(file.getBytes());
        //借助HttpUtil工具类发送POST请求
        String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
        //解析响应JSON字符串
        cn.hutool.json.JSONObject jsonObj = JSONUtil.parseObj(JSONResult);

        cn.hutool.json.JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));

        String url = (String) content.getObj("download_url");
        JSONObject res = new JSONObject();
        res.put("url", " "+ url + "");
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }
}
