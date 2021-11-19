package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1.ui.CaNhan.LoginActivity;

public class OneActivity extends AppCompatActivity {
    ImageView logo,bacgroundApp;
    TextView tvAppName, tvAppname1;
    Animation leftoright, righttoleft, topBottom,aniOngVang,anibg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);


        logo = findViewById(R.id.logo);
        tvAppName = findViewById(R.id.tvAppName);
        tvAppname1 = findViewById(R.id.tvAppName1);
        bacgroundApp=findViewById(R.id.bgapp);

        aniOngVang = AnimationUtils.loadAnimation(OneActivity.this, R.anim.scale);
        anibg = AnimationUtils.loadAnimation(OneActivity.this, R.anim.background_intro_animation);


        leftoright = AnimationUtils.loadAnimation(this, R.anim.righttoleft);
        righttoleft = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        topBottom = AnimationUtils.loadAnimation(this, R.anim.bottomtop);

        logo.setAnimation(righttoleft);
        tvAppName.setAnimation(leftoright);
        tvAppname1.setAnimation(leftoright);
        bacgroundApp.setAnimation(anibg);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OneActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}