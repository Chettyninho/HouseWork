package com.example.myapplicationandstudio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class CrearTarea extends AppCompatActivity {
    private DatePicker fechaLimitePicker;
    private ImageButton fechaLimiteIcon;
    private TextView fechaSeleccionada;
    private boolean bull = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);
        // Obtén referencias a los elementos de tu diseño
        fechaLimitePicker = findViewById(R.id.fechaLimitePicker);
        fechaLimiteIcon = findViewById(R.id.fechaLimiteIcon);
        fechaSeleccionada = findViewById(R.id.fechaSeleccionada);
        Button botonGuardar = findViewById(R.id.botonGuardar);

        //OnClickListener en el ImageButton
        fechaLimiteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Muestra u oculta el DatePicker según el estado actual de bull
                if (bull == false) {
                    fechaLimitePicker.setVisibility(View.VISIBLE);
                    bull = true;
                } else {
                    fechaLimitePicker.setVisibility(View.INVISIBLE);
                    bull = false;
                }
            }
        });

        // Inicializa el DatePicker
        fechaLimitePicker.init(
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Actualizar el texto en el EditText con la fecha seleccionada
                        actualizarFechaSeleccionada(year, monthOfYear, dayOfMonth);

                        // Ocultar el DatePicker después de seleccionar la fecha
                        fechaLimitePicker.setVisibility(View.INVISIBLE);
                    }
                });
// Establece un OnClickListener en el botón para guardar en la base de datos
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llama a la función para guardar en la base de datos
                guardarEnBaseDeDatos();
            }
        });
    }

    private void guardarEnBaseDeDatos() {
        // Obtiene los datos que deseas guardar
        String nombre = ((EditText) findViewById(R.id.tituloTarea)).getText().toString();
        String descripcion = ((EditText) findViewById(R.id.descripcionTarea)).getText().toString();
        String fecha = fechaSeleccionada.getText().toString();
        int estado = 0;

        // Crea un objeto ContentValues para almacenar los datos
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        values.put("fechaRealizacion", fecha);
        values.put("estado", estado); // Estado por defecto es false (0)

        dataBase aux = new dataBase(CrearTarea.this);
        // Abre la base de datos para escritura
        SQLiteDatabase db = aux.getWritableDatabase();
try{
        // Inserta la nueva tarea en la base de datos
        long newRowId = db.insert("TAREAS", null, values);

        // Verifica si la inserción fue exitosa
        if (newRowId != -1) {
            // Tarea insertada con éxito
            mostrarToast("Tarea insertada con éxito, ID: " + newRowId);
            // Realiza otras acciones o muestra mensajes según sea necesario
        } else {
            // Error al insertar la tarea
            mostrarToast("Error al insertar la tarea en la base de datos");
            Log.d("DEBUG", "Nombre: " + nombre + ", Descripción: " + descripcion + ", Fecha: " + fecha + ", Estado: " + estado);
            Log.e("ERROR_DDBB", "Error al insertar la tarea en la base de datos. Fila ID: " + newRowId);

            // Muestra mensajes de error o realiza acciones adicionales en caso de fallo
        }
} catch (SQLiteException e) {
    Log.e("SQL_ERROR", "Error al insertar en la base de datos: " + e.getMessage());
}
        // Cierra la base de datos
        db.close();
    }

    // Función auxiliar para mostrar Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void actualizarFechaSeleccionada(int year, int month, int day) {
        // Suma 1 al mes ya que en DatePicker los meses se cuentan desde cero
        month = month + 1;

        // Formatea la fecha con dos dígitos para el día y el mes
        String formattedDay = String.format(Locale.getDefault(), "%02d", day);
        String formattedMonth = String.format(Locale.getDefault(), "%02d", month);

        // Formatea la fecha en el formato deseado
        String fechaFormateada = formattedDay + "/" + formattedMonth + "/" + year;

        // Establece el texto en el TextView
        fechaSeleccionada.setText(fechaFormateada);
    }


}