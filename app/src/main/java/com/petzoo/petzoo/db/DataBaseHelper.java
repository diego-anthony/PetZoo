package com.petzoo.petzoo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.petzoo.petzoo.constants.DbConstants;

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String TABLE_CREATE_USER = "CREATE TABLE "+ DbConstants.TABLE_USER +" ( "
            + DbConstants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DbConstants.KEY_CODIGO_USUARIO + " INTEGER NOT NULL, "
            + DbConstants.KEY_USER_NAME + " TEXT NOT NULL, "
            + DbConstants.KEY_PASSWORD + "TEXT NOT NULL, "
            + DbConstants.KEY_ID_PERSONA + "INTEGER , "
            + DbConstants.KEY_APELLIDO_PATERNO + "TEXT , "
            + DbConstants.KEY_APELLIDO_MATERNO + "TEXT , "
            + DbConstants.KEY_CELULAR + "TEXT , "
            + DbConstants.KEY_CORREO_ELECTRONICO + "TEXT , "
            + DbConstants.KEY_NAME + "TEXT NOT NULL "
            +" ); ";

    public DataBaseHelper(Context context) {
        super(context, DbConstants.DATABASE_NAME
                , null, DbConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+DbConstants.TABLE_USER);
        db.execSQL(TABLE_CREATE_USER);
    }
}
