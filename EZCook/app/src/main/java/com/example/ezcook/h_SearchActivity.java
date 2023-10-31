package com.example.ezcook;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.adapter.h_category_food_adapter;
import com.example.ezcook.model.h_category_food_model;
import com.example.ezcook.model.h_category_suggest_model;
import com.example.ezcook.myinterface.i_ClickItemListener_FoodSearch;

import java.util.ArrayList;
import java.util.List;

public class h_SearchActivity extends AppCompatActivity {
    private Button button_back;
    private ImageButton imagebutton_filter;
    private SearchView searchView_food;
    List<h_category_food_model> hCategoryFoodModels;
    h_category_food_adapter hCategoryFoodAdapter;
    private RecyclerView rcv_food;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_search);


        Anhxa();
        Action();
        showListFood();
        searchViewFood();

    }


    private void Anhxa(){
        button_back = findViewById(R.id.quaylai_btn);
        imagebutton_filter = findViewById(R.id.btnimage_filter);
        rcv_food = findViewById(R.id.recycler_category_food);
        searchView_food = findViewById(R.id.searchview_home);




    }
    private void Action() {
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(h_SearchActivity.this, HomeFragment.class));
                setResult(Activity.RESULT_OK);
                finish();
            }
        });


        imagebutton_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });





    }


    private void searchViewFood(){
        searchView_food.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


    }
    @Override
    public void onBackPressed() {
        if (!searchView_food.isIconified()) {
            searchView_food.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }
    private void filterList(String newText) {
        List<h_category_food_model> filterList_search = new ArrayList<>();
        for (h_category_food_model hCategoryFoodModel: hCategoryFoodModels){
            if(hCategoryFoodModel.getTitle_food().toLowerCase().contains(newText.toLowerCase())){
                filterList_search.add(hCategoryFoodModel);
            }
        }
            hCategoryFoodAdapter.setFilterList(filterList_search, new i_ClickItemListener_FoodSearch() {
                @Override
                public void onClickItemListener_FoodSearch(h_category_food_model hCategoryFoodModel_Filter) {
                    onClickGotoCookFood(hCategoryFoodModel_Filter);
                }
            });
    }

    private void showListFood(){
        rcv_food.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_food.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcv_food.setLayoutManager(gridLayoutManager);

        hCategoryFoodAdapter = new h_category_food_adapter();
        hCategoryFoodAdapter.setData(getListFood(), new i_ClickItemListener_FoodSearch() {
            @Override
            public void onClickItemListener_FoodSearch(h_category_food_model hCategoryFoodModel) {
                onClickGotoCookFood(hCategoryFoodModel);
            }
        });
        rcv_food.setAdapter(hCategoryFoodAdapter);
    }
    private void onClickGotoCookFood(h_category_food_model hCategoryFoodModel) {
        Intent intent = new Intent(this, f_StepCookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("stepcook_food", hCategoryFoodModel);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private List<h_category_food_model> getListFood() {
        hCategoryFoodModels = new ArrayList<>();
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_full, "5.0", R.drawable.thanh_pham, "Bò hầm", "20 phút", "200 kcal", R.drawable.h_ic_medium));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_half, "4.8", R.drawable.cat_1, "Phở bò hà nội", "10 phút", "100 kcal", R.drawable.h_ic_medium));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_half, "4.6", R.drawable.cat_2, "Hamburger", "25 phút", "50 kcal", R.drawable.h_ic_easy));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_full, "5.0", R.drawable.cat_3, "Bánh mì nướng", "30 phút", "10 kcal", R.drawable.h_ic_easy));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_half, "4.3", R.drawable.cat_4, "Bò hầm", "15 phút", "200 kcal", R.drawable.h_ic_medium));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_full, "5.0", R.drawable.fast_1, "Sườn chua ngọt", "30 phút", "100 kcal", R.drawable.h_ic_medium));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_full, "5.0", R.drawable.fast_2, "Thịt kho ruốc", "20 phút", "30 kcal", R.drawable.h_ic_difficult));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_full, "5.0", R.drawable.thanh_pham_bunbo, "Bún bò", "15 phút", "120 kcal", R.drawable.h_ic_medium));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_half, "4.3", R.drawable.thanh_pham_canhmuopdang, "Canh mướp đắng", "30 phút", "200 kcal", R.drawable.h_ic_easy));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_half, "4.7", R.drawable.thanh_pham_suonrim, "Sườn rim", "30 phút", "210 kcal", R.drawable.h_ic_difficult));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_full, "5.0", R.drawable.image_test, "Mỳ xào hải sản", "30 phút", "100 kcal", R.drawable.h_ic_medium));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_half, "4.9", R.drawable.f_canhchua, "Canh chua", "30 phút", "250 kcal", R.drawable.h_ic_difficult));
        hCategoryFoodModels.add(new h_category_food_model(R.drawable.h_ic_star_full, "5.0", R.drawable.thanh_pham, "Bò hầm", "15 phút", "20 kcal", R.drawable.h_ic_easy));

        return hCategoryFoodModels;
    }



    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.p_bottomsheetlayout);

        LinearLayout layoutSetting = dialog.findViewById(R.id.layoutSetting);
        LinearLayout layoutCaNhan = dialog.findViewById(R.id.layoutCaNhan);
        LinearLayout layoutQR = dialog.findViewById(R.id.layoutQR);
        LinearLayout layoutHD = dialog.findViewById(R.id.layoutHD);
        LinearLayout layoutAuthor = dialog.findViewById(R.id.layoutAuthor);

        layoutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintenSetting = new Intent(h_SearchActivity.this, p_SettingActivity.class);
                startActivity(myintenSetting);
            }
        });

        layoutCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintenSettingUser = new Intent(h_SearchActivity.this, p_SettingUserActivity.class);
                startActivity(myintenSettingUser);
            }
        });

        layoutQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(h_SearchActivity.this,"QR is click",Toast.LENGTH_SHORT).show();
            }
        });

        layoutHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(h_SearchActivity.this,"Hoạt động is click",Toast.LENGTH_SHORT).show();
            }
        });
        layoutAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintenSettingUser = new Intent(h_SearchActivity.this, p_AuthorActivity.class);
                startActivity(myintenSettingUser);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.h_menu_searchview, menu);
//        return true;
//    }
}
