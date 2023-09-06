package com.barreto.listpost.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.barreto.listpost.Api.PostService;
import com.barreto.listpost.Api.PostView;
import com.barreto.listpost.R;
import com.barreto.listpost.adapter.PostAdapter;
import com.barreto.listpost.controller.PostController;
import com.barreto.listpost.model.Post;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PostView {

    private PostController postController;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter();
        recyclerView.setAdapter(postAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostService postService = retrofit.create(PostService.class);
        postController = new PostController(postService, this);

        postController.getPosts();
    }

    @Override
    public void displayPosts(List<Post> posts) {
        postAdapter.setPosts(posts);
    }

    @Override
    public void displayError(String errorMessage) {
        // Exibir uma mensagem de erro na UI
    }
}