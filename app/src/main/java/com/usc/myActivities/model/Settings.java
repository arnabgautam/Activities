package com.usc.myActivities.model;

import java.util.UUID;

public class Settings {

    private UUID mId;

    private String name;
    private String id;
    private String email;
    private String gender;
    private String comment;

    public Settings(){
        mId = UUID.randomUUID();
    }

    public Settings(UUID id) {
        mId = id;
    }

    public Settings(String name, String id, String email, String gender, String comment) {
        this(UUID.randomUUID());
        this.name = name;
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.comment = comment;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
