package com.example.myapplicationandstudio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowTareas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tareas);
        showElements();
    }

    public void showElements() {
        SQLiteDatabase db = new dataBase(this).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS", null);
        LinearLayout layout = findViewById(R.id.fillContent);

        // Función para ver si la tabla contiene algo
        if (cursor.moveToFirst()) {
            do {
                // Obtén el valor del nombre de la tarea y la descripción
                final String nombre = cursor.getString(1);
                final String descripcion = cursor.getString(2);

                // Crea un nuevo TextView para cada tarea
                TextView taskTextView = new TextView(this);
                taskTextView.setText("Nombre: " + nombre);

                // Crea un nuevo TextView para cada descripción
                final TextView descTextView = new TextView(this);
                descTextView.setText("Descripción: " + descripcion);
                descTextView.setVisibility(View.GONE); // Establece inicialmente la visibilidad en GONE

                // Agrega el OnClickListener a taskTextView para alternar la visibilidad de descTextView
                taskTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (descTextView.getVisibility() == View.VISIBLE) {
                            descTextView.setVisibility(View.GONE);
                        } else {
                            descTextView.setVisibility(View.VISIBLE);
                        }
                    }
                });

                // Agrega los TextView al LinearLayout
                layout.addView(taskTextView);
                layout.addView(descTextView);

            } while (cursor.moveToNext());
        }
        db.close();
    }
}
