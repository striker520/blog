package com.fkzm.blog.mapper;

import com.fkzm.blog.entities.BlogType;
import com.fkzm.blog.entities.TypeToBlogs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTypeMapper {

    List<BlogType> getByList();

    void save(@Param("blogType") BlogType blogType);
    Long update(@Param("blogType") BlogType blogType);
    BlogType get(Long id);
    void delete(Long id);
    BlogType getByName(@Param("type") BlogType blogType);

    List<TypeToBlogs> getEveryTypesBlogCount();
}
