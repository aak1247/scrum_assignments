package com.aak1247.todos.entity;

import java.util.Date;

@SuppressWarnings("unused")
public class Todo extends Model {
    private String content;
    private Date createdTime;

    public Todo() {
    }

    @SuppressWarnings("all")
    public Todo(int id, String content, Date createdTime) {
        super(id);
        this.content = content;
        this.createdTime = createdTime;
    }

    public Todo(String content, Date createdTime) {
        this.content = content;
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Todo copy() {
        return new Todo(this.id, this.content, this.createdTime);
    }
}
