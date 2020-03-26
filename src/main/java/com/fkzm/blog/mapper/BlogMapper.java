package com.fkzm.blog.mapper;

import com.fkzm.blog.entities.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface BlogMapper  {


    Blog getById(Long id);

    Blog getByType(Integer id);

    Blog getByTag(Integer id);

    Long updateArticle(@Param("blog") Blog blog);

    Long delete(Long id);

    Long save(@Param("blog") Blog blog);

    List<Blog> getByCriteria(@Param("blog") Blog blog);

        List<Blog> getAll();

    List<Blog> getAllPublished();

    List<Blog> getSortedPublishedWithLimit(Long maxSize);

    List<Blog> getAllByKeyWord(String keyword);

    void incViews(Long id);
}
