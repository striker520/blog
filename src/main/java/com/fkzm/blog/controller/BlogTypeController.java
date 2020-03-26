package com.fkzm.blog.controller;


import com.fkzm.blog.entities.BlogType;
import com.fkzm.blog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/admin")
@Controller
public class BlogTypeController {
    private TypeService typeService;

    @Autowired
    public BlogTypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/getPage/{offset}/{limit}")
    public String getForPage(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, ModelMap modelMap) {
        PageInfo<BlogType> pageInfo = typeService.getForList(offset, limit);

        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("limit", limit);
        return "admin/typeManage";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        typeService.delete(id);

        return "redirect:/admin/getPage/1/3";
    }

    @GetMapping("/toSave")
    public String toSaveBlogType() {
        return "/admin/typeAdd";
    }

    @PostMapping("/saveBlogType")
    public String saveBlogType(BlogType blogType, ModelMap modelMap) {
        if (typeService.insert(blogType)) {
            modelMap.addAttribute("message", "添加成功!");
        } else {
            modelMap.addAttribute("message", "对不起,已存在该类型,请重新输入");
        }
        return "/admin/typeAdd";
    }

    @GetMapping("/edit/{id}/input")
    public String editType(@PathVariable("id") Long id, ModelMap map) {
        map.addAttribute("typeItem", typeService.get(id));
        return "/admin/editType";
    }

    @PostMapping("/blogType/edit")
    public String doEditType(BlogType blogType, ModelMap map) {
        //前端有非空检查,传入的必定时修改过的值
        if (typeService.findOrUpdate(blogType)){
            map.addAttribute("message", "更新成功");

        }else{
            map.addAttribute("message","对不起,请重新编辑,存在重名");

        }
        map.addAttribute("typeItem",blogType);

        return "/admin/editType";
    }
}
