package com.fkzm.blog.service;

import com.fkzm.blog.entities.BlogType;
import com.fkzm.blog.entities.TypeToBlogs;
import com.fkzm.blog.mapper.BlogTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Comparator;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final BlogTypeMapper blogTypeMapper;

    @Autowired
    public TypeServiceImpl(BlogTypeMapper blogTypeMapper) {
        this.blogTypeMapper = blogTypeMapper;

    }

    @Transactional
    @Override
    public BlogType get(Long id) {
        return blogTypeMapper.get(id);
    }

    @Override
    public List<BlogType> getAllByList() {
        return blogTypeMapper.getByList();
    }

    @Transactional
    @Override
    public PageInfo<BlogType> getForList(int pageNum, int limit) {
        PageHelper.startPage(pageNum, limit);
        List<BlogType> byList = blogTypeMapper.getByList();

        return new PageInfo<>(byList);
    }

    @Override
    public BlogType getByName(BlogType blogType) {
        return blogTypeMapper.getByName(blogType);
    }

    @Override
    public Boolean findOrUpdate(BlogType blogType) {
        //首先确定没有重名
        BlogType exist = blogTypeMapper.getByName(blogType);
        if(exist !=null){
            return false;
        }else{
            return blogTypeMapper.update(blogType) > 0;
        }


    }

    @Override
    public void addSortedTypesToModel(ModelMap modelMap, Long maxSize) {
        List<TypeToBlogs> typeToBlogList=blogTypeMapper.getEveryTypesBlogCount();
        typeToBlogList.sort(new Comparator<TypeToBlogs>() {
            @Override
            public int compare(TypeToBlogs o1, TypeToBlogs o2) {
                return -o1.getBlogCount().compareTo(o2.getBlogCount());
            }
        });
        if(typeToBlogList.size()>maxSize) {
            typeToBlogList = typeToBlogList.subList(0, maxSize.intValue());
        }
        modelMap.addAttribute("typesWithCount", typeToBlogList);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        blogTypeMapper.delete(id);
    }

    @Transactional
    @Override
    public Boolean insert(BlogType blog) {
        BlogType old = getByName(blog);
        if (old != null && old.getName().equals(blog.getName())) return false;
        else {
            blogTypeMapper.save(blog);
            return true;
        }
    }

    @Transactional
    @Override
    public void update(BlogType blogType) {
        blogTypeMapper.update(blogType);
    }
}
