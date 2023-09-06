package com.barreto.listpost.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barreto.listpost.R;
import com.barreto.listpost.model.Post;

import java.util.List;

public class PostAdapter extends  RecyclerView.Adapter<PostAdapter.PostViewHolder>{

private List<Post> posts;

public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
        }

@NonNull
@Override
public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false);
        return new PostViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.postTitle.setText(post.getTitle());
        holder.postBody.setText(post.getBody());
        }

@Override
public int getItemCount() {
        return posts != null ? posts.size() : 0;
        }

static class PostViewHolder extends RecyclerView.ViewHolder {
    TextView postTitle;
    TextView postBody;

    PostViewHolder(@NonNull View itemView) {
        super(itemView);
        postTitle = itemView.findViewById(R.id.postTitle);
        postBody = itemView.findViewById(R.id.postBody);
    }
}
}
