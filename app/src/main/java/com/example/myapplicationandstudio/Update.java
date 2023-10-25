package com.example.myapplicationandstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    EditText editTextName;
    EditText editTextEmail;
    Button buttonSave;
    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Inicializa los elementos de la interfaz
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Recibe el valor del correo electrónico pasado desde la actividad anterior
        String email = getIntent().getStringExtra("email");

        // Muestra el correo electrónico en el campo de Email
        editTextEmail.setHint("Email: " + email);

        // Configura un OnClickListener para el botón "Guardar Cambios"
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obten los valores editados desde los EditText
                String newName = editTextName.getText().toString();
                String newEmail = editTextEmail.getText().toString();

                // Aquí debes implementar la lógica para actualizar los datos en la base de datos
                // Utiliza newName y newEmail para realizar la actualización
                // Por ejemplo, puedes usar una función para actualizar los datos en tu base de datos

                // Después de la actualización, puedes cerrar la actividad actual
                finish();
            }
        });

        // Configura un OnClickListener para el botón "Cancelar"
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si el usuario hace clic en "Cancelar," simplemente cierra la actividad actual
                finish();
            }
        });
    }


}