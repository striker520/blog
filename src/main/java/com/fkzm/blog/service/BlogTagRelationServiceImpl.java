package com.fkzm.blog.service;

import com.fkzm.blog.entities.Blog;
import com.fkzm.blog.entities.Tag;
import com.fkzm.blog.entities.TagToBlogCount;
import com.fkzm.blog.mapper.BlogTagRelationMapper;
import com.fkzm.blog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class BlogTagRelationServiceImpl implements  BlogTagRelationService {
    private BlogTagRelationMapper blogTagRelationMapper;
    private TagMapper tagMapper;
    @Autowired
    public BlogTagRelationServiceImpl(BlogTagRelationMapper blogTagRelationMapper,TagMapper tagMapper){
        this.blogTagRelationMapper=blogTagRelationMapper;
        this.tagMapper=tagMapper;
    }

    @Override
    public void setRelation(Blog blog) {
        //blog里是有 id的,之前插入已经赋值了
       //若存在,更新关系
        //若不存在,新增标签,更新关系
        for(Tag tag: blog.getTagList()){
            if(hasTag(tag)>0){
                //有这个标签,需要添加一条关系
                Long tagId=getTagId(tag);
                tag.setId(tagId);
                blogTagRelationMapper.setRelation(blog.getId(),tagId);
            }else{
                //没有标签,新增一个标签,然后添加一条关系
                saveTag(tag);
                //insert 使用自动生成 key 赋值给 tag,而不是 saveTag 的返回值
                Long tagId=tag.getId();
                blogTagRelationMapper.setRelation(blog.getId(),tagId);
            }
        }
    }

    private Long saveTag(Tag tag) {
        return tagMapper.save(tag);
    }

    private Long getTagId(Tag tag) {
        return tagMapper.getId(tag);
    }

    private Long hasTag(Tag tag) {
        return tagMapper.getCount(tag);
    }

    @Override
    public void updateRelation(Blog blog) {
       //遍历 tagList,若已存在,跳过
        for(Tag tag:blog.getTagList()){
            if(hasTag(tag)>0){
                Long tagId=getTagId(tag);
                tag.setId(tagId);
            }else{
                //若不存在,新增 tag,添加一条关系
                saveTag(tag);
                Long tagId=tag.getId();
                blogTagRelationMapper.setRelation(blog.getId(),tagId);

            }
        }
        //这还不够,遍历完成后,关系库中还有一些不在 tagList 的关系存在,需要删除
        //select tagId from blog_tag where blog_id=?-->List<Long> -->遍历 tagList find(tag.id),删除,对 List<Long>剩余部分,再次遍历完成删除
        List<Long> tagIdList=blogTagRelationMapper.getTagIds(blog.getId());
        for(Tag tag:blog.getTagList()){
            tagIdList.remove(tag.getId());
        }
        if(tagIdList.size()>0){
            for(Long tagId:tagIdList){
                deleteRelation(blog.getId(),tagId);
            }
        }

    }

    @Override
    public void addSortedTagsToModel(ModelMap modelMap, Long maxSize) {
        List<TagToBlogCount> tagToBlogCountList=blogTagRelationMapper.getRelations();
        tagToBlogCountList.sort(new Comparator<TagToBlogCount>() {
            @Override
            public int compare(TagToBlogCount o1, TagToBlogCount o2) {
                return -o1.getBlogCount().compareTo(o2.getBlogCount());
            }
        });
        if(tagToBlogCountList.size()>maxSize) {
            tagToBlogCountList = tagToBlogCountList.subList(0, maxSize.intValue());
        }
        modelMap.addAttribute("tagsWithCount", tagToBlogCountList);



    }

    @Override
    public void deleteRelations(Long id) {
        blogTagRelationMapper.deleteRelations(id);
    }

    private void deleteRelation(Long id, Long tagId) {
        blogTagRelationMapper.deleteRelation(id,tagId);
    }

    private void deleteRelations(Blog blog) {

    }
}
