package com.example.ezcook.adapter;

import com.example.ezcook.R;
import com.example.ezcook.model.h_category_suggest_model;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.example.ezcook.model.t_categogy_foodtrend_model;


public class t_categogy_fastfood_adapter extends RecyclerView.Adapter<t_categogy_fastfood_adapter.viewholder_fastfood> {

    private List<t_categogy_foodtrend_model> category_foodtrend_list;

//    }
public void setData(List<t_categogy_foodtrend_model> suggest_list){
    this.category_foodtrend_list = suggest_list;

    notifyDataSetChanged();
}
    public class viewholder_fastfood extends RecyclerView.ViewHolder {

    private ImageView category_fastfood_pic, category_fastfood_picLevel;
    private TextView category_fastfood_title, category_fastfood_time, category_fastfood_kcal;
    private CardView itemsuggest;
    private ImageView imgAddToSave;

    public viewholder_fastfood(@NonNull View itemView) {
        super(itemView);
//        itemsuggest = itemView.findViewById(R.id.item_suggest);
//        imgAddToSave = itemView.findViewById(R.id.image_save_suggest);

        category_fastfood_pic = itemView.findViewById(R.id.category_foodtrend_pic);
        category_fastfood_title = itemView.findViewById(R.id.category_foodtrend_title);
        category_fastfood_time = itemView.findViewById(R.id.category_foodtrend_time);
        category_fastfood_kcal = itemView.findViewById(R.id.category_foodtrend_kcal);
        category_fastfood_picLevel = itemView.findViewById(R.id.category_foodtrend_picLevel);

    }
    }

    @NonNull
    @Override
    public viewholder_fastfood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_foastfood = LayoutInflater.from(parent.getContext()).inflate(R.layout.t_item_food, parent, false);
        return new viewholder_fastfood(view_foastfood);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_fastfood holder, int position) {
        final t_categogy_foodtrend_model categoryfoodtrendModel = category_foodtrend_list.get(position);
        if(categoryfoodtrendModel == null){
            return;
        }
        holder.category_fastfood_title.setText(categoryfoodtrendModel.getTitle_food());
        holder.category_fastfood_time.setText(categoryfoodtrendModel.getTime_food());
        holder.category_fastfood_pic.setImageResource(categoryfoodtrendModel.getPic_food());
        holder.category_fastfood_kcal.setText(categoryfoodtrendModel.getKcal_food());
        holder.category_fastfood_picLevel.setImageResource(categoryfoodtrendModel.getPic_level());
    }


    @Override
    public int getItemCount() {
        if(category_foodtrend_list != null){
            return category_foodtrend_list.size();
        }
        return 0;
    }
}
