package com.example.asifkhan.sqlitesimpletodoapp.models;

/**
 * Created by asifkhan on 12/27/17.
 */

public class PendingTodoModel {
    private int todoID;
    private String todoTitle,todoContent,todoDate,
            todoTimeFrom,toTimeTo,todoPriority,todoTag,todoColor,todoTextColor;

    public PendingTodoModel(){}

    public PendingTodoModel(int todoID, String todoTitle, String todoContent, String todoDate, String todoTimeFrom, String toTimeTo, String todoPriority, String todoTag, String todoColor,String todoTextColor) {
        this.todoID = todoID;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoDate = todoDate;
        this.todoTimeFrom = todoTimeFrom;
        this.toTimeTo = toTimeTo;
        this.todoPriority = todoPriority;
        this.todoTag = todoTag;
        this.todoColor = todoColor;
        this.todoTextColor=todoTextColor;
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

    public String getTodoTimeFrom() {
        return todoTimeFrom;
    }

    public void setTodoTimeFrom(String todoTimeFrom) {
        this.todoTimeFrom = todoTimeFrom;
    }

    public String getToTimeTo() {
        return toTimeTo;
    }

    public void setToTimeTo(String toTimeTo) {
        this.toTimeTo = toTimeTo;
    }

    public String getTodoPriority() {
        return todoPriority;
    }

    public void setTodoPriority(String todoPriority) {
        this.todoPriority = todoPriority;
    }

    public String getTodoTag() {
        return todoTag;
    }

    public void setTodoTag(String todoTag) {
        this.todoTag = todoTag;
    }

    public String getTodoColor() {
        return todoColor;
    }

    public void setTodoColor(String todoColor) {
        this.todoColor = todoColor;
    }

    public String getTodoTextColor() {
        return todoTextColor;
    }

    public void setTodoTextColor(String todoTextColor) {
        this.todoTextColor = todoTextColor;
    }
}
