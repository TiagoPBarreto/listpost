package com.barreto.listpost.Api;

import com.barreto.listpost.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("posts")
    Call<List<Post>> getPosts();
}
