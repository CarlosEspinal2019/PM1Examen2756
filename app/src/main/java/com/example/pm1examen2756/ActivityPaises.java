package com.example.pm1examen2756;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm1examen2756.clases.AlertDialogo;
import com.example.pm1examen2756.configuraciones.SQLiteConexion;
import com.example.pm1examen2756.configuraciones.TransaccionesPaises;

public class ActivityPaises extends AppCompatActivity {
    EditText idPais, nombrePais, codigoPais;
    Button btnGuardarPaises;
    Button AGPbtnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises);
        idPais = (EditText) findViewById(R.id.ap_txtId);
        nombrePais = (EditText) findViewById(R.id.ap_txtpais1);
        codigoPais = (EditText) findViewById(R.id.ap_txtcodigopais);

        btnGuardarPaises = (Button) findViewById(R.id.ap_btnRegistrarPais);
        btnGuardarPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nombrePais.getText().toString().isEmpty() && !codigoPais.getText().toString().isEmpty())
                {
                    AgregarPaises();
                }
                else{
                    Mensaje();
                }
            }
        });

        AGPbtnRegresar = (Button) findViewById(R.id.ap_btnRegresar);
        AGPbtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPaises.this, ActivityContactoGuardar.class);
                startActivity(intent);
            }
        });
    }

    private void Mensaje() {

        AlertDialogo alerta = new AlertDialogo();
        alerta.show(getSupportFragmentManager(),"Mensaje");
    }

    private void AgregarPaises() {
        SQLiteConexion conexion = new SQLiteConexion(this, TransaccionesPaises.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(TransaccionesPaises.nombrepais, nombrePais.getText().toString());
        valores.put(TransaccionesPaises.codigopais, codigoPais.getText().toString());

        Long resultado = db.insert(TransaccionesPaises.tablapaises, TransaccionesPaises.idpais, valores);

        Toast.makeText(getApplicationContext(),
                "Registro ingresado con exito!! Codigo " + resultado.toString(),
                Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();
    }

    private void LimpiarPantalla() {
        idPais.setText("");
        nombrePais.setText("");
        codigoPais.setText("");
    }
}