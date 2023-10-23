package com.example.ezcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation center_anim;
    LinearLayout ln_header, ln_content, ln_footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Anhxa();
        Action_Animation();

    }
    private void Anhxa(){
        ln_content = findViewById(R.id.linearLayout_logo);
    }
    private void Action_Animation(){
        center_anim = AnimationUtils.loadAnimation(this, R.anim.center_animation);

        ln_content.setAnimation(center_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_load = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent_load);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}