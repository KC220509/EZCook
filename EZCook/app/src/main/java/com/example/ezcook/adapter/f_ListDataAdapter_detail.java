package com.example.ezcook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.model.f_ListData_detail;

import java.util.List;



public class f_ListDataAdapter_detail extends RecyclerView.Adapter<f_ListDataAdapter_detail.ListData_detailViewHolder>{


    public static final int Type_ingredient=1;
    public static final int Type_Step=2;
    private List<f_ListData_detail> mListDetail;
    private Context mContext;
    public void setData(Context context,List<f_ListData_detail> listDetail){
        this.mContext = context;
        this.mListDetail= listDetail;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mListDetail.get(position).getType();
    }
    public class ListData_detailViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title_detail;
        private RecyclerView rcvItem_detail;

        public ListData_detailViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title_detail = itemView.findViewById(R.id.title_detail);
            rcvItem_detail = itemView.findViewById(R.id.rcv_detail);
        }
    }
    @NonNull
    @Override
    public ListData_detailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_layout_list_detail,parent,false);
        return new ListData_detailViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListData_detailViewHolder holder, int position) {
        f_ListData_detail listDataDetail = mListDetail.get(position);
        if (listDataDetail == null){
            return;
        }
        if (Type_ingredient == holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false);
            holder.rcvItem_detail.setLayoutManager(linearLayoutManager);
            holder.rcvItem_detail.setFocusable(false);

            f_IngredientAdapter_detail ingredientAdapterDetail = new f_IngredientAdapter_detail();
            ingredientAdapterDetail.setData(listDataDetail.getListIngredient());
            holder.tv_title_detail.setText(listDataDetail.getTitle_content());
            holder.rcvItem_detail.setAdapter(ingredientAdapterDetail);

        }else if(Type_Step== holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false);
            holder.rcvItem_detail.setLayoutManager(linearLayoutManager);
            holder.rcvItem_detail.setFocusable(false);

            f_StepAdapter_detail stepAdapterDetail = new f_StepAdapter_detail();
            stepAdapterDetail.setData(listDataDetail.getListStep());
            holder.tv_title_detail.setText(listDataDetail.getTitle_content());
            holder.rcvItem_detail.setAdapter(stepAdapterDetail);
        }




    }

    @Override
    public int getItemCount() {
        if (mListDetail != null){
            return mListDetail.size();
        }
        return 0;
    }
}

