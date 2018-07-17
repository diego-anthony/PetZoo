package com.petzoo.petzoo.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.petzoo.petzoo.constants.PreferencesFileConstants;

public class PreferencesHelper {
    private SharedPreferences app_prefs;
    public PreferencesHelper(Context context)
    {
        app_prefs = context.getSharedPreferences(PreferencesFileConstants.PREF_NAME,Context.MODE_PRIVATE);
    }

    public void putIsLogin(boolean value){
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putBoolean(PreferencesFileConstants.IS_LOGIN,value);
        editor.commit();
    }

    public boolean getIsLogin(){
        return app_prefs.getBoolean(PreferencesFileConstants.IS_LOGIN,false);
    }

    public void putName(String value){
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putString(PreferencesFileConstants.USER_NAME,value);
        editor.commit();
    }

    public String getName(){
        return app_prefs.getString(PreferencesFileConstants.USER_NAME,"");
    }

    public void putEmail(String value){
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putString(PreferencesFileConstants.USER_EMAIL,value);
        editor.commit();
    }

    public String getEmail(){
        return app_prefs.getString(PreferencesFileConstants.USER_EMAIL,"");
    }
}

