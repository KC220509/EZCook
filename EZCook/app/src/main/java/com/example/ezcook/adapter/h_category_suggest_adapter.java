package com.example.ezcook.adapter;

import com.example.ezcook.R;
import com.example.ezcook.f_StepCookActivity;
import com.example.ezcook.fragment.HomeFragment;
import com.example.ezcook.model.h_category_suggest_model;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class h_category_suggest_adapter extends RecyclerView.Adapter<h_category_suggest_adapter.viewholder_suggest>{

    private List<h_category_suggest_model> category_suggest_list;


    public void setData(List<h_category_suggest_model> suggest_list){
        this.category_suggest_list = suggest_list;
        notifyDataSetChanged();
    }

//    public category_suggest_adapter(List<category_suggest_model> category_suggest_list) {
//        this.category_suggest_list = category_suggest_list;
//    }

    public class viewholder_suggest extends RecyclerView.ViewHolder{

        private ImageView  category_suggest_pic;
        private TextView category_suggest_title, category_suggest_time, category_suggest_kcal;
        private CardView itemsuggest;
        public viewholder_suggest(@NonNull View itemView){
            super(itemView);
            itemsuggest = itemView.findViewById(R.id.item_suggest);
            category_suggest_pic = itemView.findViewById(R.id.category_suggest_pic);
            category_suggest_title = itemView.findViewById(R.id.category_suggest_title);
            category_suggest_time = itemView.findViewById(R.id.category_suggest_time);
            category_suggest_kcal = itemView.findViewById(R.id.category_suggest_kcal);
        }
    }

    @NonNull
    @Override
    public viewholder_suggest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_suggest = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_item_category_suggest, parent, false);
        return new viewholder_suggest(view_suggest);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_suggest holder, int position) {
        h_category_suggest_model categorySuggestModel = category_suggest_list.get(position);
        if(categorySuggestModel == null){
            return;
        }
        holder.category_suggest_title.setText(categorySuggestModel.getTitle());
        holder.category_suggest_time.setText(categorySuggestModel.getTime());
        holder.category_suggest_pic.setImageResource(categorySuggestModel.getPic());
        holder.category_suggest_kcal.setText(categorySuggestModel.getKcal());

        holder.itemsuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(context, f_StepCookActivity.class);
                intent.putExtra("category_image", categorySuggestModel.getPic());
//                intent.putExtra("category_title", categorySuggestModel.getTitle());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(category_suggest_list != null){
            return category_suggest_list.size();
        }
        return 0;
    }

}
