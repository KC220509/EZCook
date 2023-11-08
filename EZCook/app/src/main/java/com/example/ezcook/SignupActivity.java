package com.example.ezcook;

import android.app.ProgressDialog;
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

public class SignupActivity extends AppCompatActivity {
    Button bt_signup,bt_login;
    TextInputEditText editEmail, editName, editPhone, editPass, editRepass;
    FrameLayout frame_btn;
    TextView textbtn;
    ProgressBar progressBar_load;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_signup);

        Anhxa();
        Acion();


    }
    private void Anhxa(){

        editEmail = findViewById(R.id.editTextEmail);
        editName = findViewById(R.id.editTextName);
        editPhone = findViewById(R.id.editTextPhoneNumberSignup);
        editPass = findViewById(R.id.editTextPass);
        editRepass = findViewById(R.id.editTextRePass);

        frame_btn = findViewById(R.id.button_signup);
        textbtn = findViewById(R.id.text_btn);
        progressBar_load = findViewById(R.id.progressbar);

//        bt_signup = findViewById(R.id.button_signup);
        bt_login = findViewById(R.id.button_login);
    }
    private void Acion(){
        frame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar_load.setVisibility(View.VISIBLE);
                textbtn.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        onClickSignup();
                    }
                },1000);
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
    private void onClickSignup(){
        String strEmail = editEmail.getText().toString().trim();
        String strName = editName.getText().toString().trim();
        String strPhone =  editPhone.getText().toString().trim();
        String strPass =  editPass.getText().toString().trim();
        String strRepass =  editRepass.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();


        if (strEmail.isEmpty() || strName.isEmpty() || strPhone.isEmpty() || strPass.isEmpty() || strRepass.isEmpty()) {
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
            // Kiểm tra xem các trường có được nhập đầy đủ không
            Toast.makeText(SignupActivity.this, "Vui lòng điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!isEmailValid(strEmail)) {
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
            Toast.makeText(SignupActivity.this, "Email không hợp lệ. Vui lòng kiểm tra lại !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isPhoneValid(strPhone)) {
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
            Toast.makeText(SignupActivity.this, "Số điện thoại không hợp lệ. Vui lòng kiểm tra lại !", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isPasswordValid(strPass) || !isPasswordValid(strRepass)){
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
            Toast.makeText(SignupActivity.this, "Mật khẩu tối thiểu 6 ký tự và có ít nhất một số !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!strPass.equals(strRepass)) {
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
                Toast.makeText(SignupActivity.this, "Mật khẩu không trùng khớp !", Toast.LENGTH_SHORT).show();
                return;
            // Kiểm tra xem mật khẩu và repass có trùng khớp không
        }


        auth.createUserWithEmailAndPassword(strEmail, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Đăng ký thành công
                            Toast.makeText(SignupActivity.this, "Đăng ký thành công.", Toast.LENGTH_SHORT).show();

                            Intent intent_signup_success = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent_signup_success);
                            finish();
                        } else {
                            progressBar_load.setVisibility(View.INVISIBLE);
                            textbtn.setVisibility(View.VISIBLE);
                            // Đăng ký không thành công
                            Toast.makeText(SignupActivity.this, "Đăng ký không thành công !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isEmailValid(String strEmail) {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            return strEmail.matches(emailPattern);
    }
    private boolean isPhoneValid(String phone) {
        // Sử dụng regex đầu tiên
        return phone.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
    }
    private boolean isPasswordValid(String password) {
        // Kiểm tra chiều dài tối thiểu (ít nhất 6 ký tự)
        if (password.length() < 6) {
            return false;
        }

        // Kiểm tra xem mật khẩu có ít nhất một số
        if (!password.matches(".*\\d.*")) {
            return false;
        }


        return true;
    }
}
