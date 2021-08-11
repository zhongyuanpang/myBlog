package com.pzy.service.impl;

import com.pzy.NotFoundException;
import com.pzy.dao.BlogRepository;
import com.pzy.pojo.Blog;
import com.pzy.pojo.Type;
import com.pzy.service.BlogService;
import com.pzy.util.MarkDownUtils;
import com.pzy.util.MyBeanUtils;
import com.pzy.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    //查询一个主键id
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

/**
 * @author : pangzy
 * @date : 2021/6/11 10:57
 * @return : com.pzy.pojo.Blog
 * 获取内容并转换
 */
    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
         Blog blog = blogRepository.getOne(id);
//        Blog blog = blogRepository.getBlogById(id);
        if (blog == null) {
            throw new NotFoundException("The blog disappears");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        //更新浏览次数
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    //分页查询
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            /**
             *  Root:代表查询的对象
             *  CriteriaQuery：查询的容器
             *  CriteriaBuilder：设置条件的表达式
             * */
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                //判断标题是否为空
                if(!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicateList.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                //判断类型是否为空
                if(blog.getTypeId()!= null){
                    predicateList.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommed()){
                    predicateList.add(cb.equal(root.<Boolean>get("recommed"),blog.isRecommed()));
                }
                cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tageId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");//关联tags表
                return cb.equal(join.get("id"),tageId);
            }
        },pageable);
    }


    /**
 * @author : pangzy
 * @date : 2021/6/11 10:10
 * @return : org.springframework.data.domain.Page<com.pzy.pojo.Blog>
 * 搜索查询方法
 */
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }


    //
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "updateTime"));
        return blogRepository.findTop(pageable);
    }

    //归档查询
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
//            System.out.println(year);
//            System.out.println(map.keySet());
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }


    @Transactional
    @Override
    //保存一条数据，并放进事务里面
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }


    @Transactional
    @Override
    //更新并保存
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null) {
            throw new NotFoundException("Post not exist");
        }
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    //删除
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
