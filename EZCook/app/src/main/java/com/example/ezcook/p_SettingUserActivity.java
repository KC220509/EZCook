package com.example.ezcook;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ezcook.adapter.h_category_listdata_adapter;
import com.example.ezcook.fragment.HomeFragment;
import com.example.ezcook.fragment.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.HashMap;
import java.util.Map;


public class p_SettingUserActivity extends AppCompatActivity {
    h_category_listdata_adapter categoryListdataAdapter;
    ProfileFragment profileFragment;
    HomeFragment homeFragment;
    LoginActivity reload;

    View view_home;

    Button quaylai_btn;
    FrameLayout update_btn;
    TextView textbtn;
    ProgressBar progressBar_load;

    //profile
    private ImageView imageprofile;
    private EditText nameprofile, idprofile, emailprofile, phoneprofile, gioithieuprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_setting_user);

        Anhxa();
        Action();
        setUserProfileInfomation();
//        initListener();
    }

    private void Anhxa() {
        quaylai_btn = findViewById(R.id.quaylai_btn);
        imageprofile = findViewById(R.id.imgage_Profile);
        nameprofile = findViewById(R.id.editTextNameProfile);
        idprofile = findViewById(R.id.editTextIDProfile);
        emailprofile = findViewById(R.id.editTextEmailProfile);
        phoneprofile = findViewById(R.id.editTextPhoneProfile);
        gioithieuprofile = findViewById(R.id.editTextMotaProfile);

        update_btn = findViewById(R.id.btn_update_profile);
        textbtn = findViewById(R.id.text_btn);
        progressBar_load = findViewById(R.id.progressbar);
    }
    private void Action() {
        quaylai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar_load.setVisibility(View.VISIBLE);
                textbtn.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        onClickUpdateProfile();
                    }
                },1000);
            }
        });
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null){
            return;
        }
        String strName = nameprofile.getText().toString().trim();
//        String phone = phoneprofile.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strName)
                .build();

        user.updateProfile(profileUpdates)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user != null){
                            String updateId = user.getUid();
                            String updateName = user.getDisplayName();
                            String updateEmail = user.getEmail();

                            upadteData(updateId, updateName, updateEmail);
                        }


                        Toast.makeText(p_SettingUserActivity.this, "Cập nhật trang cá nhân thành công", Toast.LENGTH_SHORT).show();
//                        updateData();
                        progressBar_load.setVisibility(View.INVISIBLE);
                        textbtn.setVisibility(View.VISIBLE);

                        // Cập nhật tên người dùng
                        updateUsernameInFragmentProfile();

                    }
                }
            });
    }

    private void updateUsernameInFragmentProfile() {
        profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentProfile);
        if(profileFragment != null){
            profileFragment.showUserProfileInfo();
        }

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentHome);
        if(homeFragment != null){
            homeFragment.showUserInfo();
            homeFragment.getDataFoodnew();
            homeFragment.onDataUpdated();
        }

    }

    private void setUserProfileInfomation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        nameprofile.setText(user.getDisplayName());
        idprofile.setText(user.getUid());
        emailprofile.setText(user.getEmail());
        phoneprofile.setText(user.getPhoneNumber());
        Glide.with(p_SettingUserActivity.this).load(R.drawable.h_account_circle_24).into(imageprofile);
//        gioithieuprofile.setText(user.get());
    }
    private void upadteData(String uid, String tendangnhap, String email) {
        String url = "http://10.0.2.2:8080/DataEzcook/saveUser.php";
        // Tạo một RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Tạo một StringRequest để thực hiện POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý phản hồi từ máy chủ (có thể hiển thị thông báo hoặc thực hiện các hành động khác)
                        Toast.makeText(p_SettingUserActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi thực hiện request
                        Toast.makeText(p_SettingUserActivity.this, "Lỗi khi gửi thông tin đến máy chủ", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", uid);
                params.put("tendangnhap", tendangnhap);
                params.put("email", email);
                return params;
            }
        };

        // Thêm request vào hàng đợi
        requestQueue.add(stringRequest);
    }

}