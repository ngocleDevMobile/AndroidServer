package com.example.androidserver.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidserver.Models.User;
import com.google.android.gms.common.internal.Constants;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;
    private SharedPreferences mSharedPreferences;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }


    public void saveUser(User user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("_id", user.get_id());
        editor.putString("email", user.getEmail());
        editor.putString("username", user.getUsername());
        //editor.putString("school", user.getSchool());

        editor.apply();

    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("_id", String.valueOf(-1)) != String.valueOf(-1);
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("_id", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("username", null)
               // sharedPreferences.getString("school", null)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putString(Constants.EMAIL,"");
//        editor.putString(Constants.TOKEN,"");
//        editor.apply();
//        finish();
    }

}
