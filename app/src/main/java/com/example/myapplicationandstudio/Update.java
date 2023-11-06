package com.example.myapplicationandstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class Update extends AppCompatActivity {
    // Recupera el Intent que inició esta actividad
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update); // Reemplaza 'activity_update' con el nombre de tu archivo XML

        // Recupera el valor del correo electrónico del Intent
        intent = getIntent();
        String email = intent.getStringExtra("email");

        // Accede a las vistas (EditText y Button) por sus IDs
        EditText inputName = findViewById(R.id.inputName);
        EditText inputUserName = findViewById(R.id.inputUserName);
        EditText inputPassword = findViewById(R.id.inputPassword);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputSurname = findViewById(R.id.inputSurname);

        SQLiteDatabase db = new dataBase(this).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE email = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String userName = cursor.getString(4);
            String password = cursor.getString(5);

            inputName.setText(name);
            inputSurname.setText(surname);
            inputEmail.setText(email);
            inputUserName.setText(userName);
            inputPassword.setText(password);
        }

        cursor.close();
    }
    //@Override
    //protected void onDestroy() {
       // super.onDestroy();
        //if (db != null) {
           // db.close();
        //}


        // Accede al botón por su ID
        //Button buttonSave = findViewById(R.id.buttonSave);

        // Define el comportamiento cuando se hace clic en el botón Guardar
        //que viene a ser enviar a la bbdd

    }
