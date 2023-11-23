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
                final TextView taskTextView = new TextView(this);
                applyTaskTextViewStyle(taskTextView, nombre); // Aplicar estilo y texto al TextView

                // Agrega un margen inferior para separación entre contenedores
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) taskTextView.getLayoutParams();
                params.setMargins(0, 0, 0, 16); // Establece el valor deseado para el margen inferior
                taskTextView.setLayoutParams(params);

                //taskTextView.setVisibility(View.VISIBLE);

                // Combina el nombre y la descripción usando SpannableString
               // SpannableString spannableString = new SpannableString("Nombre: " + nombre );

                // Aplica un estilo diferente a la descripción (opcional)
                //spannableString.setSpan(new StyleSpan(Typeface.ITALIC), spannableString.length() - descripcion.length(), spannableString.length(), 0);

                // Establece el texto combinado en taskTextView
                //taskTextView.setText(spannableString);

                // Crea un nuevo TextView para cada descripción
                //final TextView descTextView = new TextView(this);
                //applyDescTextViewStyle(descTextView);
                //descTextView.setText("Descripción: " + descripcion);
                //descTextView.setVisibility(View.GONE); // Establece inicialmente la visibilidad en GONE

                // Agrega el OnClickListener a taskTextView para alternar la visibilidad de descTextView
                taskTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (taskTextView.getText().equals(nombre)) {
                            // Si el texto actual es el nombre, cambiar a nombre + descripción
                            taskTextView.setText(nombre + "\nDescripción: " + descripcion);
                        } else {
                            // Si el texto actual ya incluye la descripción, cambiar a solo nombre
                            taskTextView.setText(nombre);
                        }
                    }
                });

                // Agrega los TextView al LinearLayout
                layout.addView(taskTextView);

            } while (cursor.moveToNext());
        }
        db.close();
    }


    //prueba para estilo de TaskTextView

    // Métodos para aplicar estilos específicos a los TextViews
    private void applyTaskTextViewStyle(TextView textView, String text) {
        // Establece el color de texto negro
        textView.setTextColor(getResources().getColor(android.R.color.black));

        // Establece el fondo blanco
        textView.setBackgroundColor(getResources().getColor(android.R.color.white));

        // Establece bordes naranjas y redondeados
        textView.setBackground(getRoundOrangeBorder());

        // Establece dimensiones
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Agrega un margen superior para separación entre contenedores
        params.setMargins(4, 22, 4, 4); // Establece el valor deseado para el margen superior

        textView.setLayoutParams(params);

        // Establece el texto inicial al nombre de la tarea
        textView.setText(text);

        // Otros estilos...
        // Puedes ajustar el tamaño del texto para dar más importancia al nombre
        textView.setTextSize(18); // Tamaño del texto del nombre

        //textView.setPadding(8,12,8,12);
    }

    // Método para obtener un fondo con bordes naranjas y redondeados
    private Drawable getRoundOrangeBorder() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(getResources().getColor(android.R.color.white));
        shape.setStroke(2, getResources().getColor(android.R.color.holo_orange_dark));
        shape.setCornerRadius(8);

        return shape;
    }
}
