package com.fkzm.blog.entities;

public class TagToBlogCount {
    private Long tagId;
    private String tagName;
    private Long blogCount;

    @Override
    public String toString() {
        return "TagToBlogCount{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", blogCount=" + blogCount +
                '}';
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Long blogCount) {
        this.blogCount = blogCount;
    }

    public TagToBlogCount() {
    }
}
