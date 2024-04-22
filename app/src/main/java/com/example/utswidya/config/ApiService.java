package com.example.utswidya.config;

import com.example.utswidya.models.GithubResponse;
import com.example.utswidya.models.UsersItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    @Headers("Authorization: Bearer " + TOKEN)
    Call<GithubResponse> getUsers(@Query("q") String username);

    @GET("users/{username}")
    Call<UsersItem> getDetailUser(@Path("username") String username);

    String TOKEN = "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";
}
