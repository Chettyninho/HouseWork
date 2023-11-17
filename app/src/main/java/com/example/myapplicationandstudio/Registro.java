package com.example.myapplicationandstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        /*FirebaseApp.initializeApp(this);*/

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
                Intent nIntent = new Intent(Registro.this, MainActivity.class);
                startActivity(nIntent);

                FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
                Map<String, String> users = new HashMap<>();
                users.put("name", name);
                users.put("surName",surname);
                users.put("email", email);
                users.put("userName", userName);
                users.put("password",password);

                firestoreDb.collection("HouseWorks").document("users")
                        .set(users)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("_DEBUG", "DEBUG");
                            }

                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("ERROR", e.getMessage());
                            }
                        });

            } else {
                Toast.makeText(this, "FALLO AL INSERTAR", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"db es nulo",Toast.LENGTH_LONG);
        }





    }

    public void changeToMain(View view) {
        Intent nIntent = new Intent(Registro.this, MainActivity.class);
        startActivity(nIntent);
    }
}