package com.fkzm.blog.service;

import com.fkzm.blog.entities.Tag;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface TagService {
    Long save(Tag tag);
    Tag getById(Long id);
    Tag getByName(String name);
    PageInfo<Tag> getByList(int pageNo);
    List<Tag> getByList();
    Long update(Long id,Tag tag);
    void delete(Long id);


}
