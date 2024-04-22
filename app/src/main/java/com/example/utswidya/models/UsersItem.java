package com.example.utswidya.models;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersItem {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("bio")
    private String bio;

//    public CharSequence getLogin() {
//        return null;
//    }
//
//    public Object getAvatarUrl() {
//        return null;
//    }
//
//    public CharSequence getBio() {
//        return null;
//    }
}
