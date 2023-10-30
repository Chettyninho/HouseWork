package com.example.myapplicationandstudio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dataBase extends SQLiteOpenHelper {
    private static final String NAME_DB = "HOUSE_WORKS";
    private static final int VERSION_DB = 1;



    //CONSTRUCTOR
    public dataBase(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name varchar(45) NOT NULL," +
                "surname VARCHAR(45) NOT NULL," +
                "email varchar(45) NOT NULL," +
                "userName varchar(45) NOT NULL," +
                "password  varchar(45) NOT NULL)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
