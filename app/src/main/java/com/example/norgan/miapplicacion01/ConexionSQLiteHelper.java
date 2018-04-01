package com.example.norgan.miapplicacion01;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.norgan.miapplicacion01.utilidades.Utilidades;

/**
 * Created by norgan on 1/04/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final  String CREAR_TABLA_USUARIO = " CREATE TABLE usuarios(id INTEGER,nombre TEXT,telefono TEXT)";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(Utilidades.CREATE_TABLA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
