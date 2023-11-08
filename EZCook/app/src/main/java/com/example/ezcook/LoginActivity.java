package com.example.ezcook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button bt_login, bt_signup;
    TextInputEditText ip_email, ip_pass;
    FrameLayout frame_btn_login;
    TextView textbtn;
    ProgressBar progressBar_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_login);

        Anhxa();

        Action();
    }
    private void Anhxa(){
        ip_email = findViewById(R.id.input_Email);
        ip_pass = findViewById(R.id.input_Pass);

        frame_btn_login = findViewById(R.id.button_login);
        textbtn = findViewById(R.id.text_btn);
        progressBar_load = findViewById(R.id.progressbar);

//        bt_login = findViewById(R.id.button_login);
        bt_signup = findViewById(R.id.button_signup);
    }
    private void Action(){
        frame_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar_load.setVisibility(View.VISIBLE);
                textbtn.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        onClickLogin();
                    }
                },1000);
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
    private void onClickLogin(){
        String ipEmail = ip_email.getText().toString().trim();
        String ipPass =  ip_pass.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (ipEmail.isEmpty() || ipPass.isEmpty()) {
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
            // Kiểm tra xem các trường có được nhập đầy đủ không
            Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!isEmailValid(ipEmail)) {
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
            Toast.makeText(LoginActivity.this, "Email không hợp lệ. Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
            return;
        }


        auth.signInWithEmailAndPassword(ipEmail, ipPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Chào" + " " + ipEmail, Toast.LENGTH_SHORT).show();

                            Intent intent_login_success = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent_login_success);
                            finishAffinity();

                        } else {
                            progressBar_load.setVisibility(View.INVISIBLE);
                            textbtn.setVisibility(View.VISIBLE);
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng !",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private boolean isEmailValid(String strEmail) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return strEmail.matches(emailPattern);
    }
}
