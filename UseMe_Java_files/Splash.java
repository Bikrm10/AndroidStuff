package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class Splash extends AppCompatActivity   {
View view;
RelativeLayout rl;
TextView use,me;
Animation u,m;
final static  int splash = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        use = findViewById(R.id.use);
        me= findViewById(R.id.me);
        u= AnimationUtils.loadAnimation(this,R.anim.use_animation);
        m=AnimationUtils.loadAnimation(this,R.anim.me_animation);
        use.setAnimation(u);
        me.setAnimation(m);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Register.class);
                startActivity(i);
                finish();
            }
        },splash);
    }
}