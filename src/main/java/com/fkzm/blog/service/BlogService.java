package com.fkzm.blog.service;

import com.fkzm.blog.entities.Blog;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;

public interface BlogService {
    Blog getById(Long id);

    PageInfo<Blog> listBlog(int pageNo,int pageSize,Blog blog);
    PageInfo<Blog> listAll(int pageNo,int pageSize);
    PageInfo<Blog> listAll(String keyword,int pageNo,int pageSize);

    Long saveBlog(Blog Blog);

    Long updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    PageInfo<Blog> listAllPublished(int i, int i1);

    void addSortedBlogsByCreateTimeToModel(ModelMap modelMap, Long maxSize);

    Blog getParsedBlogById(Long id);
}
