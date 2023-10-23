package com.example.ezcook;

import static com.example.ezcook.adapter.category_listdata_adapter.CATEGORY_FOODNEW;
import static com.example.ezcook.adapter.category_listdata_adapter.CATEGORY_SUGGEST;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.adapter.category_regime_eat_adapter;
import com.example.ezcook.model.category_foodnew_model;
import com.example.ezcook.model.category_regime_eat_model;
import com.example.ezcook.model.category_suggest_model;
import com.example.ezcook.adapter.category_listdata_adapter;
import com.example.ezcook.model.category_listdata_model;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCategoryData, recyclerViewRegimeEat, recyclerViewCategory_suggest;

//    private category_list_vertical_adapter categoryListVerticalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Anhxa();
        Regime_recyclerView();
        category_listdata();
//        Category_listrecyclerview();
    }
    private void Anhxa(){
        recyclerViewRegimeEat = findViewById(R.id.recycler_category_regime_eat);
        recyclerViewCategoryData = findViewById(R.id.recycler_category_data);
//        recyclerViewCategory_suggest = findViewById(R.id.recycler_category_suggest);
    }

    private void Regime_recyclerView(){
        recyclerViewRegimeEat.setHasFixedSize(true);
        recyclerViewRegimeEat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<category_regime_eat_model> categoryRegimeEatModels = new ArrayList<>();

        categoryRegimeEatModels.add(new category_regime_eat_model(R.drawable.image_buoisang,"Ăn sáng"));
        categoryRegimeEatModels.add(new category_regime_eat_model(R.drawable.cat_1,"Ăn trưa"));
        categoryRegimeEatModels.add(new category_regime_eat_model(R.drawable.cat_2,"Ăn tối"));
        categoryRegimeEatModels.add(new category_regime_eat_model(R.drawable.cat_3,"Ăn kiêng"));
        categoryRegimeEatModels.add(new category_regime_eat_model(R.drawable.cat_4,"Ăn chay"));

        category_regime_eat_adapter categoryRegimeEatAdapter = new category_regime_eat_adapter();
        categoryRegimeEatAdapter.setData(categoryRegimeEatModels);
        recyclerViewRegimeEat.setAdapter(categoryRegimeEatAdapter);
    }
//    private void Category_listrecyclerview(){
//        recyclerViewCategory.setHasFixedSize(true);
//
//        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
//
//
//        category_listdata_adapter categoryListVerticalAdapter = new category_listdata_adapter(this);
//        categoryListVerticalAdapter.setData(getCategory_list_verticals());
//        recyclerViewCategory.setAdapter(categoryListVerticalAdapter);
//    }
    private void category_listdata(){
       recyclerViewCategoryData.setHasFixedSize(true);
        recyclerViewCategoryData.setLayoutManager(new LinearLayoutManager(this));

        category_listdata_adapter categoryListdataAdapter = new category_listdata_adapter(this);
        categoryListdataAdapter.setData(getListData());

        recyclerViewCategoryData.setAdapter(categoryListdataAdapter);
    }

    private List<category_listdata_model> getListData() {

        List<category_suggest_model> categorySuggestModels = new ArrayList<>();
        categorySuggestModels.add(new category_suggest_model(R.drawable.image_test, "Thịt kho tàu", "20 phút", "300 kcal"));
        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_1, "Tàu hũ nướng", "15 phút", "310 kcal"));
        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_2, "Cơm chiên trứng", "25 phút", "290 kcal"));
        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_3, "Cơm hải sản", "10 phút", "120 kcal"));


        List<category_foodnew_model> categoryFoodnewModels = new ArrayList<>();
        categoryFoodnewModels.add(new category_foodnew_model(R.drawable.fast_2, "Thịt kho tàu", "10 phút", "200 kcal", R.drawable.ic_user, "Bùi Đức Công"));
        categoryFoodnewModels.add(new category_foodnew_model(R.drawable.fast_1, "Thịt kho tàu", "30 phút", "200 kcal", R.drawable.ic_user, "Khánh Công"));
        categoryFoodnewModels.add(new category_foodnew_model(R.drawable.fast_2, "Cơm chiên hải sản", "25 phút", "200 kcal", R.drawable.ic_user, "Khánh Công"));
        categoryFoodnewModels.add(new category_foodnew_model(R.drawable.fast_3, "Thịt kho tàu", "15 phút", "200 kcal", R.drawable.ic_user, "Trần Thị Lê Trinh"));
        categoryFoodnewModels.add(new category_foodnew_model(R.drawable.image_test, "Thịt kho tàu", "20 phút", "200 kcal", R.drawable.cat_4, "Khánh Công"));


        List<category_listdata_model> categoryListdataModels = new ArrayList<>();
        categoryListdataModels.add(new category_listdata_model(CATEGORY_SUGGEST, "Gợi ý hôm nay", categorySuggestModels, null));
        categoryListdataModels.add(new category_listdata_model(CATEGORY_FOODNEW, "Món ăn mới nhất", null, categoryFoodnewModels));
        categoryListdataModels.add(new category_listdata_model(CATEGORY_SUGGEST, "Gợi ý hôm nay", categorySuggestModels, null));

        return categoryListdataModels;
    }

//    private List<category_listdata_model> getCategory_list_verticals(){
//
//        List<category_suggest_model> categorySuggestModels = new ArrayList<>();
//        categorySuggestModels.add(new category_suggest_model(R.drawable.image_test, "Thịt kho tàu", "20 phút", "300 kcal"));
//        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_1, "Tàu hũ nướng", "15 phút", "310 kcal"));
//        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_2, "Cơm chiên trứng", "25 phút", "290 kcal"));
//        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_3, "Cơm hải sản", "10 phút", "120 kcal"));
//        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_3, "Cơm hải sản", "10 phút", "120 kcal"));
//        categorySuggestModels.add(new category_suggest_model(R.drawable.fast_3, "Cơm hải sản", "10 phút", "120 kcal"));
//
//
//        List<category_listdata_model> categoryListVerticals = new ArrayList<>();
//        categoryListVerticals.add(new category_listdata_model("Gợi ý hôm nay !!!", categorySuggestModels));
//        categoryListVerticals.add(new category_listdata_model("Chế độ ăn !!!", categorySuggestModels));
//        categoryListVerticals.add(new category_listdata_model("Món ăn mới nhất !!!", categorySuggestModels));
//        categoryListVerticals.add(new category_listdata_model("Gợi ý hôm nay !!!", categorySuggestModels));
//        categoryListVerticals.add(new category_listdata_model("Chế độ ăn !!!", categorySuggestModels));
//        categoryListVerticals.add(new category_listdata_model("Món ăn mới nhất !!!", categorySuggestModels));
//        categoryListVerticals.add(new category_listdata_model("Gợi ý hôm nay !!!", categorySuggestModels));
//        categoryListVerticals.add(new category_listdata_model("Chế độ ăn !!!", categorySuggestModels));
//        return categoryListVerticals;
//
//    }
}
