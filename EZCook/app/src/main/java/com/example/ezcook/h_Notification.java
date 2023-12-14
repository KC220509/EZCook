package com.example.ezcook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.adapter.h_NotificationAdapter;
import com.example.ezcook.fcm.SQLiteHelper;
import com.example.ezcook.model.h_Notification_Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class h_Notification extends AppCompatActivity {
    private static h_Notification instance;
    RecyclerView recyclerNotification;
    h_NotificationAdapter h_notificationAdapter;
    Button btn_backhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_notification);
        Anhxa();
        Action();
        recycleViewNotification();
    }
    private void Anhxa() {
        instance = this;
        btn_backhome = findViewById(R.id.btn_backhome);
        recyclerNotification = findViewById(R.id.ListNotification);
    }

    private void Action() {
        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void recycleViewNotification() {
        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            // Nếu đã đăng nhập, lấy uid và gọi phương thức recycleViewNotification
            String userId = user.getUid();
            setupRecyclerView(userId);
        }
    }

    private void setupRecyclerView(String userId) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerNotification.setLayoutManager(linearLayoutManager);

        SQLiteHelper dbHelper = new SQLiteHelper(this);

        // Lấy dữ liệu từ cơ sở dữ liệu dựa trên user_id
        List<h_Notification_Model> notificationList = dbHelper.getAllNotifications(userId);

        // Khởi tạo Adapter và cài đặt dữ liệu
        h_notificationAdapter = new h_NotificationAdapter();
        h_notificationAdapter.setData(notificationList);
        recyclerNotification.setAdapter(h_notificationAdapter);
    }






}
