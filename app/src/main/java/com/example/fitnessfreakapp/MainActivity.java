package com.example.fitnessfreakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  static int SPLASH_SCREEN = 7000;

    //variable
    Animation topAnim, bottomAnim;
    ImageView simage;
    TextView welcome,tagline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        simage = findViewById(R.id.imageView);
        welcome = findViewById(R.id.textView);
        tagline = findViewById(R.id.textView3);

        simage.setAnimation(topAnim);
        welcome.setAnimation(bottomAnim);
        tagline.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(simage,"logo_image");
                pairs[1] = new Pair<View,String>(welcome,"logo_image");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        },SPLASH_SCREEN);
    }
}