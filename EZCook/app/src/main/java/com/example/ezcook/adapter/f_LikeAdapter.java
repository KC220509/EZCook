package com.example.ezcook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.model.f_Like_model;
import com.example.ezcook.R;

import java.util.List;

public class f_LikeAdapter extends RecyclerView.Adapter<f_LikeAdapter.LikeViewHolder>{

    private List<f_Like_model> mListLike;
    public void setData(List<f_Like_model> list_like){
        this.mListLike = list_like;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_item_like,parent,false);
        return  new LikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        f_Like_model likeModel = mListLike.get(position);
        if (likeModel == null){
            return;
        }
        holder.img_like.setImageResource(likeModel.getImg_mode_like());
        holder.title_Like.setText(likeModel.getName_model_like());
        holder.time_like.setText(likeModel.getTime_model_like());
        holder.kcal_like.setText(likeModel.getKcal_model_like());
        holder.favorite_like.setText(likeModel.getFavorite_model_like());
        holder.cmt_like.setText(likeModel.getCmt_model_like());
        holder.note_like.setText(likeModel.getNote_model_like());

    }

    @Override
    public int getItemCount() {
        if (mListLike != null){
            return mListLike.size();
        }
        return 0;
    }

    public class LikeViewHolder extends RecyclerView.ViewHolder{

    private ImageView img_like;
    private TextView title_Like, time_like, kcal_like, favorite_like, cmt_like, note_like;


        public LikeViewHolder(@NonNull View itemView) {
            super(itemView);


            //anh xa
            img_like = itemView.findViewById(R.id.hinhanh_like);
            title_Like = itemView.findViewById(R.id.title_like);
            time_like = itemView.findViewById(R.id.time_like);
            kcal_like = itemView.findViewById(R.id.kcal_like);
            favorite_like = itemView.findViewById(R.id.sl_favorite_like);
            cmt_like = itemView.findViewById(R.id.sl_cmt_like);
            note_like = itemView.findViewById(R.id.sl_note_like);

        }
    }
}
