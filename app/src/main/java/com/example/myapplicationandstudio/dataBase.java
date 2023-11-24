package com.example.myapplicationandstudio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dataBase extends SQLiteOpenHelper {
    private static final String NAME_DB = "HOUSE_WORKS";
    private static final int VERSION_DB = 2;



    //CONSTRUCTORz
    public dataBase(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] tablas = {
                "CREATE TABLE USERS" +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name varchar(45) NOT NULL," +
                        "surname VARCHAR(45) NOT NULL," +
                        "email varchar(45) NOT NULL," +
                        "userName varchar(45) NOT NULL," +
                        "password  varchar(45) NOT NULL)",
                "CREATE TABLE TAREAS (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT NOT NULL, " +
                        "descripcion TEXT NOT NULL, " +
                        "fechaRealizacion DATE NOT NULL, " +
                        "estado INTEGER NOT NULL)"
        };
        for (String tabla : tablas) {
            db.execSQL(tabla);
        }
        //db.execSQL("CREATE TABLE USERS" +
          //      "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            //    "name varchar(45) NOT NULL," +
              //  "surname VARCHAR(45) NOT NULL," +
                //"email varchar(45) NOT NULL," +
                //"userName varchar(45) NOT NULL," +
                //"password  varchar(45) NOT NULL)");
//tabla tareas
       // db.execSQL("CREATE TABLE TAREAS (" +
         //       "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
           //     "nombre TEXT NOT NULL, " +
              //  "descripcion TEXT NOT NULL, " +
                //"fechaRealizacion DATE NOT NULL, " +
                //"estado INTEGER NOT NULL)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
