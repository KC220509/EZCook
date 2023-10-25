package com.example.ezcook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.model.p_MonAn_model;

import java.util.List;

public class p_AdapterMonAn extends RecyclerView.Adapter<p_AdapterMonAn.MonAnViewHolder> {

    private List<p_MonAn_model> mListMonAn;
    public void setData(List<p_MonAn_model> list){
        this.mListMonAn  = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.p_layout_monan,parent,false);
        return  new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        p_MonAn_model monan = mListMonAn.get(position);
        if(monan == null){
            return;
        }
        holder.name.setText(monan.getTenmon());
        holder.thoigian.setText(monan.getThoigian());
        holder.kcal.setText(monan.getKcal());
        holder.imghinh.setImageResource(monan.getHinh());

    }

    @Override
    public int getItemCount() {
        if(mListMonAn != null){
            return mListMonAn.size();
        }
        return 0;
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder{
        private TextView name,thoigian, kcal;
        private ImageView imghinh;
        private ConstraintLayout mainLayout;
        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            thoigian = itemView.findViewById(R.id.thoigian);
            kcal = itemView.findViewById(R.id.kcal);
            imghinh = itemView.findViewById(R.id.imagehinh);

        }
    }
}
