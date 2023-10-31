package com.example.ezcook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.model.h_category_food_model;
import com.example.ezcook.myinterface.i_ClickItemListener_FoodSearch;

import java.util.List;

public class h_category_food_adapter extends RecyclerView.Adapter<h_category_food_adapter.listfood_viewholder> {
    List<h_category_food_model> hCategoryFoodModelList;
    public i_ClickItemListener_FoodSearch iClickItemListenerFoodSearch;
    public void setData(List<h_category_food_model> listdatafood, i_ClickItemListener_FoodSearch iClickItemListenerFoodSearch){
        this.hCategoryFoodModelList = listdatafood;
        this.iClickItemListenerFoodSearch = iClickItemListenerFoodSearch;
        notifyDataSetChanged();
    }
    public void setFilterList(List<h_category_food_model> filterList, i_ClickItemListener_FoodSearch iClickItemListenerFoodSearch){
        this.hCategoryFoodModelList = filterList;
        this.iClickItemListenerFoodSearch = iClickItemListenerFoodSearch;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public listfood_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_food = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_item_category_food, parent,false);
        return new listfood_viewholder(view_food);
    }

    @Override
    public void onBindViewHolder(@NonNull listfood_viewholder holder, int position) {
        final h_category_food_model hCategoryFoodModel = hCategoryFoodModelList.get(position);
        if(hCategoryFoodModel == null){
            return;
        }

        holder.pic_star.setImageResource(hCategoryFoodModel.getPic_star());
        holder.title_evaluate.setText(hCategoryFoodModel.getText_evaluate());
        holder.pic_food.setImageResource(hCategoryFoodModel.getPic_food());
        holder.title_food.setText(hCategoryFoodModel.getTitle_food());
        holder.time_food.setText(hCategoryFoodModel.getTime_food());
        holder.kcal_food.setText(hCategoryFoodModel.getKcal_food());
        holder.pic_level.setImageResource(hCategoryFoodModel.getPic_level());


        holder.item_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListenerFoodSearch.onClickItemListener_FoodSearch(hCategoryFoodModel);
            }
        });
    }



    @Override
    public int getItemCount() {
        if(hCategoryFoodModelList != null){
            return hCategoryFoodModelList.size();
        }
        return 0;
    }

    public class listfood_viewholder extends RecyclerView.ViewHolder {
        private ImageView pic_star, pic_food, pic_level;
        private TextView title_evaluate, title_food, time_food, kcal_food;

        private CardView item_food;


        public listfood_viewholder(@NonNull View itemView) {
            super(itemView);
            item_food = itemView.findViewById(R.id.item_food);


            pic_star = itemView.findViewById(R.id.image_star);
            title_evaluate = itemView.findViewById(R.id.title_evaluate);
            pic_food = itemView.findViewById(R.id.category_food_pic);
            title_food = itemView.findViewById(R.id.category_food_title);
            time_food = itemView.findViewById(R.id.category_food_time);
            kcal_food = itemView.findViewById(R.id.category_food_kcal);
            pic_level = itemView.findViewById(R.id.level_image);
        }
    };
}
