package com.example.norgan.miapplicacion01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PrincipalActivity extends AppCompatActivity {
    ImageButton imageButton3,imageButton2,imageButton1,imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


                imageButton = (ImageButton)findViewById(R.id.imageButton);
                imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
                imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
                imageButton3 = (ImageButton)findViewById(R.id.imageButton3);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarioss",null,1);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent80 = new Intent(PrincipalActivity.this,ListViewActivity.class);
                startActivity(intent80);
            }
        });


        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(PrincipalActivity.this,Spinner1Activity.class);
                startActivity(intent4);

            }
        });


        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PrincipalActivity.this,RegistrarActivity.class);
                startActivity(intent2);
            }
        });



        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(PrincipalActivity.this,ConsultarActivity.class);
                startActivity(intent3);

            }
        });





    }
}
