package com.example.norgan.miapplicacion01;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.norgan.miapplicacion01.entidades.Usuario;
import com.example.norgan.miapplicacion01.utilidades.Utilidades;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {


    ListView listView1;
    ArrayList<Usuario> listaUsuario;
    ArrayList<String> listaInformacion;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarioss",null,1);
        listView1=(ListView)findViewById(R.id.listView1);


        consultarListaPersonas();
        obtenerListaView();



        ArrayAdapter adaptere = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listView1.setAdapter(adaptere);






    }

    private void obtenerListaView() {

        listaInformacion = new ArrayList<String>();
        for (int i = 0;i<listaUsuario.size();i++){


            listaInformacion.add(listaUsuario.get(i).getId()+"-"+listaUsuario.get(i).getNombre());
        }
    }

    private void consultarListaPersonas() {

        SQLiteDatabase db= conn.getReadableDatabase();

        Usuario usuario =null;
        listaUsuario = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);
        while (cursor.moveToNext()){

            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            listaUsuario.add(usuario);
        }

    }



}
