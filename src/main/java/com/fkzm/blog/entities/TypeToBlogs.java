package com.fkzm.blog.entities;

public class TypeToBlogs {
    private Long typeId;
    private String typeName;
    private Long blogCount;

    @Override
    public String toString() {
        return "TypeToBlogs{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", blogCount=" + blogCount +
                '}';
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Long blogCount) {
        this.blogCount = blogCount;
    }

    public TypeToBlogs() {
    }
}
