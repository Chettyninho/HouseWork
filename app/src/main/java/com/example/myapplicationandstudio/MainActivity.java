package com.example.myapplicationandstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertValue(View v){
        TextView emailTextView = findViewById(R.id.email);
        TextView passwordTextView = findViewById(R.id.password);

        String email = emailTextView.getText().toString();
        String password = emailTextView.getText().toString();

        dataBase aux = new dataBase(MainActivity.this);
        SQLiteDatabase db = aux.getWritableDatabase();

        if (db != null && (!email.isEmpty()||!password.isEmpty()) ) {
            ContentValues values = new ContentValues();

            values.put("password", password);
            values.put("email", email);

            long res =db.insert("USERS", null, values);
            if (res >= 0){
                Toast.makeText(this,"INSERTADO TODO OK", Toast.LENGTH_LONG).show();
                emailTextView.setText("");
                passwordTextView.setText("");

            }else{
                Toast.makeText(this,"FALLO AL INSERTAR", Toast.LENGTH_LONG).show();
            }


        }

        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        Map<String, String> users = new HashMap<>();
        users.put("mail", email);
        users.put("password",password);

        firestoreDb.collection("HouseWork").document("users")
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
    }

    public void changeToShow(View view){
        Intent nIntent = new Intent(MainActivity.this, show.class);
        startActivity(nIntent);
    }
    public void changeToUpdate(View view){
        Intent nIntent = new Intent(MainActivity.this, Update.class);
        startActivity(nIntent);
    }


}