package com.barreto.listpost.controller;

import com.barreto.listpost.Api.PostService;
import com.barreto.listpost.Api.PostView;
import com.barreto.listpost.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostController {

    private final PostService postService;
    private final PostView postView;

    public PostController(PostService postService, PostView postView) {
        this.postService = postService;
        this.postView = postView;
    }

    public void getPosts() {
        Call<List<Post>> call = postService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    postView.displayPosts(posts);
                } else {
                    postView.displayError("Erro ao obter os posts.");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postView.displayError("Erro de rede: " + t.getMessage());
            }
        });
    }

}
