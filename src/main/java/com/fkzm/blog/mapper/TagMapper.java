package com.fkzm.blog.mapper;

import com.fkzm.blog.entities.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    Tag get(Long id);
    List<Tag> getList();
    Tag getByName(String name);
    Long delete(Long id);
    Long save(@Param("tag") Tag tag);
    Long update(@Param("tag") Tag tag,@Param("id") Long id);

    Long getCount(@Param("tag") Tag tag);
    Long getId(@Param("tag") Tag tag);


}
