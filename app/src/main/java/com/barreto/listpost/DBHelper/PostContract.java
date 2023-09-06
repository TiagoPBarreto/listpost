package com.barreto.listpost.DBHelper;

import android.provider.BaseColumns;

public class PostContract {
    private PostContract() {}

    public static class PostEntry implements BaseColumns {
        public static final String TABLE_NAME = "posts";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_BODY = "body";
    }
}
