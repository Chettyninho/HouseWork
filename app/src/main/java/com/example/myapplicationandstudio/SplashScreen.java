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

        ImageView logoSplash = findViewById(R.id.bota);
        TextView nameSplash = findViewById(R.id.appNameSplash);

        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation nameAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation shakeAnimation = AnimationUtils.loadAnimation(this,R.anim.shake);

        logoSplash.startAnimation(logoAnimation);
        logoSplash.startAnimation(shakeAnimation);
        ImageView background = findViewById(R.id.backgroundSplash);
        Glide.with(this)
                .load("https://images.unsplash.com/photo-1533240332313-0db49b459ad6?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8c2VuZGVyaXNtb3xlbnwwfHwwfHx8MA%3D%3D")
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .centerCrop()
                .into(background);
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
        },3000);

    }

}