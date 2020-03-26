package com.fkzm.blog.service;

import com.fkzm.blog.entities.Tag;
import com.fkzm.blog.mapper.TagMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class TagServiceImpl implements  TagService {
    private TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public Long save(Tag tag) {
        return tagMapper.save(tag);
    }

    @Override
    public Tag getById(Long id) {
        return tagMapper.get(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagMapper.getByName(name);
    }

    @Override
    public PageInfo<Tag> getByList(int pageNo) {
        PageHelper.startPage(pageNo, 5);
        List<Tag> tagList=tagMapper.getList();
        return  new PageInfo<>(tagList);
    }

    @Override
    public List<Tag> getByList() {
        return tagMapper.getList();
    }

    @Override
    public Long update(Long id, Tag tag) {
        return tagMapper.update(tag,id);
    }

    @Override
    public void delete(Long id) {
        tagMapper.delete(id);
    }



}
