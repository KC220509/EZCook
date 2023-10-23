package com.example.ezcook.adapter;

import com.example.ezcook.R;
import com.example.ezcook.model.category_suggest_model;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class category_suggest_adapter extends RecyclerView.Adapter<category_suggest_adapter.viewholder_suggest>{
    private List<category_suggest_model> category_suggest_list;
    public void setData(List<category_suggest_model> suggest_list){
        this.category_suggest_list = suggest_list;
        notifyDataSetChanged();
    }

//    public category_suggest_adapter(List<category_suggest_model> category_suggest_list) {
//        this.category_suggest_list = category_suggest_list;
//    }

    public class viewholder_suggest extends RecyclerView.ViewHolder{

        private ImageView  category_suggest_pic;
        private TextView category_suggest_title, category_suggest_time, category_suggest_kcal;
        public viewholder_suggest(@NonNull View itemView){
            super(itemView);
            category_suggest_pic = itemView.findViewById(R.id.category_suggest_pic);
            category_suggest_title = itemView.findViewById(R.id.category_suggest_title);
            category_suggest_time = itemView.findViewById(R.id.category_suggest_time);
            category_suggest_kcal = itemView.findViewById(R.id.category_suggest_kcal);
        }
    }

    @NonNull
    @Override
    public viewholder_suggest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_suggest = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_suggest, parent, false);
        return new viewholder_suggest(view_suggest);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_suggest holder, int position) {
        category_suggest_model categorySuggestModel = category_suggest_list.get(position);
        if(categorySuggestModel == null){
            return;
        }
        holder.category_suggest_title.setText(categorySuggestModel.getTitle());
        holder.category_suggest_time.setText(categorySuggestModel.getTime());
        holder.category_suggest_pic.setImageResource(categorySuggestModel.getPic());
        holder.category_suggest_kcal.setText(categorySuggestModel.getKcal());
//
//        holder.category_suggest_title.setText(this.category_suggest_list.get(position).getTitle());
//        holder.category_suggest_time.setText(this.category_suggest_list.get(position).getTime());
//        holder.category_suggest_pic.setImageResource(this.category_suggest_list.get(position).getPic());
//        holder.category_suggest_kcal.setText(this.category_suggest_list.get(position).getKcal());
    }

    @Override
    public int getItemCount() {
        if(category_suggest_list != null){
            return category_suggest_list.size();
        }
        return 0;
    }

}
