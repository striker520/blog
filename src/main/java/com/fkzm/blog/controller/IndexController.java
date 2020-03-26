package com.fkzm.blog.controller;

import com.fkzm.blog.apputils.ColorGenerator;
import com.fkzm.blog.entities.Blog;
import com.fkzm.blog.service.BlogService;
import com.fkzm.blog.service.BlogTagRelationService;
import com.fkzm.blog.service.TagService;
import com.fkzm.blog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
public class IndexController {
    private BlogService blogService;
    private TagService tagService;
    private TypeService typeService;
    private BlogTagRelationService blogTagRelationService;
    private ColorGenerator colorGenerator;
    @Autowired
    public IndexController(BlogService blogService, TagService tagService, TypeService typeService, BlogTagRelationService blogTagRelationService,ColorGenerator colorGenerator) {
        this.blogService = blogService;
        this.tagService = tagService;
        this.typeService = typeService;
        this.blogTagRelationService = blogTagRelationService;
        this.colorGenerator=colorGenerator;
    }

    @GetMapping("")
    public String index(ModelMap modelMap){
        Long maxSize= (long) '5';
        //获取文章分页
        PageInfo<Blog> blogPageInfo=blogService.listAllPublished(1, 5);
        modelMap.addAttribute("blogPageInfo", blogPageInfo);
        //获取5个 recommend blog(只包含 id,title),按照更新时间排序
        blogService.addSortedBlogsByCreateTimeToModel(modelMap,maxSize);
        //获取5个标签,按照包含文章数量降序
        blogTagRelationService.addSortedTagsToModel(modelMap,maxSize);
        //获取5个类型,同样按照文章数量降序
        typeService.addSortedTypesToModel(modelMap,maxSize);
        modelMap.addAttribute("colorGenerator", colorGenerator);

        return "index";
    }
//    @GetMapping("page/{pageNo}")
//    public String getPage(@PathVariable("pageNo") Integer pageNo){
//        PageInfo<Blog> blogPageInfo=blogService.listAllPublished(pageNo, 5);
//
//
//    }
//    @GetMapping("detail/{id}")
//    public String toDetail(@PathVariable("id")Long id){
//
//
//    }

    @PostMapping("/search")
    public String search(String keyword,ModelMap modelMap){
        modelMap.addAttribute("blogPageInfo",blogService.listAll(keyword,1,5));
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Long id,ModelMap modelMap){
        modelMap.addAttribute("blog",blogService.getParsedBlogById(id));
        return "blog";
    }
}
