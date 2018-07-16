package com.petzoo.petzoo.db;

import android.content.Context;

import com.petzoo.petzoo.models.Usuario;

public class DBHelper {
    private DBAdapter dbAdapter;

    public DBHelper(Context context) {
        dbAdapter = new DBAdapter(context);
        dbAdapter.open();
        dbAdapter.close();
    }

    public void createUser(Usuario user){
        dbAdapter.open();
        dbAdapter.createUser(user);
        dbAdapter.close();
    }

    public Usuario getUser(){
        dbAdapter.open();
        Usuario user = dbAdapter.getUser();
        dbAdapter.close();
        return user;
    }

    public void deleteUser(){
        dbAdapter.open();
        dbAdapter.deteleUser();
        dbAdapter.close();
    }

    public boolean isCreated(){
        return dbAdapter.isCreated();
    }

    public boolean isOpen(){
        return  dbAdapter.isOpen();
    }
}
