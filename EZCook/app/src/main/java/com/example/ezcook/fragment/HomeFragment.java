package com.example.ezcook.fragment;

import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_FOODNEW;
import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_SUGGEST;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.adapter.h_category_regime_eat_adapter;
import com.example.ezcook.adapter.h_category_suggest_adapter;
import com.example.ezcook.fcm.ImageLoader;
import com.example.ezcook.h_Notification;
import com.example.ezcook.h_SearchActivity;
import com.example.ezcook.model.h_category_foodnew_model;
import com.example.ezcook.model.h_category_regime_eat_model;
import com.example.ezcook.model.h_category_suggest_model;
import com.example.ezcook.adapter.h_category_listdata_adapter;
import com.example.ezcook.model.h_category_listdata_model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private MainActivity mainActivity;

    private RecyclerView recyclerViewRegimeEat, recyclerViewCategoryData;
    private ImageView image_userhome, imgNotification;
    private TextView name_userhome;
    private LinearLayout action_search;
    List<h_category_listdata_model> categoryListdataModels;
    h_category_listdata_adapter categoryListdataAdapter;

    h_category_suggest_adapter categorySuggestAdapter;
    List<h_category_foodnew_model> arrayListFoodNew;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();

        Anhxa(view_home);
        show_activity_search();
        show_activity_notification();
        regime_recyclerView();
        getDataFoodnew();
        category_listdata();
        showUserInfo();
        return view_home;
    }

    private void Anhxa(View view){


        image_userhome = view.findViewById(R.id.image_userhome);
        name_userhome = view.findViewById(R.id.tv_name_user);
        imgNotification = view.findViewById(R.id.imgNotification);
        action_search = view.findViewById(R.id.linear_search);

        recyclerViewRegimeEat = view.findViewById(R.id.recycler_category_regime_eat);
        recyclerViewCategoryData = view.findViewById(R.id.recycler_category_data);

        arrayListFoodNew = new ArrayList<>();
        categoryListdataAdapter = new h_category_listdata_adapter(mainActivity, mainActivity);

//        recyclerViewCategory_suggest = findViewById(R.id.recycler_category_suggest);
    }

    private void show_activity_search(){
        action_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(mainActivity, h_SearchActivity.class);
                startActivity(intent_search);
            }
        });
    }
    private void show_activity_notification(){
        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show_Notification = new Intent(mainActivity, h_Notification.class);
                startActivity(show_Notification);
            }
        });
    }

    private void regime_recyclerView(){
        recyclerViewRegimeEat.setHasFixedSize(true);
        recyclerViewRegimeEat.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false));

        List<h_category_regime_eat_model> categoryRegimeEatModels = new ArrayList<>();

        categoryRegimeEatModels.add(new h_category_regime_eat_model(R.drawable.image_buoisang,"Ăn sáng"));
        categoryRegimeEatModels.add(new h_category_regime_eat_model(R.drawable.cat_1,"Ăn trưa"));
        categoryRegimeEatModels.add(new h_category_regime_eat_model(R.drawable.cat_2,"Ăn tối"));
        categoryRegimeEatModels.add(new h_category_regime_eat_model(R.drawable.cat_3,"Ăn kiêng"));
        categoryRegimeEatModels.add(new h_category_regime_eat_model(R.drawable.cat_4,"Ăn chay"));

        h_category_regime_eat_adapter categoryRegimeEatAdapter = new h_category_regime_eat_adapter();
        categoryRegimeEatAdapter.setData(categoryRegimeEatModels);
        recyclerViewRegimeEat.setAdapter(categoryRegimeEatAdapter);
    }
    public void category_listdata(){
        recyclerViewCategoryData.setHasFixedSize(true);
        recyclerViewCategoryData.setLayoutManager(new LinearLayoutManager(mainActivity));

        categorySuggestAdapter = new h_category_suggest_adapter();
        categoryListdataAdapter.setData(getListData());
        recyclerViewCategoryData.setAdapter(categoryListdataAdapter);
    }
    public void getDataFoodnew(){
        final String url = "https://kcfullstack.000webhostapp.com/getDataFoodnew.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i< response.length();i++){
                            try {
                                JSONObject Object = response.getJSONObject(i);
                                String masp = Object.getString("MASP");
                                String hinhanh = Object.getString("HINHANH");
                                String title = Object.getString("TIEUDE");
                                int time = Object.getInt("TIME");
                                int kcal = Object.getInt("KCAL");
                                String avt = Object.getString("AVT");
                                String name = Object.getString("TENDANGNHAP");
                                h_category_foodnew_model newFood = new h_category_foodnew_model(masp, hinhanh, title, time + " phút", kcal + " kcal", avt, name);

                                arrayListFoodNew.add(newFood);

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        categoryListdataAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.toString());
                        Toast.makeText(mainActivity, "Lỗi truy cập đường dẫn !", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    private List<h_category_listdata_model> getListData() {

        List<h_category_suggest_model> categorySuggestModels = new ArrayList<>();
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.image_test, "Thịt kho tàu", "20 phút", "300 kcal", R.drawable.h_ic_easy));
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.fast_1, "Ếch xào sả ớt", "15 phút", "310 kcal",R.drawable.h_ic_medium));
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.fast_2, "Cơm chiên trứng", "25 phút", "290 kcal",R.drawable.h_ic_easy));
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.fast_3, "Cơm hải sản", "10 phút", "120 kcal",R.drawable.h_ic_difficult));
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.cat_1, "Bò hầm", "20 phút", "300 kcal", R.drawable.h_ic_difficult));
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.cat_4, "Tàu hũ nướng", "15 phút", "310 kcal",R.drawable.h_ic_medium));
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.cat_2, "Humburger", "25 phút", "290 kcal",R.drawable.h_ic_easy));
        categorySuggestModels.add(new h_category_suggest_model(R.drawable.cat_3, "Mì xào hải sản", "20 phút", "120 kcal",R.drawable.h_ic_easy));


        categoryListdataModels = new ArrayList<>();
        categoryListdataModels.add(new h_category_listdata_model(CATEGORY_SUGGEST, "Gợi ý hôm nay", categorySuggestModels, null));
        categoryListdataModels.add(new h_category_listdata_model(CATEGORY_FOODNEW, "Món ăn mới nhất", null, arrayListFoodNew));

        return categoryListdataModels;
    }

    public void showUserInfo(){
        final String url = "https://kcfullstack.000webhostapp.com/getDataUser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i< response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String uid = object.getString("UID");
                                String nameuser = object.getString("TENDANGNHAP");
                                String avt = object.getString("AVT");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (uid.equals(user.getUid())) {
                                    name_userhome.setText(nameuser);
//                                    Picasso.get().load(Uri.parse(avt)).into(image_userhome);
//                                    ImageLoader.loadImage(mainActivity, image_userhome, avt);
                                    if(avt.equals("") || avt == null){
                                        Picasso.get().load(R.drawable.h_account_circle_24).into(image_userhome);
                                    }
                                    else {
                                        Picasso.get().load(Uri.parse(avt)).into(image_userhome);
                                    }
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
                        Toast.makeText(mainActivity, "Lỗi truy cập đường dẫn !", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }


    @Override
    public void onResume() {
        super.onResume();
        showUserInfo();
    }
}
