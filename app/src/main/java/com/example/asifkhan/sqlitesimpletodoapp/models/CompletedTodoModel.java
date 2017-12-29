package com.example.asifkhan.sqlitesimpletodoapp.models;

/**
 * Created by asifkhan on 12/27/17.
 */

public class CompletedTodoModel {
    private int todoID;
    private String todoTitle,todoContent,todoDate,
            todoTime,todoTag;

    public CompletedTodoModel(){}

    public CompletedTodoModel(int todoID, String todoTitle, String todoContent, String todoDate, String todoTime,String todoTag) {
        this.todoID = todoID;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
        this.todoTag = todoTag;
    }

    public int getTodoID() {
        return todoID;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoContent() {
        return todoContent;
    }

    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
    }

    public String getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    public String getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }
    public String getTodoTag() {
        return todoTag;
    }

    public void setTodoTag(String todoTag) {
        this.todoTag = todoTag;
    }
}
