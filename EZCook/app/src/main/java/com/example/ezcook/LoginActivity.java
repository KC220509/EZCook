package com.example.ezcook;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ezcook.fcm.SendNotification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

                            sendPushNotificationLogin();
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
    private void addNotification(String uid, String tieude, String noidung, String image, String time){
        String url = "https://kcfullstack.000webhostapp.com/addNotification.php";
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
                        Toast.makeText(LoginActivity.this, "Lỗi khi gửi thông tin đến máy chủ", Toast.LENGTH_SHORT).show();
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
    private void sendPushNotificationLogin(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.h_logo_app);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        long currentTimeMillis = System.currentTimeMillis();
        String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(new Date(currentTimeMillis));

        String image = "android.resource://" + getPackageName() + "/" + R.drawable.h_logo_app;

        addNotification(user.getUid(), "Login success", "Chào mừng " + user.getDisplayName() + " đến với Ezcook", image, timestamp);

        Notification notification = new NotificationCompat.Builder(this, SendNotification.CHANNEL_ID)
                .setContentTitle("Ezcook")
                .setContentText("Chào mừng " + user.getDisplayName() + " đến với Ezcook")
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
