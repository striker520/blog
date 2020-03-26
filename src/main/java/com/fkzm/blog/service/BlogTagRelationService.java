package com.fkzm.blog.service;

import com.fkzm.blog.entities.Blog;
import org.springframework.ui.ModelMap;

public interface BlogTagRelationService {
    void setRelation(Blog blog);
    void updateRelation(Blog blog);

    void addSortedTagsToModel(ModelMap modelMap, Long maxSize);

    void deleteRelations(Long id);
}
