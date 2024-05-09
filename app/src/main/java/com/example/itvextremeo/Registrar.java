package com.example.itvextremeo;

import static com.example.itvextremeo.Utils.IPEQUIPO;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Registrar extends AppCompatActivity {


    EditText nombre, apellido, telefono, correo, contrasena, repetirContrasena;
    Button registrase, cancelar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        //EditText
        nombre = findViewById(R.id.editTextReNombre);
        apellido = findViewById(R.id.editTextReApe);
        telefono = findViewById(R.id.editTextReTele);
        correo = findViewById(R.id.editTextReCorreo);
        contrasena = findViewById(R.id.editTextReContraseña);
        repetirContrasena = findViewById(R.id.editTextReContraseñaRepe);

        //Botones
        registrase = findViewById(R.id.buttonRegistrarse);
        cancelar = findViewById(R.id.buttonCancelar);


        registrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Registrar.this, MainActivity.class);
                //startActivity(intent);
                String name = nombre.getText().toString().trim();
                String ape = apellido.getText().toString();
                String tele = telefono.getText().toString().trim();
                String corre = correo.getText().toString().trim();
                String contra = contrasena.getText().toString().trim();
                String recontra = repetirContrasena.getText().toString().trim();
                String fechaActual = obtenerFechaActual();
                if(contra.equals(recontra)){
                    new AgregarUsuarioTask().execute(name, ape, tele, corre, contra, fechaActual);
                }else{
                    Toast.makeText(Registrar.this, "Contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private class AgregarUsuarioTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put("name", params[0]);
            postDataParams.put("ape", params[1]);
            postDataParams.put("tele", params[2]);
            postDataParams.put("correo", params[3]);
            postDataParams.put("pass", params[4]);
            postDataParams.put("fecha", params[5]);
            return ConexiónPHP.enviarPost(IPEQUIPO+"/registrar.php", postDataParams);
        }

        @Override
        protected void onPostExecute(String result) {
                Toast.makeText(Registrar.this, result, Toast.LENGTH_LONG).show();
                if(result.equals("Datos insertados correctamente")){

                    Intent intent = new Intent(Registrar.this, MainActivity.class);
                    startActivity(intent);
                }

        }
    }

    public static String obtenerFechaActual() {
        // Obtener la fecha actual
        Date fecha = new Date();

        // Crear un formato de fecha personalizado
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        // Formatear la fecha actual según el formato personalizado
        String fechaFormateada = formato.format(fecha);

        // Devolver la fecha formateada
        return fechaFormateada;
    }
}