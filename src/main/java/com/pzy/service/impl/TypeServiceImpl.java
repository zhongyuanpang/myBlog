package com.pzy.service.impl;

import com.pzy.NotFoundException;
import com.pzy.dao.TypeRepository;
import com.pzy.pojo.Type;
import com.pzy.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Override
    @Transactional      // 放进事务里面，（增、删、改、查都可以放进去）
    public Type saveType(Type type) {
        //保存一条数据
        return repository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        //获取一条数据
        return repository.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        //根据name查询
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        //获取全部数据
        return repository.findAll(pageable);
    }

    //分类根据数量排序
    @Override
    @Transactional
    public List<Type> listTypeTop(Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "blogs.size"));
        return repository.findTop(pageable);
    }

    @Override
    //获取全部数据
    public List<Type> listType() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        //更新一条数据并保存
        Type t = repository.getOne(id);
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return repository.save(t);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        repository.deleteById(id);
    }


/**
 * @author : pangzy
 * @date : 2021/7/17 8:40
 * @return : java.lang.Long
 * 获取分类数量
 */
    @Override
    public Long typeCount() {
        return repository.count();
    }
}
