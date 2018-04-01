package com.example.norgan.miapplicacion01;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.security.Principal;

public class MainActivity extends AppCompatActivity {

    ImageView ivFondo;
    private final int DURACION_SPLASH=7000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ivFondo = (ImageView)findViewById(R.id.ivFondo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent1 = new Intent(MainActivity.this, PrincipalActivity.class);
                startActivity(intent1);
                finish();


            };
        },DURACION_SPLASH);


        Animation myanima = AnimationUtils.loadAnimation(this,R.anim.mitransicion);
        ivFondo.startAnimation(myanima);


    }
}
