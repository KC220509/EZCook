package com.example.ezcook.fragment;

import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_FOODNEW;
import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_SUGGEST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ezcook.AnimationUtil;
import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.adapter.h_category_regime_eat_adapter;
import com.example.ezcook.adapter.h_category_suggest_adapter;
import com.example.ezcook.f_StepCookActivity;
import com.example.ezcook.h_SearchActivity;
import com.example.ezcook.model.h_category_foodnew_model;
import com.example.ezcook.model.h_category_regime_eat_model;
import com.example.ezcook.model.h_category_suggest_model;
import com.example.ezcook.adapter.h_category_listdata_adapter;
import com.example.ezcook.model.h_category_listdata_model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private MainActivity mainActivity;

    private RecyclerView recyclerViewRegimeEat, recyclerViewCategoryData;
    private ImageView image_userhome;
    private TextView name_userhome;
    private LinearLayout action_search;
    h_category_listdata_adapter categoryListdataAdapter;

    h_category_suggest_adapter categorySuggestAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();


        Anhxa(view_home);
        show_activity_search();
        regime_recyclerView();
        category_listdata();

        showUserInfo();
        return view_home;
    }
    private void Anhxa(View view){

        image_userhome = view.findViewById(R.id.image_userhome);
        name_userhome = view.findViewById(R.id.tv_name_user);
        action_search = view.findViewById(R.id.linear_search);

        recyclerViewRegimeEat = view.findViewById(R.id.recycler_category_regime_eat);
        recyclerViewCategoryData = view.findViewById(R.id.recycler_category_data);


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
    private void category_listdata(){
        recyclerViewCategoryData.setHasFixedSize(true);
        recyclerViewCategoryData.setLayoutManager(new LinearLayoutManager(mainActivity));

        categoryListdataAdapter = new h_category_listdata_adapter(mainActivity, mainActivity);

        categorySuggestAdapter = new h_category_suggest_adapter();
        categoryListdataAdapter.setData(getListData());
        recyclerViewCategoryData.setAdapter(categoryListdataAdapter);
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



        List<h_category_foodnew_model> categoryFoodnewModels = new ArrayList<>();
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.fast_2, "Thịt kho tàu", "10 phút", "200 kcal", R.drawable.h_ic_user, "Bùi Đức Công"));
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.fast_1, "Cơm chiên trứng", "30 phút", "200 kcal", R.drawable.h_ic_user, "Khánh Công"));
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.fast_2, "Cơm chiên hải sản", "25 phút", "200 kcal", R.drawable.h_ic_user, "Khánh Công"));
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.fast_3, "Thịt kho tàu", "15 phút", "200 kcal", R.drawable.h_ic_user, "Trần Thị Lê Trinh"));
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.image_test, "Thịt kho", "20 phút", "200 kcal", R.drawable.cat_4, "Khánh Công"));
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.fast_2, "Thịt kho tàu", "10 phút", "200 kcal", R.drawable.h_ic_user, "Bùi Đức Công"));
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.fast_1, "Thịt kho tàu", "30 phút", "200 kcal", R.drawable.h_ic_user, "Khánh Công"));
        categoryFoodnewModels.add(new h_category_foodnew_model(R.drawable.fast_2, "Cơm chiên hải sản", "25 phút", "200 kcal", R.drawable.h_ic_user, "Khánh Công"));


        List<h_category_listdata_model> categoryListdataModels = new ArrayList<>();
        categoryListdataModels.add(new h_category_listdata_model(CATEGORY_SUGGEST, "Gợi ý hôm nay", categorySuggestModels, null));
        categoryListdataModels.add(new h_category_listdata_model(CATEGORY_FOODNEW, "Món ăn mới nhất", null, categoryFoodnewModels));


        return categoryListdataModels;
    }
    public void showUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }

        String name_user = user.getDisplayName();
        String email_user = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
//
        if (name_user == null){
//            name_userhome.setVisibility(View.GONE);
            name_userhome.setText(email_user);
        }
        else {
            name_userhome.setVisibility(View.VISIBLE);
            name_userhome.setText(name_user);
        }

        Glide.with(this).load(photoUrl).error(R.drawable.h_account_circle_24).into(image_userhome);

    }

    @Override
    public void onResume() {
        super.onResume();
        showUserInfo();
    }
}
