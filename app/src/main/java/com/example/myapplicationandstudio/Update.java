package com.example.myapplicationandstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Update extends AppCompatActivity {
    // Recupera el Intent que inició esta actividad
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update); // Reemplaza 'activity_update' con el nombre de tu archivo XML

        // Recupera el valor del correo electrónico del Intent
        intent = getIntent();
        String email = intent.getStringExtra("email");

        // Accede a las vistas (EditText y Button) por sus IDs
        EditText inputName = findViewById(R.id.inputName);
        EditText inputUserName = findViewById(R.id.inputUserName);
        EditText inputPassword = findViewById(R.id.inputPassword);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputSurname = findViewById(R.id.inputSurname);

        SQLiteDatabase db = new dataBase(this).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE email = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String userName = cursor.getString(4);
            String password = cursor.getString(5);

            inputName.setText(name);
            inputSurname.setText(surname);
            inputEmail.setText(email);
            inputUserName.setText(userName);
            inputPassword.setText(password);
        }

        cursor.close();

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos(view);
            }
        });

    }
    public void actualizarDatos(View v){
        //obtenemos el valor de los editText
        EditText name_editText = findViewById(R.id.inputName);
        String newName = name_editText.getText().toString();
        EditText  surName_editText = findViewById(R.id.inputSurname);
        String newSurname = surName_editText.getText().toString();
        EditText email_editText = findViewById(R.id.inputEmail);
        String newEmail = email_editText.getText().toString();
        EditText userName_editText = findViewById(R.id.inputUserName);
        String newUserName = userName_editText.getText().toString();
        EditText Pasword_editText = findViewById(R.id.inputPassword);
        String newPassword = Pasword_editText.getText().toString();

//no va joder mecaguen la puta .

        //consulta a la base de datos para insertar.
        dataBase aux = new dataBase(Update.this);
        SQLiteDatabase db = aux.getWritableDatabase();
        //consulta para comparar con la bbdd
        Cursor cursor = db.rawQuery("SELECT email FROM USERS", null);

        if(cursor.moveToFirst())
            do {
                if (db != null && (!newName.isEmpty() || !newSurname.isEmpty() || !newEmail.isEmpty() || !newUserName.isEmpty() || !newPassword.isEmpty())) {
                    String emailFromCursor = cursor.getString(1);
                    String updateQuery = "UPDATE USERS" +
                            " SET name = '" + newName + "'," +
                            " surName = '" + newSurname + "'," +
                            " email = '" + newEmail + "'," +
                            " userName = '" + newUserName + "'," +
                            " password = '" + newPassword + "'" +
                            " WHERE email = '" + emailFromCursor + "';";
                    db.execSQL(updateQuery);
                }
            } while (cursor.moveToNext());

        }
    }


        // Accede al botón por su ID
        //Button buttonSave = findViewById(R.id.buttonSave);

        // Define el comportamiento cuando se hace clic en el botón Guardar
        //que viene a ser enviar a la bbdd


