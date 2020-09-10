package com.example.sub2_rahmatsaputra.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sub2_rahmatsaputra.helper.GlobalVariable;

public class BasePreferences {
    private SharedPreferences sharedPreferences;

    public BasePreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(BaseConfig.PREF_RAHMAT_SAPUTRA, Context.MODE_PRIVATE);
    }

    public String getUserName() {
        return sharedPreferences.getString(GlobalVariable.USER_NAME, "");
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GlobalVariable.USER_NAME, userName);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString(BaseConfig.PREF_NAME, "");
    }

    public void setName(String phone) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BaseConfig.PREF_NAME, phone);
        editor.apply();
    }

    public int getFollowers() {
        return sharedPreferences.getInt(BaseConfig.PREF_FOLLOWERS, 0);
    }

    public void setFollowers(int followers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(BaseConfig.PREF_FOLLOWERS, followers);
        editor.apply();
    }

    public int getFollowing() {
        return sharedPreferences.getInt(BaseConfig.PREF_FOLLOWING, 0);
    }

    public void setFollowing(int following) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(BaseConfig.PREF_FOLLOWING, following);
        editor.apply();
    }
}
