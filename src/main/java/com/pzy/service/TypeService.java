package com.pzy.service;

import com.pzy.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);   //保存

    Type getType(Long id);    //获取单个

    Type getTypeByName(String name);//根据名称查询

    Page<Type> listType(Pageable pageable);     //获取多个

    List<Type> listTypeTop(Integer size);    //页数

    List<Type> listType();

    Type updateType(Long id,Type type);     //修改

    void deleteType(Long id);   //删除

    Long typeCount();
}
