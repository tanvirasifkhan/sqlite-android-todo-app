package com.example.asifkhan.sqlitesimpletodoapp.models;

/**
 * Created by asifkhan on 12/28/17.
 */

public class TagsModel {
    private int tagID;
    private String tagTitle,tagBackgroundColor,tagTextColor;

    public TagsModel(int tagID, String tagTitle, String tagBackgroundColor, String tagTextColor) {
        this.tagID = tagID;
        this.tagTitle = tagTitle;
        this.tagBackgroundColor = tagBackgroundColor;
        this.tagTextColor = tagTextColor;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public String getTagBackgroundColor() {
        return tagBackgroundColor;
    }

    public void setTagBackgroundColor(String tagBackgroundColor) {
        this.tagBackgroundColor = tagBackgroundColor;
    }

    public String getTagTextColor() {
        return tagTextColor;
    }

    public void setTagTextColor(String tagTextColor) {
        this.tagTextColor = tagTextColor;
    }
}
