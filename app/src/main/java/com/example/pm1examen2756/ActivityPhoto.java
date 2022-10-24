package com.example.pm1examen2756;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pm1examen2756.configuraciones.SQLiteConexion;
import com.example.pm1examen2756.configuraciones.TransaccionesContactos;

import java.io.ByteArrayInputStream;

public class ActivityPhoto extends AppCompatActivity {

    SQLiteConexion conexion = new SQLiteConexion(this, TransaccionesContactos.NameDatabase, null, 1);
    ImageView imageViewFoto;
    Button btnAtras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        btnAtras = (Button) findViewById(R.id.btnavfAtras);

        imageViewFoto = (ImageView) findViewById(R.id.imgViewVerFoto);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityContacto.class);
                startActivity(intent);
            }
        });
        /*
        Toast.makeText(getApplicationContext(), getIntent().getStringExtra("codigoParaFoto")+" "
                ,Toast.LENGTH_LONG).show();*/

        Bitmap recuperarFoto = buscarImagen(getIntent().getStringExtra("codigoParaFoto"));
        imageViewFoto.setImageBitmap(recuperarFoto);


    }

    public Bitmap buscarImagen(String id) {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String sql = "SELECT foto FROM contactos WHERE id =" + id;
        Cursor cursor = db.rawQuery(sql, new String[] {});
        Bitmap bitmap = null;
        if(cursor.moveToFirst()){
            byte[] blob = cursor.getBlob(0);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            bitmap = BitmapFactory.decodeStream(bais);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return bitmap;
    }
}