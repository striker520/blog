package com.fkzm.blog.service;

import com.fkzm.blog.apputils.MarkDownUtils;
import com.fkzm.blog.entities.Blog;
import com.fkzm.blog.entities.Tag;
import com.fkzm.blog.exception.NotFoundException;
import com.fkzm.blog.mapper.BlogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogTagRelationService blogTagRelationService;
    private BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper, BlogTagRelationService blogTagRelationService) {
        this.blogMapper = blogMapper;
        this.blogTagRelationService = blogTagRelationService;

    }

    @Override
    public Blog getById(Long id) {
        return blogMapper.getById(id);
    }


    //分页参数暂定为在此方法中定义,后期优化可以将参数添加到配置文件中
    //吸取 BlogType 查询的教训,这次显示要求传入欲修改的 Blog id;
    //其实应该定义一个 BlogCriteria 对象封装查询条件的,这里将查询条件封装成一个 Blog,动态生成查询语句
    @Override
    @Transactional
    public PageInfo<Blog> listBlog(int pageNo, int pageSize, Blog blog) {
        PageHelper.startPage(pageNo, pageSize);
        List<Blog> blogList = blogMapper.getByCriteria(blog);
        return new PageInfo<>(blogList);

    }

    @Override
    @Transactional
    public PageInfo<Blog> listAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Blog> blogList = blogMapper.getAll();
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> listAll(String keyword, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        List<Blog> blogList=blogMapper.getAllByKeyWord(keyword);
        return new PageInfo<>(blogList);
    }

    @Override
    @Transactional
    public Long saveBlog(Blog blog) {
        //首先需要建立关系,type是 manyToOne 的,所以不用额外操作,关键在于 tag 是多对多的关系,数据库中存在一张 blog_tag 表保存关系,每行表示一个对应关系
        //例如一篇文章有两个标签,那么就有两行表示这种关系的数据,这张表的 id字段不重要
        //使用情景,读者选取了几个标签,那么就要找到这张表, select blog_id from blog_tag where tag_id in (....)查到所有 id
        // 然后  select * from blog where id in (select blog_id from blog_tag where tag_id in (....)) 组合查询
        //千万别在 java代码里使用 for循环这种2b操作,虚拟机的性能跟数据库可没法比

        //多选下拉有 bug,只能选一个,权宜之计是用 input ,分隔,这样的话不知道 id,每次新增文章都要查询 tag是否存在,不存在则添加,存在的话需要更新关系
        List<Tag> tagList = blog.getTagList();
        //如果传入的 blog id为 null 表示是添加,否则是更新

        //对于更新,删除原来的旧关系,其后操作和新增一样

        //关键是如何维护 tag 库
        boolean flag = blog.getId() == null;
//        blogTagRelationMapper.setRelation(tagList); 这是查询用的,写错了
        blog.setFirstPicture("https://picsum.photos/1000");
        Long key;
        if (flag) {
            //此时 taglist 只有 name,没有 id
            blogMapper.save(blog);
            key = blog.getId();
            blogTagRelationService.setRelation(blog);
            return key;

            //需要修改方法,很麻烦,建立一个关系维护 Service吧
//            blogTagRelationMapper.setRelations(key, blog.getTagList());
        } else {
            //针对更新blog操作

            blogMapper.updateArticle(blog);
            key = blog.getId();
            blogTagRelationService.updateRelation(blog);

            return key;

        }


    }

    @Override
    @Transactional
    public Long updateBlog(Long id, Blog blog) {
        blog.setId(id);
        return blogMapper.updateArticle(blog);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        //删除 blog
        blogMapper.delete(id);
        //删除关系,但不删除 tag
        blogTagRelationService.deleteRelations(id);
    }

    @Override
    public PageInfo<Blog> listAllPublished(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Blog> blogList = blogMapper.getAllPublished();
        return new PageInfo<>(blogList);
    }

    @Override
    public void addSortedBlogsByCreateTimeToModel(ModelMap modelMap, Long maxSize) {
        //按照更新时间排序,只是标签展示,所以不用查询太多数据
        //id title updateTime
        List<Blog> blogList=blogMapper.getSortedPublishedWithLimit(maxSize);
        modelMap.addAttribute("sortedBlog",blogList);
    }

    @Transactional
    @Override
    public Blog getParsedBlogById(Long id) {
        Blog blog=getById(id);
        if(blog == null){
            throw new NotFoundException("该博客不存在");
        }
        //先设置浏览数加一
        blogMapper.incViews(id);
        String content = blog.getContent();
        if(content !=null ||!content.equals("")){
            String parsedContent= MarkDownUtils.markdownToHtml(content);
            blog.setContent(parsedContent);
        }
        return blog;

    }
}
