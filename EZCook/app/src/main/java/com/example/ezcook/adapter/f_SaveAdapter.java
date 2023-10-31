package com.example.ezcook.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.f_StepCookActivity;
import com.example.ezcook.model.f_Save_model;
import com.example.ezcook.R;

import java.util.List;

public class f_SaveAdapter extends RecyclerView.Adapter<f_SaveAdapter.SaveViewHolder>{

    private List<f_Save_model> mListSave;

    public void setData(List<f_Save_model> list_save){
        this.mListSave = list_save;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_item_save,parent,false);
        return  new SaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        f_Save_model saveModel = mListSave.get(position);
        if (saveModel == null){
            return;
        }
        holder.pic_Save.setImageResource(saveModel.getPic());
        holder.title_Save.setText(saveModel.getTitle());
        holder.time_Save.setText(saveModel.getTime());
        holder.kcal_Save.setText(saveModel.getKcal());


        
    }

    @Override
    public int getItemCount() {
        if (mListSave != null){
            return mListSave.size();
        }
        return 0;
    }

    public class SaveViewHolder extends RecyclerView.ViewHolder{

        private ImageView pic_Save;
        private TextView title_Save, time_Save, kcal_Save;
        private CardView item_save;


        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            item_save = itemView.findViewById(R.id.itemsave);

            pic_Save=itemView.findViewById(R.id.save_pic);
            title_Save=itemView.findViewById(R.id.save_title);
            time_Save =itemView.findViewById(R.id.save_time);
            kcal_Save=itemView.findViewById(R.id.save_kcal);
        }
    }
}
