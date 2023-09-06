package com.barreto.listpost.Api;

import com.barreto.listpost.model.Post;

import java.util.List;

public interface PostView {

    void displayPosts(List<Post> posts);
    void displayError(String message);
}
