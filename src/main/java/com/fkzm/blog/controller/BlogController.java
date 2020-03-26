package com.fkzm.blog.controller;

import com.fkzm.blog.entities.Blog;
import com.fkzm.blog.entities.BlogType;
import com.fkzm.blog.entities.Tag;
import com.fkzm.blog.entities.User;
import com.fkzm.blog.service.BlogService;
import com.fkzm.blog.service.TagService;
import com.fkzm.blog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping("/admin")
@Controller
public class BlogController {
    private BlogService blogService;
    private TypeService typeService;
    private TagService tagService;

    @Autowired
    public BlogController(BlogService blogService,TypeService typeService,TagService tagService){
        this.blogService=blogService;
        this.typeService=typeService;
        this.tagService=tagService;
    }

    @GetMapping("/editBlog/toPage/{pageNo}")
    public  String toEditPage(ModelMap modelAndView,@PathVariable("pageNo")int pageNo){
        PageInfo<Blog> pageInfo=blogService.listAll(pageNo, 5);
        List<BlogType> allByList = typeService.getAllByList();
        modelAndView.addAttribute("typeList", allByList);

        modelAndView.addAttribute("pageInfo",pageInfo);

        return "admin/editArticle";
    }
    //这是 ajax所用的搜索控制器,返回一个 table片段
    @PostMapping("/editBlog/changePage")
    public  String changePage(ModelMap modelAndView,int pageNo){
        PageInfo<Blog> pageInfo=blogService.listAll(pageNo, 5);
        List<BlogType> allByList = typeService.getAllByList();
        modelAndView.addAttribute("typeList", allByList);

        modelAndView.addAttribute("pageInfo",pageInfo);
        return "admin/editArticle :: blogList";
    }
    @GetMapping("/editBlog/{id}")
    public String editArticle(ModelMap modelMap, @PathVariable("id")Long id){
        Blog blog = blogService.getById(id);
        modelMap.addAttribute("readyToEditBlog",blog);
        return "admin/editArticle";
    }
    @GetMapping("/deleteBlog/{id}")
    public String editBlog(@PathVariable("id")Long id,RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/editBlog/toPage/1";
    }

    @PostMapping("/searchBlog")
    public String searchBlog(String title,String typeId,Boolean recommend,String pageNum,ModelMap modelMap){
        Blog criteria=new Blog();
        if(title!=null)criteria.setTitle(title);
        if(typeId!=null){
            BlogType blogType=new BlogType();
            blogType.setId(Long.parseLong(typeId));
        }
        criteria.setRecommend(recommend);
        int pageNo=1;
        if(pageNum!=null){
            pageNo=Integer.parseInt(pageNum);
        }
        PageInfo<Blog> pageInfo = blogService.listBlog(pageNo, 5, criteria);
        List<BlogType> allByList = typeService.getAllByList();
        modelMap.addAttribute("typeList", allByList);
        modelMap.addAttribute("pageInfo",pageInfo);

        return "admin/editArticle :: blogList";

    }
    @GetMapping("/toAddBlog")
    public String add(ModelMap modelMap){
        List<Tag> tagList=tagService.getByList();
        List<BlogType> blogTypeList=typeService.getAllByList();
        modelMap.addAttribute("tagList", tagList);
        modelMap.addAttribute("blogTypeList",blogTypeList);
        return "admin/addBlog";
    }
    @PostMapping("/blogs")
    public String input(ModelMap modelMap, Blog blog, HttpSession session ,String _tagList,RedirectAttributes redirectAttributes){
        blog.setUser((User)session.getAttribute("user"));
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
       String typeName=typeService.get(blog.getBlogType().getId()).getName();

       blog.getBlogType().setName(typeName);
       String[] strs=_tagList.split(",");
       List<Tag> tagList=new ArrayList<>();
       if(!_tagList.equals("")) {
           for (String s : strs) {
               Tag tag = new Tag();
               tag.setName(s);
               tagList.add(tag);
           }
       }
       blog.setTagList(tagList);


        blog.setPageViews(0);



        System.out.println("------------------------------------------");

        System.out.println(blog);

        blogService.saveBlog(blog);
        redirectAttributes.addFlashAttribute("message", "添加成功");
        //站内重定向,方法默认为 get,转发则还是原来的方法类型,站内重定向适用于一顿操作后再次返回某个页面,如刷新列表,主页等
        return "redirect:/admin/editBlog/toPage/1";
    }
    @GetMapping("/toBlogEdit/{id}")
    public String readyToEdit(@PathVariable("id")Long id,ModelMap modelMap){
        Blog blog=blogService.getById(id);
        List<Tag> tagList=tagService.getByList();
        List<BlogType> blogTypeList=typeService.getAllByList();
        modelMap.addAttribute("tagList", tagList);
        modelMap.addAttribute("blogTypeList",blogTypeList);
        modelMap.addAttribute("readyToEditBlog",blog);
        return "admin/editBlogPage";
    }
    @PostMapping("/blogs/edit")
    public String editBlog(ModelMap modelMap, Blog blog, HttpSession session , String _tagList, RedirectAttributes redirectAttributes){
        String[] strs=_tagList.split(",");
        List<Tag> tagList=new ArrayList<>();
        if(!_tagList.equals("")) {
            for (String s : strs) {
                Tag tag = new Tag();
                tag.setName(s);
                tagList.add(tag);
            }
        }

        blog.setTagList(tagList);
        blog.setUpdateTime(new Date());
        blogService.saveBlog(blog);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/editBlog/toPage/1";

    }


}
