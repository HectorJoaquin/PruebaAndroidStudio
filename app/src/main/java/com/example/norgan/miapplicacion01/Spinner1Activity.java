package com.example.norgan.miapplicacion01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.norgan.miapplicacion01.entidades.Usuario;
import com.example.norgan.miapplicacion01.utilidades.Utilidades;

import java.util.ArrayList;

public class Spinner1Activity extends AppCompatActivity {
    Spinner btnspinner;
    TextView idPersonaSpinner,nombrePersonaSpinner,telefonoPersonaSpinner;
    Button btnPersona;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personaList;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner1);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarioss",null,1);

         idPersonaSpinner =(TextView)findViewById(R.id.idPersonaSpinner);
         nombrePersonaSpinner =(TextView)findViewById(R.id.nombrePersonaSpinner);
         telefonoPersonaSpinner =(TextView)findViewById(R.id.telefonoPersonaSpinner);
        btnPersona= (Button)findViewById(R.id.btnPersona);
        btnspinner = (Spinner)findViewById(R.id.btnspinner);


        consultarListaPersonas();
        obtenerLista();

        ArrayAdapter<CharSequence> adaptador =  new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
        btnspinner.setAdapter(adaptador);


        btnPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent retro1 = new Intent(Spinner1Activity.this,PrincipalActivity.class);
                startActivity(retro1);
            }
        });


        btnspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    idPersonaSpinner.setText("ID  :"+personaList.get(position -1).getId().toString());
                    nombrePersonaSpinner.setText("Nombre  :"+personaList.get(position -1).getNombre());
                    telefonoPersonaSpinner.setText("Telefono  :"+personaList.get(position -1).getTelefono());
                }else{

                    idPersonaSpinner.setText("");
                    nombrePersonaSpinner.setText("");
                    telefonoPersonaSpinner.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void obtenerLista() {


        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione una Opción");
        for (int i=0; i<personaList.size(); i++){

            listaPersonas.add(personaList.get(i).getId()+"-"+personaList.get(i).getNombre()+"-"+personaList.get(i).getTelefono());
        }


    }

    private void consultarListaPersonas() {

        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario persona=null;
        personaList =new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO,null);
        while (cursor.moveToNext()){


            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));


            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Tel",persona.getTelefono());

            personaList.add(persona);

        }



    }
}
