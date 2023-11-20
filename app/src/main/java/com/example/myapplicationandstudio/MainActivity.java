package com.example.myapplicationandstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
        registrarse();

    }

    /*public void insertValue(View v){

    /*eN VEZ DE INSERTAR VALORES, AQUI HAY QUE COMPROBAR SI COINCIDE CON LA BBDD*/
        /*PARA VER SI PUEDE O NO INICIAR SESION*/
       /* TextView emailTextView = findViewById(R.id.username);
        TextView passwordTextView = findViewById(R.id.password);

        String userName = emailTextView.getText().toString();
        String password = emailTextView.getText().toString();

        dataBase aux = new dataBase(MainActivity.this);
        SQLiteDatabase db = aux.getWritableDatabase();

        if (db != null && (!userName.isEmpty()||!password.isEmpty()) ) {
            ContentValues values = new ContentValues();

            values.put("password", password);
            values.put("userName", userName);

            long res =db.insert("USERS", null, values);
            if (res >= 0){
                Toast.makeText(this,"INSERTADO TODO OK", Toast.LENGTH_LONG).show();
                emailTextView.setText("");
                passwordTextView.setText("");

            }else{
                Toast.makeText(this,"FALLO AL INSERTAR", Toast.LENGTH_LONG).show();
            }


        }

        /*FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        Map<String, String> users = new HashMap<>();
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
    }*/

    public void changeToShow(View view){
        Intent nIntent = new Intent(MainActivity.this, show.class);
        startActivity(nIntent);
    }
    public void changeToUpdate(View view){
        Intent nIntent = new Intent(MainActivity.this, Update.class);
        startActivity(nIntent);
    }

    public void registrarse() {
        TextView textViewButton = findViewById(R.id.registerbutton);
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí puedes iniciar la nueva actividad
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });
    }


    public void login(View view) {
        EditText userEditText = findViewById(R.id.username); // Cambiado a EditText para el campo de usuario
        EditText passwordEditText = findViewById(R.id.password);

        String user = userEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        dataBase aux = new dataBase(MainActivity.this);
        SQLiteDatabase db = aux.getReadableDatabase(); // Utiliza getReadableDatabase para lectura

        if (db != null && !user.isEmpty() && !password.isEmpty()) {
            // Define la consulta SQL para buscar un usuario por email y contraseña
            //el interrogante significa que es el valor de lo que le vamos a pedir
            String query = "SELECT * FROM USERS WHERE userName = ? AND password = ?";

            // Parámetros de la consulta que se recogen del texto
            String[] selectionArgs = {user, password};
            // Ejecuta la consulta con Cursor
            Cursor cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.getCount() > 0) {
                // El usuario existe en la base de datos
                Toast.makeText(this, "Usuario existe", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ShowTareas.class);
                startActivity(intent);
            } else {
                // El usuario no existe en la base de datos
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
            }

            // Cierre cursor si encuentra coincidencia o valores que no coincidan NO NULOS
            if (cursor != null) {
                cursor.close();
            }
        }
    }



    public void crearTareas(View view) {
        Intent intent = new Intent(MainActivity.this, CrearTarea.class);
        startActivity(intent);
    }
}