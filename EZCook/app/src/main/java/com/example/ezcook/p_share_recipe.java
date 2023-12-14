package com.example.ezcook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class p_share_recipe extends AppCompatActivity {
    Button btnbackpost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity_action);

        Anhxa();
        Action();
    }
    private void Anhxa(){

        btnbackpost = findViewById(R.id.btn_backpost);
    }
    private void Action(){
        btnbackpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
