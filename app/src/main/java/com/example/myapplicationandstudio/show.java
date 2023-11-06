package com.example.myapplicationandstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        showElements();
    }

    /*public void showElements(){
        SQLiteDatabase db = new dataBase(this).getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * FROM USERS", null);
        //TextView showNameView = findViewById(R.id.textViewShow);
        LinearLayout layout = findViewById(R.id.fillContent);

        //funcion para ver si la tabla contiene algo
        if(cursor.moveToFirst()){
            StringBuilder builder = new StringBuilder();
            do {

                String email = cursor.getString(1);
                builder.append("Email: "+email).append("\n");
                //showNameView.setText(builder);

                TextView data = new TextView(this);
                data.setText("Nombre: " + email );
                layout.addView(data);

            }while (cursor.moveToNext());

        }
        db.close();
    }*/

    public void showElements(){
        SQLiteDatabase db = new dataBase(this).getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
        LinearLayout layout = findViewById(R.id.fillContent);

        // Función para ver si la tabla contiene algo
        if(cursor.moveToFirst()){
            do {
                // Obtén el valor del correo electrónico
                final String nombre = cursor.getString(1);
                final String email = cursor.getString(3);


                // Crea un nuevo LinearLayout para cada registro
                LinearLayout recordLayout = new LinearLayout(this);
                recordLayout.setOrientation(LinearLayout.HORIZONTAL);

                // Crea un TextView para mostrar el email
                TextView data = new TextView(this);
                data.setText("Nombre: " + nombre);

                // Crea un botón para cada registro
                Button buttonUpdate = new Button(this);
                buttonUpdate.setText("Editar"); // Puedes personalizar el texto del botón
                Button delete = new Button(this);
                delete.setText("Eliminar"); // Puedes personalizar el texto del botón


                // Configura un OnClickListener para el boton update
                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        // Crea un Intent para abrir la pantalla de actualización
                        Intent intent = new Intent(show.this, Update.class);

                        // Pasa el valor del correo electrónico como un extra al Intent
                        intent.putExtra("email", email);

                        // Inicia la actividad de actualización
                        startActivity(intent);
                    }


                });


                // Configura un OnClickListener para el boton delete
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Elimina el usuario de la base de datos
                        SQLiteDatabase deleteDb = new dataBase(show.this).getWritableDatabase();
                        //deleteDb.delete("USERS", "email = ?", new String[]{String.valueOf(email)});

                        deleteDb.execSQL("delete from users where email = '" + email + "';");
                        deleteDb.close();
                        // Elimina la vista del registro de la interfaz de usuario
                        layout.removeView(recordLayout);

                        Toast.makeText(show.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    }
                });

                // Agrega el TextView y el botón al LinearLayout del registro
                recordLayout.addView(data);
                recordLayout.addView(buttonUpdate);
                recordLayout.addView(delete);

                // Agrega el LinearLayout del registro al contenedor
                layout.addView(recordLayout);

            } while (cursor.moveToNext());
        }
        db.close();
    }

    public void changeToMain(View view) {
        Intent nIntent = new Intent(show.this, MainActivity.class);
        startActivity(nIntent);
    }
}