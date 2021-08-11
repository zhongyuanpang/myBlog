package com.pzy.service.impl;

import com.pzy.NotFoundException;
import com.pzy.dao.TagRepository;
import com.pzy.pojo.Tag;
import com.pzy.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    //保存一个内容
    @Override
    public Tag saveTag(Tag type) {
        return tagRepository.save(type);
    }

    //根据id获取内容
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    //根据name或者内容
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }


    //获取页数
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    //获取全部数据
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "blogs.size"));
        return tagRepository.findTop(pageable);
    }


    //获取一组对象:如1,2,3,4
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    /**
     * @author : pangzy
     * @date : 2021/6/10 11:13
     * @return : java.util.List<java.lang.Long>
     * 把字符串转换为数组
     */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (ids != null && !ids.equals("")) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        //更新一条数据并保存
        Tag t = tagRepository.getOne(id);
        if( t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(t,tag);
        return tagRepository.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
