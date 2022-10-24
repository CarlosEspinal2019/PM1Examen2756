package com.example.pm1examen2756;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pm1examen2756.configuraciones.SQLiteConexion;
import com.example.pm1examen2756.configuraciones.TransaccionesContactos;
import com.example.pm1examen2756.tablas.Contactos;

import java.util.ArrayList;

public class ActivityContactosSalvados extends AppCompatActivity {



    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Contactos> listaContactos;
    ArrayList<String> ArregloContactos;
    Button btnRegresar;
    EditText alctxtnombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_salvados);
        conexion = new SQLiteConexion(this, TransaccionesContactos.NameDatabase,null,1);
        lista = (ListView) findViewById(R.id.acs_lista);

        ObtenerListaContactos();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloContactos);
        lista.setAdapter(adp);

        //Buscar
        alctxtnombre = (EditText) findViewById(R.id.alctxtnombre);

        alctxtnombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                adp.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contactos contac = listaContactos.get(i);

                Intent intent = new Intent(ActivityContactosSalvados.this, ActivityContacto.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contacto",contac);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        btnRegresar = (Button) findViewById(R.id.acs_btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityContactosSalvados.this, ActivityContactoGuardar.class);
                startActivity(intent);
            }
        });
    }

    private void ObtenerListaContactos() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos list_perso = null;
        listaContactos = new ArrayList<Contactos>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TransaccionesContactos.tablacontactos, null);

        while (cursor.moveToNext()){
            list_perso = new Contactos();
            list_perso.setId(cursor.getInt(0));
            list_perso.setPaises(cursor.getString(1));
            list_perso.setNombres(cursor.getString(2));
            list_perso.setTelefono(cursor.getInt(3));
            list_perso.setNota(cursor.getString(4));

            listaContactos.add(list_perso);
        }
        cursor.close();

        llenalista();
    }

    private void llenalista() {
        ArregloContactos = new ArrayList<String>();
        for (int i=0; i<listaContactos.size(); i++){
            ArregloContactos.add(listaContactos.get(i).getId() +") "+
                    listaContactos.get(i).getPaises() +" | "+
                    listaContactos.get(i).getNombres() +" | "+
                    listaContactos.get(i).getTelefono() + " | "+
                    listaContactos.get(i).getNota());
        }
    }
}