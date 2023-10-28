package com.example.ezcook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class p_SettingActivity extends AppCompatActivity {

    Button quaylai_btn;
    LinearLayout btn_signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_setting);

        Anhxa();
        Action();

    }
    private void Anhxa(){
        quaylai_btn = findViewById(R.id.quaylai_btn);
        btn_signout = findViewById(R.id.btn_signout);

    }
    private void Action(){
        quaylai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(p_SettingActivity.this, LoginActivity.class));
            }
        });
    }
}