package com.example.myapplicationandstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void insertValue(View v) {

        EditText nameEditText = findViewById(R.id.inputName);
        EditText surnameEditText = findViewById(R.id.inputSurname);
        EditText emailEditText = findViewById(R.id.inputEmail);
        EditText userNameEditText = findViewById(R.id.inputUserName);
        EditText passwordEditText = findViewById(R.id.inputPassword);


        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        dataBase aux = new dataBase(Registro.this);
        SQLiteDatabase db = aux.getWritableDatabase();

        if (db != null && (!name.isEmpty() || !surname.isEmpty() || !email.isEmpty() || !userName.isEmpty() || !password.isEmpty())) {
            ContentValues values = new ContentValues();

            values.put("name", name);
            values.put("surName", surname);
            values.put("email", email);
            values.put("userName", userName);
            values.put("password", password);

            long res = db.insert("USERS", null, values);
            if (res >= 0) {
                Toast.makeText(this, "INSERTADO TODO OK", Toast.LENGTH_LONG).show();
                nameEditText.setText("");
                surnameEditText.setText("");
                emailEditText.setText("");
                userNameEditText.setText("");
                passwordEditText.setText("");

            } else {
                Toast.makeText(this, "FALLO AL INSERTAR", Toast.LENGTH_LONG).show();
            }


        }
    }
}