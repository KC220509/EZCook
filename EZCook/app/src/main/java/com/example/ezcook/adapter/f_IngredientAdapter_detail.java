package com.example.ezcook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.model.f_ingredient_detail;

import java.util.List;



public class f_IngredientAdapter_detail extends RecyclerView.Adapter<f_IngredientAdapter_detail.IngredientViewHolder>{

    private List<f_ingredient_detail> mListIng;
    public void setData(List<f_ingredient_detail> list_Ing){
        this.mListIng = list_Ing;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_item_nguyenlieu,parent,false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        f_ingredient_detail ingredientDetail = mListIng.get(position);
        if (ingredientDetail == null){
            return;
        }
        holder.img_ingredient.setImageResource(ingredientDetail.getImg_ingredient());
        holder.tv_name_ingredient.setText(ingredientDetail.getName_ingredient());
        holder.tv_mass_ingredient.setText(ingredientDetail.getMass_ingredient());
    }

    @Override
    public int getItemCount() {
        if (mListIng != null){
            return mListIng.size();
        }
        return 0;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_ingredient;
        private TextView tv_name_ingredient;
        private TextView tv_mass_ingredient;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            img_ingredient = itemView.findViewById(R.id.f_img_detail);
            tv_name_ingredient = itemView.findViewById(R.id.f_temNL_detail);
            tv_mass_ingredient = itemView.findViewById(R.id.f_kl_detail);
        }
    }
}
