package com.petzoo.petzoo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.petzoo.petzoo.constants.DbConstants;
import com.petzoo.petzoo.models.Usuario;

public class DBAdapter {
        private DataBaseHelper dataBaseHelper;
        private SQLiteDatabase db;
        public DBAdapter(Context context){
            dataBaseHelper = new DataBaseHelper(context);
        }

        public DBAdapter open() {
            try{
                db = dataBaseHelper.getWritableDatabase();
            }
            catch (SQLiteException e)
            {
                String error = e.getMessage().toString();
                Log.i("ERROR: ",error);
            }
            return this;
        }

        public boolean isCreated(){
            return (db != null);
        }

        public boolean isOpen(){
            return (db == null);
        }

        public void close(){
            dataBaseHelper.close();
            db.close();
        }

        public void createUser(Usuario user) {
            ContentValues values = new ContentValues();
            values.put(DbConstants.KEY_CODIGO_USUARIO, user.getIdUsuario());
            values.put(DbConstants.KEY_USER_NAME, user.getUsername());
            values.put(DbConstants.KEY_PASSWORD, user.getPassword());
            values.put(DbConstants.KEY_ID_PERSONA, user.getIdPersona());
            values.put(DbConstants.KEY_APELLIDO_MATERNO, user.getApellidoMaterno());
            values.put(DbConstants.KEY_APELLIDO_PATERNO, user.getApellidoPaterno());
            values.put(DbConstants.KEY_CELULAR, user.getCelular());
            values.put(DbConstants.KEY_CORREO_ELECTRONICO, user.getCorreoElectronico());
            values.put(DbConstants.KEY_NAME, user.getNombre());
            db.insert(DbConstants.TABLE_USER, null, values);
        }
        public void deteleUser() {
            db.delete(DbConstants.TABLE_USER, null,null);
        }

        public Usuario getUser(){
            String select = "SELECT * FROM "+ DbConstants.TABLE_USER;
            Usuario user = null;

            Cursor cursor = db.rawQuery(select,null);

            if (cursor != null && cursor.getCount() > 0){
                cursor.moveToLast();
                /*
                user = new Usuario();
                user.setIdUsuario(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setIdPersona(cursor.getInt(3));
                user.setApellidoMaterno(cursor.getString(4));
                user.setApellidoPaterno(cursor.getString(5));
                user.setCelular(cursor.getString(6));
                user.setCorreoElectronico(cursor.getString(7));
                user.setNombre(cursor.getString(8));
                */
                cursor.close();
            }
            return user;
        }
    }
