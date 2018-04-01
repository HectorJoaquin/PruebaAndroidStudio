package com.example.norgan.miapplicacion01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.norgan.miapplicacion01.utilidades.Utilidades;

public class RegistrarActivity extends AppCompatActivity {

    EditText idPersona,nombrePersona,telefonoPersona;
    Button btnRegistarPersona,btnCancelarPersona;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


        idPersona = (EditText)findViewById(R.id.idPersona);
        nombrePersona = (EditText)findViewById(R.id.nombrePersona);
        telefonoPersona = (EditText)findViewById(R.id.telefonoPersona);
        btnRegistarPersona = (Button)findViewById(R.id.btnRegistarPersona);
        btnCancelarPersona = (Button)findViewById(R.id.btnCancelarPersona);


        btnCancelarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retro = new Intent(RegistrarActivity.this,PrincipalActivity.class);
                startActivity(retro);

            }
        });



        btnRegistarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regitrarUsuario();


            }
        });

    }

    private void regitrarUsuario() {





        ConexionSQLiteHelper conn =  new ConexionSQLiteHelper(this,"bd_usuarioss",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(Utilidades.CAMPO_ID,idPersona.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,nombrePersona.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,telefonoPersona.getText().toString());

        Long idResultado = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);
        Toast.makeText(this, "ID registardo"+idResultado, Toast.LENGTH_SHORT).show();

        limpiar1();

    }

    private void limpiar1() {

        idPersona.setText(" ");
        nombrePersona.setText(" ");
        telefonoPersona.setText(" ");
    }

}
