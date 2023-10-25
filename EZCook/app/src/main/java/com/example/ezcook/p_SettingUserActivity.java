package com.example.ezcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ezcook.fragment.ProfileFragment;

public class p_SettingUserActivity extends AppCompatActivity {

    Button quaylai_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_setting_user);


        quaylai_btn = findViewById(R.id.quaylai_btn);
        quaylai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}