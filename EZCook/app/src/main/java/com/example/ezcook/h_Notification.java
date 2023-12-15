package com.example.ezcook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ezcook.adapter.h_NotificationAdapter;
import com.example.ezcook.model.h_Notification_Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class h_Notification extends AppCompatActivity {
    RecyclerView recyclerNotification;
    h_NotificationAdapter h_notificationAdapter;
    List<h_Notification_Model> notificationModels;
    Button btn_backhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_notification);
        Anhxa();
        Action();

        getNotification();
        setupRecyclerView();
    }
    private void Anhxa() {
        btn_backhome = findViewById(R.id.btn_backhome);
        recyclerNotification = findViewById(R.id.ListNotification);

        notificationModels = new ArrayList<>();
    }

    private void Action() {
        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerNotification.setLayoutManager(linearLayoutManager);

        // Khởi tạo Adapter và cài đặt dữ liệu
        h_notificationAdapter = new h_NotificationAdapter();
        h_notificationAdapter.setData(notificationModels);
        recyclerNotification.setAdapter(h_notificationAdapter);
    }
    private void getNotification(){
    // Lấy dữ liệu từ URL sử dụng Volley
        final String url = "https://kcfullstack.000webhostapp.com/getNotification.php";
        RequestQueue requestQueue = Volley.newRequestQueue(h_Notification.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for(int i=0;i< response.length();i++){
                        try {
                            JSONObject Object = response.getJSONObject(i);

                            String uid = Object.getString("UID");
                            String tieude = Object.getString("TIEUDE");
                            String noidung = Object.getString("NOIDUNG");
                            String image = Object.getString("IMAGE");
                            String timeString = Object.getString("TIME");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                            Date date = dateFormat.parse(timeString);
                            long time = date.getTime();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if(user.getUid().equals(uid)){
                                h_Notification_Model newFood = new h_Notification_Model(tieude, noidung, image, time);

                                notificationModels.add(newFood);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    h_notificationAdapter.notifyDataSetChanged();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VolleyError", error.toString());
                    Toast.makeText(h_Notification.this, "Lỗi truy cập đường dẫn !", Toast.LENGTH_SHORT).show();
                }
            });

    requestQueue.add(jsonArrayRequest);
}





}
