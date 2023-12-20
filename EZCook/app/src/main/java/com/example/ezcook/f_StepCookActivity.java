package com.example.ezcook;


import static com.example.ezcook.adapter.f_ListDataAdapter_detail.Type_Step;
import static com.example.ezcook.adapter.f_ListDataAdapter_detail.Type_ingredient;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.ezcook.adapter.f_ListDataAdapter_detail;
import com.example.ezcook.model.f_ListData_detail;
import com.example.ezcook.model.f_Step_detail;
import com.example.ezcook.model.f_ingredient_detail;
import com.example.ezcook.model.h_category_food_model;
import com.example.ezcook.model.h_category_foodnew_model;
import com.example.ezcook.model.h_category_suggest_model;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class f_StepCookActivity extends AppCompatActivity {
    private Button btn_back;

    private RecyclerView rcv_detail;
    private f_ListDataAdapter_detail listDataDetail;
    private ImageView imagedetail;
    private TextView titledetail;
    List<f_ListData_detail> listDataDetails;

    List<f_ingredient_detail> listNguyenlieu;
    List<f_Step_detail> listStep;
    String idsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_activity_stepcook);
        Anhxa();
        getDataNguyenlieu();
        getDataCachlam();
        Action();


    }
        private void Anhxa(){
            btn_back = findViewById(R.id.btn_back);
            rcv_detail= findViewById(R.id.rcv_f_detail);
            imagedetail = findViewById(R.id.image_detail);
            titledetail = findViewById(R.id.title_detail);

            listNguyenlieu = new ArrayList<>();
            listStep = new ArrayList<>();
            listDataDetail = new f_ListDataAdapter_detail();
        }
        private void Action(){
            LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
            rcv_detail.setLayoutManager(linearLayoutManager);


            listDataDetail.setData(this , getList_DetailData());
            rcv_detail.setAdapter(listDataDetail);

            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            });

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                if (bundle.containsKey("stepcook_suggest")) {
                    h_category_suggest_model hCategorySuggestModel = (h_category_suggest_model) bundle.get("stepcook_suggest");
                    imagedetail.setImageResource(hCategorySuggestModel.getPic());
                    titledetail.setText(hCategorySuggestModel.getTitle());
                } else if (bundle.containsKey("stepcook_food")) {
                    h_category_food_model hCategoryFoodModel = (h_category_food_model) bundle.get("stepcook_food");
                    imagedetail.setImageResource(hCategoryFoodModel.getPic_food());
                    titledetail.setText(hCategoryFoodModel.getTitle_food());
                } else if (bundle.containsKey("stepcook_foodnew")) {
                    h_category_foodnew_model hCategoryFoodnewModel = (h_category_foodnew_model) bundle.get("stepcook_foodnew");
                    idsp = hCategoryFoodnewModel.getId();
                    Picasso.get().load(hCategoryFoodnewModel.getPic()).into(imagedetail);
                    titledetail.setText(hCategoryFoodnewModel.getTitle());

                }
            }

        }
    private List<f_ListData_detail> getList_DetailData() {

//        List<f_Step_detail> listStep = new ArrayList<>();
//        listStep.add(new f_Step_detail("Buoc 1","Chuẩn bị 1 chiếc nồi sạch đổ : 250ml sữa tươi, 250ml sữa đặc, 400ml nước cốt dừa ( 1 lon ) quậy đều tay để các nguyên liệu được hòa với nhau."));
//        listStep.add(new f_Step_detail("Buoc 2","Tiếp tục cho dừa tươi nạo sợi, một ít muối vào quậy đều."));
//        listStep.add(new f_Step_detail("Buoc 3","Đun hỗn hợp ở lửa trung bình và luôn quậy đều để hỗn hợp không bị cháy"));
//        listStep.add(new f_Step_detail("Buoc 4","Khi hỗn hợp sữa được 70°C ta cho 30gr sữa bột vào quậy tan ."));
//        listStep.add(new f_Step_detail("Buoc 5"," Đổ 50ml sữa còn lại vào 25gr bột ngô để bột ngô được hòa tan và tiếp tục đổ vào nồi hỗn hợp."));
//        listStep.add(new f_Step_detail("Buoc 6","Khi nồi hỗn hợp sôi lục bục ta tắt bếp và để nguội mới đổ vào khuôn kem ."));


        listDataDetails = new ArrayList<>();
        listDataDetails.add(new f_ListData_detail(Type_ingredient, "Nguyên liệu", listNguyenlieu,null));
        listDataDetails.add(new f_ListData_detail(Type_Step,"Cách làm",null, listStep));


        return listDataDetails;
    }
    private void getDataCachlam(){
        final String url = "https://kcfullstack.000webhostapp.com/getDataFoodnew.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i< response.length();i++){
                            try {
                                JSONObject Object = response.getJSONObject(i);
                                String steps = Object.getString("CACHLAM");
                                String masp = Object.getString("MASP");
                                if(masp.equals(idsp)){
                                    String[] stepsArray = steps.split(";");
                                    for (String step : stepsArray) {
                                        // Create f_Step_detail objects and add them to the list
                                        listStep.add(new f_Step_detail("Bước " + (listStep.size() + 1), step.trim()));
                                    }
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        listDataDetail.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.toString());
                        Toast.makeText(f_StepCookActivity.this, "Lỗi dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
    private void getDataNguyenlieu(){
        final String url = "https://kcfullstack.000webhostapp.com/getNguyenlieu.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i< response.length();i++){
                            try {
                                JSONObject Object = response.getJSONObject(i);
                                String tengnuyenlieu = Object.getString("TENNGUYENLIEU");
                                String hinhanhnl = Object.getString("HINHANHNL");
                                int trongluong = Object.getInt("TRONGLUONG");
                                String masp = Object.getString("MASP");
                                if(masp.equals(idsp)){
                                    f_ingredient_detail ingredient_detail = new f_ingredient_detail(hinhanhnl, tengnuyenlieu, trongluong + " gam");
                                    listNguyenlieu.add(ingredient_detail);
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        listDataDetail.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.toString());
                        Toast.makeText(f_StepCookActivity.this, "Lỗi dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

}
