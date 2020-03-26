package com.fkzm.blog.entities;

public class BlogType {
    private Long id;
    private String name;

    public BlogType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
