package com.example.ezcook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button bt_login_success, bt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_login);

        Anhxa();

        Action();
    }
    private void Anhxa(){
        bt_login_success = findViewById(R.id.button_login);
        bt_signup = findViewById(R.id.button_signup);
    }
    private void Action(){
        bt_login_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login_success = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent_login_success);
            }
        });
        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent_signup);
            }
        });
    }
}
