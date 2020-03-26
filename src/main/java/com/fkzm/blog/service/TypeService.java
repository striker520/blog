package com.fkzm.blog.service;

import com.fkzm.blog.entities.BlogType;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface TypeService {


    BlogType get(Long id);
    List<BlogType> getAllByList();

    PageInfo<BlogType> getForList(int pageNum,int limit);

    void delete(Long id);

   Boolean insert(BlogType blogType);

   void update(BlogType blogType);

   BlogType getByName(BlogType blogType);

   Boolean findOrUpdate(BlogType blogType);


    void addSortedTypesToModel(ModelMap modelMap, Long maxSize);
}
