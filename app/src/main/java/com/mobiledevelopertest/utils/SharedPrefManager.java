package com.mobiledevelopertest.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by user on 23-07-2020.
 */


public class SharedPrefManager {
    private static SharedPrefManager instance;

    private static Context ctx;

    private static final String SHARED_PREF_NAME = "sharedpref";
    public static final String KEY_COUNTER = "count";

    private SharedPrefManager(Context context) {
        ctx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public boolean write(String key, int value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);

        editor.apply();
        return true;

    }

    public static int read(String key, int defValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);
    }
}
