package com.example.norgan.miapplicacion01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.norgan.miapplicacion01.utilidades.Utilidades;

public class ConsultarActivity extends AppCompatActivity {
    EditText idPersonaConsultar,nombrePersonaConsultar,telefonoPersonaConsultar;
    Button btnBuscar,btnEliminar,btnActualizar;
    ConexionSQLiteHelper conn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        idPersonaConsultar = (EditText)findViewById(R.id.idPersonaConsultar);
        nombrePersonaConsultar = (EditText)findViewById(R.id.nombrePersonaConsultar);
        telefonoPersonaConsultar = (EditText)findViewById(R.id.telefonoPersonaConsultar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarioss",null,1);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarUsuario();

            }
        });


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuario();
            }
        });



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                consultar();
            }
        });


    }

    private void eliminarUsuario() {

        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {idPersonaConsultar.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(this, "Se Elimino Correctamente", Toast.LENGTH_SHORT).show();
        db.close();
        limpiar2();


    }

    private void actualizarUsuario() {

        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {idPersonaConsultar.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,nombrePersonaConsultar.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,telefonoPersonaConsultar.getText().toString());
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(this, "Se Actualizo Correctamente", Toast.LENGTH_SHORT).show();
        db.close();
        limpiar2();



    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametros = {idPersonaConsultar.getText().toString()};
        String [] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};


        try {

            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            nombrePersonaConsultar.setText(cursor.getString(0));
            telefonoPersonaConsultar.setText(cursor.getString(1));
            cursor.close();

        }catch (Exception e){

            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_SHORT).show();
            limpiar2();
        }




    }

    private void limpiar2() {
        nombrePersonaConsultar.setText(" ");
        telefonoPersonaConsultar.setText(" ");
    }





}
