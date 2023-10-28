package com.example.ezcook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.model.f_Step_detail;

import java.util.List;



public class f_StepAdapter_detail extends RecyclerView.Adapter<f_StepAdapter_detail.StepViewHolder>{

    private List<f_Step_detail> mListStep;
    void setData(List<f_Step_detail> listStep){
        this.mListStep = listStep;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_item_step_detail,parent,false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        f_Step_detail stepDetail= mListStep.get(position);
        if (stepDetail == null){
            return;
        }
        holder.tv_Step.setText(stepDetail.getStep());
        holder.tv_Content.setText(stepDetail.getMake());
    }

    @Override
    public int getItemCount() {
        if (mListStep != null){
            return mListStep.size();
        }
        return 0;
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Step;
        private TextView tv_Content;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Step= itemView.findViewById(R.id.f_step_detail);
            tv_Content= itemView.findViewById(R.id.f_make_detail);
        }
    }
}
