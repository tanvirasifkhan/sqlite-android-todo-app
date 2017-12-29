package com.example.asifkhan.sqlitesimpletodoapp.models;

/**
 * Created by asifkhan on 12/28/17.
 */

public class TagsModel {
    private int tagID;
    private String tagTitle;

    public TagsModel(){}

    public TagsModel(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public TagsModel(int tagID, String tagTitle) {
        this.tagID = tagID;
        this.tagTitle = tagTitle;
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
}
