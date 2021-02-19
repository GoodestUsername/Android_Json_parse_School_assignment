package com.example.dong_sapanta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {
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

    @SerializedName("age")
    @Expose
    private String age;
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    @SerializedName("nationality")
    @Expose
    private String nationality;
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @SerializedName("position")
    @Expose
    private String position;
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
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


    private String team;
    public String getTeam() { return team; }
    public void setTeam(String team) {
        this.team = team;
    }

    public Player(String name, String team, String age, String nationality, String position) {
        this.fullName = name;
        this.team = team;
        this.age = age;
        this.nationality = nationality;
        this.position = position;
    }
}

