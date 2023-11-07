package com.example.myapplicationandstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView logoSplash = findViewById(R.id.bota);
        TextView posible = findViewById(R.id.posible);
        //TextView nameSplash = findViewById(R.id.appNameSplash);

        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation nameAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation shakeAnimation = AnimationUtils.loadAnimation(this,R.anim.shake);

        posible.startAnimation(logoAnimation);

        logoSplash.startAnimation(logoAnimation);
        logoSplash.startAnimation(shakeAnimation);
        //ImageView background = findViewById(R.id.backgroundSplash);
        Glide.with(this)
                .load("https://images.unsplash.com/photo-1584820927498-cfe5211fd8bf?auto=format&fit=crop&q=80&w=1887&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .centerCrop();
                //.into(background);
        openLogin();
    }
    public void openLogin(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },4000);

    }

}