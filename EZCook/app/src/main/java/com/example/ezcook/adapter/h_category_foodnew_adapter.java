package com.example.ezcook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.model.h_category_foodnew_model;
import com.example.ezcook.myinterface.i_ClickItemListener_Foodnew;
import com.example.ezcook.myinterface.i_ClickItemListener_Suggest;
import com.squareup.picasso.Picasso;

import java.util.List;

public class h_category_foodnew_adapter extends RecyclerView.Adapter<h_category_foodnew_adapter.viewholder_foodnew> {
    public i_ClickItemListener_Foodnew iClickItemListenerFoodnew;
    public List<h_category_foodnew_model> categoryFoodnewModels;
    public void setData(List<h_category_foodnew_model> categoryfoodnewModels, i_ClickItemListener_Foodnew iClickItemListenerFoodnew){
        this.categoryFoodnewModels = categoryfoodnewModels;
        this.iClickItemListenerFoodnew = iClickItemListenerFoodnew;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public viewholder_foodnew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_foodnew = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_item_category_foodnew, parent,false);
        return new viewholder_foodnew(view_foodnew);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_foodnew holder, int position) {
        h_category_foodnew_model categoryFoodnewModel = categoryFoodnewModels.get(position);
        if(categoryFoodnewModel == null){
            return;
        }

        holder.masp.setText(categoryFoodnewModel.getId());
        Picasso.get().load(categoryFoodnewModel.getPic()).into(holder.pic);
        holder.title.setText(categoryFoodnewModel.getTitle());
        holder.time.setText(categoryFoodnewModel.getTime());
        holder.kcal.setText(categoryFoodnewModel.getKcal());
        Picasso.get().load(categoryFoodnewModel.getPic_user()).into(holder.pic_user);
        holder.title_user.setText(categoryFoodnewModel.getTitle_user());


        holder.item_foodnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListenerFoodnew.onClickItemListener_Foodnew(categoryFoodnewModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(categoryFoodnewModels != null){
            return categoryFoodnewModels.size();
        }
        return 0;
    }

    public class viewholder_foodnew extends RecyclerView.ViewHolder{
        private ImageView pic, pic_user;
        private TextView masp, title, time, kcal, title_user;
        private CardView item_foodnew;
        public viewholder_foodnew(@NonNull View itemView) {
            super(itemView);
            item_foodnew = itemView.findViewById(R.id.item_foodnew);

            masp = itemView.findViewById(R.id.category_foodnew_masp);
            pic = itemView.findViewById(R.id.category_foodnew_pic);
            title = itemView.findViewById(R.id.category_foodnew_title);
            time = itemView.findViewById(R.id.category_foodnew_time);
            kcal = itemView.findViewById(R.id.category_foodnew_kcal);
            pic_user = itemView.findViewById(R.id.category_foodnew_user_pic);
            title_user = itemView.findViewById(R.id.category_foodnew_user_title);
        }
    }
}
