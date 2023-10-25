package com.example.ezcook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    Button bt_signup_success,bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_signup);

        Anhxa();
        Acion();


    }
    private void Anhxa(){

        bt_signup_success = findViewById(R.id.button_signup);
        bt_login = findViewById(R.id.button_login);
    }
    private void Acion(){
        bt_signup_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_signup_success = new Intent(SignupActivity.this, LoginActivity.class);
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent_login);
            }
        });
    }
}
