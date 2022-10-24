package com.example.pm1examen2756;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pm1examen2756.configuraciones.SQLiteConexion;
import com.example.pm1examen2756.configuraciones.TransaccionesContactos;
import com.example.pm1examen2756.tablas.Contactos;


public class ActivityContacto extends AppCompatActivity {


    TextView codigo;
    EditText pais, nombre, telefono, nota, num, nom;
    Button btnActualiza;
    Button btnElimi;
    Button btnLLamar;
    Button btnRegres;
    Button btnCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        codigo = (TextView) findViewById(R.id.ac_txtcontacto2);
        pais = (EditText) findViewById(R.id.ac_txtpais1);
        nombre = (EditText) findViewById(R.id.ac_txtnombre1);
        telefono = (EditText) findViewById(R.id.ac_txttelefono1);
        nota = (EditText) findViewById(R.id.ac_txtnota1);

        btnActualiza = (Button) findViewById(R.id.ac_btnregresar);

        Bundle obj = getIntent().getExtras();

        Contactos conta = null;

        if (obj != null) {
            conta = (Contactos) obj.getSerializable("contacto");

            codigo.setText(conta.getId().toString());
            pais.setText(conta.getPaises().toString());
            nombre.setText(conta.getNombres().toString());
            telefono.setText(conta.getTelefono().toString());
            nota.setText(conta.getNota().toString());
        }

        btnActualiza = (Button) findViewById(R.id.ac_btnactualizar);
        btnActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModificarPersonas();
            }
        });

        btnElimi = (Button) findViewById(R.id.ac_btneliminar);
        btnElimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarContactos();
            }
        });

        btnCompartir = (Button) findViewById(R.id.btnCompartir);
        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                compartirContacto();
            }
        });

        btnRegres = (Button) findViewById(R.id.ac_btnregresar);
        btnRegres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityContacto.this, ActivityContactosSalvados.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        btnLLamar = findViewById(R.id.ac_btnllamar);
        num = findViewById(R.id.ac_txttelefono1);
        nom = findViewById(R.id.ac_txtnombre1);

        btnLLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityContacto.this);
                builder.setMessage("¿Llamar a " + nom.getText().toString() + "?")
                        .setTitle("Acción");

                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num.getText().toString()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void ModificarPersonas() {
        SQLiteConexion conexion = new SQLiteConexion(this, TransaccionesContactos.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String cod = codigo.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(TransaccionesContactos.pais, pais.getText().toString());
        valores.put(TransaccionesContactos.nombres, nombre.getText().toString());
        valores.put(TransaccionesContactos.telefono, telefono.getText().toString());
        valores.put(TransaccionesContactos.nota, nota.getText().toString());

        if (!codigo.getText().toString().isEmpty()){
            db.update("contactos", valores, "id=" + cod, null);
            Toast.makeText(this, "Se Actualizo el Registro: " +cod, Toast.LENGTH_LONG).show();
        }
    }

    private void EliminarContactos() {

        SQLiteConexion conexion = new SQLiteConexion(this, TransaccionesContactos.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String cod = codigo.getText().toString();

        db.delete("contactos", "id=" + cod, null);
        Toast.makeText(this, "Regristo " + cod + " Eliminado Correctamente", Toast.LENGTH_LONG).show();

        db.close();
        LimpiarPantalla();

        Intent intent = new Intent(ActivityContacto.this, ActivityContactosSalvados.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    private void compartirContacto ()
    {
        SQLiteConexion conexion = new SQLiteConexion(this, TransaccionesContactos.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String nom = nombre.getText().toString();
        String num = telefono.getText().toString();

        String cod = codigo.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Aqui te envio el contacto" +" "+ nom +" "+ num);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }

    private void LimpiarPantalla() {
        codigo.setText("");
        pais.setText("");
        nombre.setText("");
        telefono.setText("");
        nota.setText("");
    }


}