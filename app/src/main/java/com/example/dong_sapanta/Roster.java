package com.example.dong_sapanta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roster {
    @SerializedName("id")
    @Expose
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("fullName")
    @Expose
    private String fullName;
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @SerializedName("link")
    @Expose
    private String link;
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}
