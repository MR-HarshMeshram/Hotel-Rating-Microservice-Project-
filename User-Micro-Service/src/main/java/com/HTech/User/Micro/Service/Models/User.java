package com.HTech.User.Micro.Service.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Transient;

@Document(collection = "users")
public class User {
    @Id
    private  String id;
    private String username;
    private String email;
    private String about;
    @Transient
    private List<Rating> ratings = new ArrayList<>();

    public User(String id, String username, String email, String about, List<Rating> ratings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.about = about;
        this.ratings = ratings;
    }

    public User() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
