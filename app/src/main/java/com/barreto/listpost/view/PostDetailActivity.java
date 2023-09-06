package com.barreto.listpost.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.barreto.listpost.DBHelper.DatabaseHelper;
import com.barreto.listpost.R;
import com.barreto.listpost.model.Post;

public class PostDetailActivity extends AppCompatActivity {

    private TextView detailTitle;
    private TextView detailBody;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        detailTitle = findViewById(R.id.detailTitle);
        detailBody = findViewById(R.id.detailBody);

        databaseHelper = new DatabaseHelper(this);

        int postId = getIntent().getIntExtra("post_id", -1);
        if (postId != -1) {
            Post post = databaseHelper.getPostById(postId);
            if (post != null) {
                detailTitle.setText(post.getTitle());
                detailBody.setText(post.getBody());
            }
        }
    }
}