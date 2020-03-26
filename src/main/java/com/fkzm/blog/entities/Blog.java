package com.fkzm.blog.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer pageViews;
    private boolean canPraise;
    private boolean canShare;
    private boolean canComment;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;
    private User user;
    private List<Comment> commentList = new ArrayList<>();
    private BlogType blogType;
    private List<Tag> tagList = new ArrayList<>();

    public Blog() {
    }
    public String tagListToString(){
        if(tagList!=null){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<tagList.size();i++){
                sb.append(tagList.get(i));
                if(i<tagList.size()-1){
                    sb.append(",");
                }
            }
            return sb.toString();
        }else {
            return "";
        }
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", pageViews=" + pageViews +
                ", canPraise=" + canPraise +
                ", canShare=" + canShare +
                ", canComment=" + canComment +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", user=" + user +
                ", commentList=" + commentList +
                ", blogType=" + blogType +
                ", tagList=" + tagList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public boolean isCanPraise() {
        return canPraise;
    }

    public void setCanPraise(boolean canPraise) {
        this.canPraise = canPraise;
    }

    public boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    public boolean isCanComment() {
        return canComment;
    }

    public void setCanComment(boolean canComment) {
        this.canComment = canComment;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
