package com.example.myapplicationandstudio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
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

                // Nuevo TextView para cada tarea
                TextView taskTextView = new TextView(this);
                applyTaskTextViewStyle(taskTextView); //aplicarEstilo al TextView
                // Combina el nombre y la descripción usando SpannableString
               // SpannableString spannableString = new SpannableString("Nombre: " + nombre );

                // Aplica un estilo diferente a la descripción (opcional)
                //spannableString.setSpan(new StyleSpan(Typeface.ITALIC), spannableString.length() - descripcion.length(), spannableString.length(), 0);

                // Establece el texto combinado en taskTextView
                //taskTextView.setText(spannableString);

                // Crea un nuevo TextView para cada descripción
                final TextView descTextView = new TextView(this);
                applyDescTextViewStyle(descTextView);
                descTextView.setText("Descripción: " + descripcion);
                descTextView.setVisibility(View.GONE); // Establece inicialmente la visibilidad en GONE

                // Agrega el OnClickListener a taskTextView para alternar la visibilidad de descTextView
                taskTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (descTextView.getVisibility() == View.VISIBLE) {
                            // Si la descripción está visible, la ocultamos
                            descTextView.setVisibility(View.GONE);
                            // Mostramos solo el nombre en taskTextView
                            taskTextView.setText("Nombre: " + nombre);
                        } else {
                            // Si la descripción no está visible, la mostramos
                            descTextView.setVisibility(View.VISIBLE);
                            // Mostramos el nombre y la descripción en taskTextView
                            taskTextView.setText("Nombre: " + nombre + "\nDescripción: " + descripcion);
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


    //prueba para estilo de TaskTextView

    // Métodos para aplicar estilos específicos a los TextViews
    private void applyTaskTextViewStyle(TextView textView) {
        // Establece el color de texto negro
        textView.setTextColor(getResources().getColor(android.R.color.black));

        // Establece el fondo blanco
        textView.setBackgroundColor(getResources().getColor(android.R.color.white));

        // Establece bordes naranjas y redondeados
        textView.setBackground(getRoundOrangeBorder());

        // Otros estilos...
        textView.setTextSize(22);
        //textView.setPadding(8,12,8,12);
    }

    private void applyDescTextViewStyle(TextView textView) {
        // Establece el color de texto negro
        textView.setTextColor(getResources().getColor(android.R.color.black));

        // Establece el fondo blanco
        textView.setBackgroundColor(getResources().getColor(android.R.color.white));

        // Establece bordes naranjas y redondeados
        textView.setBackground(getRoundOrangeBorder());

        // Otros estilos...
        textView.setTextSize(16);
    }

    // Método para obtener un fondo con bordes naranjas y redondeados GRACIAS AL CHAT
    private Drawable getRoundOrangeBorder() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(getResources().getColor(android.R.color.white));
        shape.setStroke(2, getResources().getColor(android.R.color.holo_orange_dark));
        shape.setCornerRadius(8);

        return shape;
    }
}
