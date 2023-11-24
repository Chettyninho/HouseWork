package com.example.myapplicationandstudio;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
                params.setMargins(0, 6, 0, 18); // Establece el valor deseado para el margen inferior
                taskTextView.setLayoutParams(params);
// Guardar la descripción como una propiedad del TextView
                taskTextView.setTag(descripcion);

// Agrega el OnClickListener y el OnTouchListener a taskTextView
                taskTextView.setOnTouchListener(new View.OnTouchListener() {
                    // Declaramos el valor de initial x en un primer momento
                    float initialX = 0;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                // Guarda la posición inicial al tocar la pantalla
                                initialX = event.getX();
                                break;
                            case MotionEvent.ACTION_UP:
                                // Compara la posición inicial y final para determinar el gesto
                                float finalX = event.getX();
                                // Diferencia entre la posición inicial y final
                                float deltaX = finalX - initialX;

                                if (deltaX > 100) {
                                    // Deslizamiento hacia la derecha
                                    // Lógica para marcar la tarea como realizada en la bbdd
                                    Toast.makeText(ShowTareas.this, "Tarea realizada", Toast.LENGTH_SHORT).show();

                                    // Animación de desplazamiento
                                    ObjectAnimator animator = ObjectAnimator.ofFloat(taskTextView, "translationX", 0f, 200f);
                                    animator.setDuration(500); // Duración de la animación en milisegundos
                                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                        @Override
                                        public void onAnimationUpdate(ValueAnimator animation) {
                                            // Este método se llama en cada fotograma de la animación
                                            // Puedes realizar acciones adicionales aquí si es necesario
                                        }
                                    });
                                    animator.start();
                                } else {
                                    // Si no hubo deslizamiento, realiza la lógica de onClick
                                    // Si el texto actual es el nombre, cambiar a nombre + descripción
                                    if (taskTextView.getText().equals(nombre)) {
                                        taskTextView.setText(nombre + "\nDescripción: " + descripcion);
                                    } else {
                                        // Si el texto actual ya incluye la descripción, cambiar a solo nombre
                                        taskTextView.setText(nombre);
                                    }
                                }
                                break;
                        }
                        return true;
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
