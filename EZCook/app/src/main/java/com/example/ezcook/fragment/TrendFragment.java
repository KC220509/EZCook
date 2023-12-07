package com.example.ezcook.fragment;

import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_FOODNEW;
import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_SUGGEST;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.adapter.h_category_regime_eat_adapter;


import com.example.ezcook.adapter.t_categogy_fastfood_adapter;
import com.example.ezcook.model.h_category_regime_eat_model;
import com.example.ezcook.model.h_category_suggest_model;
import com.example.ezcook.adapter.h_category_listdata_adapter;
import com.example.ezcook.model.h_category_listdata_model;
import com.example.ezcook.p_AuthorActivity;
import com.example.ezcook.p_SettingActivity;
import com.example.ezcook.p_SettingUserActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.ezcook.model.t_categogy_foodtrend_model;
import java.util.ArrayList;
import java.util.List;

public class TrendFragment extends Fragment {

    private MainActivity mainActivity;

    private RecyclerView recycler_listdata_fastfood, recycler_listdata_new,recycler_listdata_cake;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_trend, container, false);
        mainActivity = (MainActivity) getActivity();


        Anhxa(view_home);
        category_listdata();
        food_recyclerView();
        return view_home;
    }
    private void Anhxa(View view){


        recycler_listdata_new = view.findViewById(R.id.recycler_listdata_new);
        recycler_listdata_fastfood = view.findViewById(R.id.recycler_listdata_fastfood);
        recycler_listdata_cake = view.findViewById(R.id.recycler_listdata_cake);


    }


    private void category_listdata(){

        recycler_listdata_new.setHasFixedSize(true);
        recycler_listdata_new.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false));

        recycler_listdata_fastfood.setHasFixedSize(true);
        recycler_listdata_fastfood.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false));

        recycler_listdata_cake.setHasFixedSize(true);
        recycler_listdata_cake.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false));


    }

    private void food_recyclerView(){

        List<t_categogy_foodtrend_model> categoryfastfoodModels = new ArrayList<>();

        categoryfastfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "20 phút", "300 kcal", R.drawable.h_ic_easy));
        categoryfastfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho xả", "20 phút", "300 kcal", R.drawable.h_ic_easy));
        categoryfastfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Sườn chua ngọt", "20 phút", "300 kcal", R.drawable.h_ic_easy));
        categoryfastfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Sườn rim", "20 phút", "300 kcal", R.drawable.h_ic_easy));
        categoryfastfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Gà bóp", "20 phút", "300 kcal", R.drawable.h_ic_easy));

        t_categogy_fastfood_adapter categoryFastfoodAdapter = new t_categogy_fastfood_adapter();
        categoryFastfoodAdapter.setData(categoryfastfoodModels);
        categoryFastfoodAdapter.notifyDataSetChanged();
        recycler_listdata_fastfood.setAdapter(categoryFastfoodAdapter);



        List<t_categogy_foodtrend_model> categorynewfoodModels = new ArrayList<>();
        categorynewfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "44 phút", "300 kcal", R.drawable.h_ic_easy));
        categorynewfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "44 phút", "300 kcal", R.drawable.h_ic_easy));
        categorynewfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "44 phút", "300 kcal", R.drawable.h_ic_easy));
        categorynewfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "24 phút", "300 kcal", R.drawable.h_ic_easy));
        categorynewfoodModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "24 phút", "300 kcal", R.drawable.h_ic_easy));

        t_categogy_fastfood_adapter categorynewFoodModels = new t_categogy_fastfood_adapter();
        categorynewFoodModels.setData(categorynewfoodModels);
        recycler_listdata_new.setAdapter(categorynewFoodModels);


        List<t_categogy_foodtrend_model> categorycakeModels = new ArrayList<>();
        categorycakeModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "33 phút", "300 kcal", R.drawable.h_ic_easy));
        categorycakeModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "33 phút", "300 kcal", R.drawable.h_ic_easy));
        categorycakeModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "33 phút", "300 kcal", R.drawable.h_ic_easy));
        categorycakeModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "33 phút", "300 kcal", R.drawable.h_ic_easy));
        categorycakeModels.add(new t_categogy_foodtrend_model(R.drawable.image_test, "Thịt kho tàu", "33 phút", "300 kcal", R.drawable.h_ic_easy));

        t_categogy_fastfood_adapter categoryCakeModels = new t_categogy_fastfood_adapter();
        categoryCakeModels.setData(categorycakeModels);
        recycler_listdata_cake.setAdapter(categoryCakeModels);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}