package com.example.ezcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.ezcook.fcm.MyFirebaseMessagingService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;

public class StartActivity extends AppCompatActivity {

    public static final String TAG = StartActivity.class.getName();


    private static int SPLASH_SCREEN = 5000;
    Animation center_anim;
    LinearLayout ln_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.h_activity_start);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.e(TAG, token);
                    }
                });

        Anhxa();
        Action_Animation();

    }
    private void Anhxa(){
        ln_content = findViewById(R.id.linearLayout_logo);
    }
    private void Action_Animation(){
        center_anim = AnimationUtils.loadAnimation(this, R.anim.h_center_animation);

        ln_content.setAnimation(center_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, SPLASH_SCREEN);
    }
    private void nextActivity(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent intent_load = new Intent(this, LoginActivity.class);
            startActivity(intent_load);

        }else {
            Intent intent_load = new Intent(this, MainActivity.class);
            startActivity(intent_load);
        }
        finish();
    }


}