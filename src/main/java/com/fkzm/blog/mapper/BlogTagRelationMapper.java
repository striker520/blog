package com.fkzm.blog.mapper;

import com.fkzm.blog.entities.Blog;
import com.fkzm.blog.entities.Tag;
import com.fkzm.blog.entities.TagToBlogCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogTagRelationMapper {

    List<Blog> getBlogsByTags(List<Tag> tagList);
    void setRelations(@Param("blogId") Long id, @Param("tagList") List<Tag> tagList);



    void deleteRelations(Long key);

    void setRelation(Long id, Long tagId);

    List<Long> getTagIds(@Param("id") Long id);

    void deleteRelation(Long id, Long tagId);

   List<TagToBlogCount> getRelations();
}
