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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

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
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            saveUidToDatabase(user.getUid(), strName, strEmail, strPhone);
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
    private void saveUidToDatabase(String uid, String tendangnhap, String email, String phone) {
        String url = "https://kcfullstack.000webhostapp.com/saveUser.php";
        // Tạo một RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Tạo một StringRequest để thực hiện POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý phản hồi từ máy chủ (có thể hiển thị thông báo hoặc thực hiện các hành động khác)
//                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi thực hiện request
                        Toast.makeText(SignupActivity.this, "Lỗi khi gửi thông tin đến máy chủ", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", uid);
                params.put("tendangnhap", tendangnhap);
                params.put("email", email);
                params.put("phone", phone);
                return params;
            }
        };

        // Thêm request vào hàng đợi
        requestQueue.add(stringRequest);
    }
}
