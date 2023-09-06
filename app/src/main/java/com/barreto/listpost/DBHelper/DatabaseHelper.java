package com.barreto.listpost.DBHelper;

import static android.app.DownloadManager.COLUMN_ID;
import static com.barreto.listpost.DBHelper.PostContract.PostEntry.COLUMN_BODY;
import static com.barreto.listpost.DBHelper.PostContract.PostEntry.COLUMN_TITLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.barreto.listpost.model.Post;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "posts.db";
    private static final int DATABASE_VERSION = 1;
    //public static final String TABLE_POSTS = "posts";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public long addPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, post.getTitle());
        values.put(COLUMN_BODY, post.getBody());
        long newRowId = db.insert(PostContract.PostEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    private SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                PostContract.PostEntry._ID,
                COLUMN_TITLE,
                COLUMN_BODY
        };
        Cursor cursor = db.query(
                PostContract.PostEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(PostContract.PostEntry._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String body = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY));
                Post post = new Post(id, title, body);
                posts.add(post);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return posts;
    }

    private SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public int updatePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, post.getTitle());
        values.put(COLUMN_BODY, post.getBody());
        String selection = PostContract.PostEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(post.getId()) };
        int count = db.update(
                PostContract.PostEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        db.close();
        return count;
    }
    public void deletePost(int postId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = PostContract.PostEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(postId) };
        db.delete(PostContract.PostEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }
    public Post getPostById(int postId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                COLUMN_TITLE,
                COLUMN_BODY
        };
        String selection = PostContract.PostEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(postId) };
        Cursor cursor = db.query(
                PostContract.PostEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Post post = null;
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String body = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY));
            post = new Post(postId, title, body);
        }

        cursor.close();
        db.close();
        return post;
    }

}
