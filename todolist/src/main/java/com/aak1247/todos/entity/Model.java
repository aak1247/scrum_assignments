package com.aak1247.todos.entity;

public abstract class Model {
    int id = -1;

    Model() {
    }

    Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public abstract Todo copy();
}
