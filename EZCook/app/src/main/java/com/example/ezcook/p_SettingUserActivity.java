package com.example.ezcook;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ezcook.fcm.ImageLoader;
import com.example.ezcook.fcm.RealPathUtil;
import com.example.ezcook.fcm.SendNotification;
import com.example.ezcook.fragment.FavoriteFragment;
import com.example.ezcook.fragment.HomeFragment;
import com.example.ezcook.fragment.ProfileFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class p_SettingUserActivity extends AppCompatActivity {
    ProfileFragment profileFragment;
    HomeFragment homeFragment;
    FavoriteFragment favoriteFragment;

    Button quaylai_btn;
    FrameLayout update_btn;
    TextView textbtn;
    ProgressBar progressBar_load;

    ImageView imageprofile;
    EditText nameprofile, idprofile, emailprofile, phoneprofile, gioithieuprofile;
    CardView chonAVT;
    private Uri selectedImageUri;
    private String oldImageUrl;

    private static final int REQUEST_CODE_PICK_IMAGE_PROFILE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_setting_user);

        Anhxa();
        Action();
        setUserProfileInfomation();
    }

    private void Anhxa() {
        quaylai_btn = findViewById(R.id.quaylai_btn);
        chonAVT = findViewById(R.id.chonAVT);
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


        chonAVT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar_load.setVisibility(View.VISIBLE);
                textbtn.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        updatePhone();
                        onClickUpdateProfile();
                    }
                },1000);
            }
        });


    }
    private void openGallery() {
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(galleryIntent, REQUEST_CODE_PICK_IMAGE_PROFILE);
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE_PROFILE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE_PROFILE && resultCode == RESULT_OK && data != null) {

            selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                Picasso.get().load(selectedImageUri).into(imageprofile);
            }
        }
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null){
            return;
        }

        String strName = nameprofile.getText().toString().trim();
        String strPhone = phoneprofile.getText().toString().trim();
        String strGioiThieu = gioithieuprofile.getText().toString().trim();

        if (selectedImageUri != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(strName)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
//                                String strPath = RealPathUtil.getRealPath(getApplicationContext(), selectedImageUri);
//                                File file = new File(strPath);


                                    upadteData(strName, strPhone, strGioiThieu, selectedImageUri.toString());
                                    progressBar_load.setVisibility(View.INVISIBLE);
                                    textbtn.setVisibility(View.VISIBLE);

                                    // Cập nhật tên người dùng
                                    updateUsernameInFragmentProfile();

                            }
                        }
                    });
        }
        else{
            upadteData(strName, strPhone, strGioiThieu, oldImageUrl);
            progressBar_load.setVisibility(View.INVISIBLE);
            textbtn.setVisibility(View.VISIBLE);
            updateUsernameInFragmentProfile();
        }
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
            homeFragment.category_listdata();
//            homeFragment.onResume();
        }
        favoriteFragment = (FavoriteFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentFavorite);
        if(favoriteFragment != null){
            favoriteFragment.showUserImage();
        }

    }

    private void setUserProfileInfomation() {
        // Lấy dữ liệu từ URL sử dụng Volley
        final String url = "https://kcfullstack.000webhostapp.com/getDataUser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(p_SettingUserActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i< response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String uid = object.getString("UID");
                                String tendangnhap = object.getString("TENDANGNHAP");
                                String email = object.getString("EMAIL");
                                String phoneNumber = object.getString("PHONE");
                                String avt = object.getString("AVT");
                                String gioithieu = object.getString("GIOITHIEU");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (uid.equals(user.getUid())) {
                                    idprofile.setText(uid);
                                    nameprofile.setText(tendangnhap);
                                    emailprofile.setText(email);
                                    phoneprofile.setText(phoneNumber);
                                    oldImageUrl = avt;
                                    if(avt.equals("") || avt == null){
                                        Picasso.get().load(R.drawable.h_account_circle_24).into(imageprofile);
                                    }
                                    else {
                                        Picasso.get().load(Uri.parse(avt)).into(imageprofile);
                                    }
                                    gioithieuprofile.setText(gioithieu);
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.toString());
                        Toast.makeText(p_SettingUserActivity.this, "Lỗi truy cập đường dẫn !", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void upadteData(String name, String phone, String gioithieu, String imageUrl) {
        String url = "https://kcfullstack.000webhostapp.com/updateUser.php";

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }
        // Tạo một RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Tạo một StringRequest để thực hiện POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý phản hồi từ máy chủ
                        if(response.trim().equals("success")){
                            Toast.makeText(p_SettingUserActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            sendPushNotification();
                        }
                        else {
                            Toast.makeText(p_SettingUserActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        }
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
                params.put("uid", user.getUid());
                params.put("tendangnhap", name);
                params.put("phone", phone);
                params.put("avt", imageUrl);
                params.put("gioithieu", gioithieu);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
    private void addNotification(String uid, String tieude, String noidung, String image, String time){
        String url = "https://kcfullstack.000webhostapp.com/addNotification.php";
        // Tạo một RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Tạo một StringRequest để thực hiện POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý phản hồi từ máy chủ
//                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("tieude", tieude);
                params.put("noidung", noidung);
                params.put("image", image);
                params.put("time", time);
                return params;
            }
        };
        // Thêm request vào hàng đợi
        requestQueue.add(stringRequest);
    }
    private void sendPushNotification(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.h_logo_app);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        long currentTimeMillis = System.currentTimeMillis();
        String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(new Date(currentTimeMillis));

        String image = "android.resource://" + getPackageName() + "/" + R.drawable.h_logo_app;

        addNotification(user.getUid(), "Update Profile", "Cập nhật trang cá nhân thành công", image, timestamp);

        Notification notification = new NotificationCompat.Builder(this, SendNotification.CHANNEL_ID)
                .setContentTitle("Ezcook")
                .setContentText("Cập nhật trang cá nhân thành công")
                .setSmallIcon(R.drawable.logo_app)
                .setLargeIcon(bitmap)
                .setSound(uri)
                .setColor(getResources().getColor(R.color.black))
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(getNotificationId(), notification);
        }
    }
    private int getNotificationId(){
        return (int) new Date().getTime();
    }

}