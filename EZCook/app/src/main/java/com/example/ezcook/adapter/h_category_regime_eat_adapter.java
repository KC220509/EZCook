package com.example.ezcook.adapter;

import com.example.ezcook.R;
import com.example.ezcook.model.h_category_regime_eat_model;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class h_category_regime_eat_adapter extends RecyclerView.Adapter<h_category_regime_eat_adapter.viewholder_regime> {
//    ArrayList<category_domain> categoryDomains;
    private List<h_category_regime_eat_model> regimeEatModels;
    public void setData(List<h_category_regime_eat_model> RegimeEatModelsModels){
        this.regimeEatModels = RegimeEatModelsModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewholder_regime onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_item_category_regime_eat, parent, false);
        return  new viewholder_regime(view_inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_regime holder, int position) {
        h_category_regime_eat_model categoryRegimeEatModel = regimeEatModels.get(position);
        if(categoryRegimeEatModel == null){
            return;
        }

        holder.categorypic.setImageResource(categoryRegimeEatModel.getPic());
        holder.categorytitle.setText(categoryRegimeEatModel.getTitle());
//        holder.categorypic.setImageResource(this.regimeEatModels.get(position).getPic());
//        holder.categorytitle.setText(this.regimeEatModels.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (regimeEatModels != null){
            return regimeEatModels.size();
        }
        return 0;
    }
   public class viewholder_regime extends RecyclerView.ViewHolder{
        private TextView categorytitle;
        private ImageView categorypic;
        public viewholder_regime(@NonNull View itemView){
            super(itemView);
            categorytitle = itemView.findViewById(R.id.category_regime_title);
            categorypic = itemView.findViewById(R.id.category_regime_pic);
        }
    }
}
