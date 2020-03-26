package com.fkzm.blog;

import com.fkzm.blog.apputils.ApplicationContextGetter;
import com.fkzm.blog.apputils.MD5;
import com.fkzm.blog.entities.Blog;
import com.fkzm.blog.entities.BlogType;
import com.fkzm.blog.entities.Tag;
import com.fkzm.blog.mapper.BlogMapper;
import com.fkzm.blog.mapper.BlogTagRelationMapper;
import com.fkzm.blog.service.BlogService;
import com.fkzm.blog.service.TagService;
import com.fkzm.blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private ApplicationContextGetter applicationContextGetter;
    @Autowired
   private BlogMapper blogMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService blogTypeService;
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Test
    void contextLoads() {
//        ApplicationContext applicationContext = applicationContextGetter.getApplicationContext();
////        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
////            System.out.print(beanDefinitionName);
//////        }
////        Blog blog=new Blog();
//////        blog.setTitle("sda");
////        BlogType blogType=new BlogType();
////
////        blogType.setId((long) 3);
////        blog.setBlogType(blogType);
////        blog.setRecommend(false);
//////        System.out.println(blogMapper.getByCriteria(blog));
//        System.out.println(tagService.getByList());
//        System.out.println(blogTypeService.getAllByList());
        List<Tag> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(new Tag((long) i));
        }
//        blogTagRelationMapper.getBlogsByTags(list).forEach(System.out::println);
        blogTagRelationMapper.setRelations((long) 4, list);
        System.out.println(MD5.encrypt("1"));
    }


}
