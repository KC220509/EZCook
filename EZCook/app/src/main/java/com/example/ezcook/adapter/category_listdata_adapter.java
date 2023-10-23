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
import com.example.ezcook.model.category_foodnew_model;
import com.example.ezcook.model.category_suggest_model;
import com.example.ezcook.adapter.category_foodnew_adapter;
import com.example.ezcook.adapter.category_suggest_adapter;
import com.example.ezcook.model.category_listdata_model;

import java.util.List;

public class category_listdata_adapter extends RecyclerView.Adapter<category_listdata_adapter.viewholder_listdata> {
    private Context context;
    public static final int CATEGORY_SUGGEST = 1;
    public static final int CATEGORY_FOODNEW = 2;

    private List<category_listdata_model> categoryListdataModels;

    public category_listdata_adapter(Context context) {
        this.context = context;
    }

    public void setData(List<category_listdata_model> categorylistdataModels){
        this.categoryListdataModels = categorylistdataModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return categoryListdataModels.get(position).getType();
    }

    public class viewholder_listdata extends RecyclerView.ViewHolder{
        private TextView title_listdata;
        private RecyclerView rcvListdata;
        public viewholder_listdata(@NonNull View itemView) {
            super(itemView);
            title_listdata = itemView.findViewById(R.id.title_listdata_category);
            rcvListdata = itemView.findViewById(R.id.recycler_listdata_category);
        }
    }
    @NonNull
    @Override
    public viewholder_listdata onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_category = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_listdata, parent, false);
        return new viewholder_listdata(view_category);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_listdata holder, int position) {
        category_listdata_model listdata_model = categoryListdataModels.get(position);
        if(listdata_model == null){
            return;
        }

        holder.title_listdata.setText(listdata_model.getTitle_list());

        if (CATEGORY_SUGGEST == holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false);
            holder.rcvListdata.setLayoutManager(linearLayoutManager);
//            holder.title_listdata.setText(listdata_model.getTitle_list());

            category_suggest_adapter categorySuggestAdapter = new category_suggest_adapter();
            categorySuggestAdapter.setData(listdata_model.getCategory_suggest_models());

            holder.rcvListdata.setAdapter(categorySuggestAdapter);

        }else if(CATEGORY_FOODNEW == holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            holder.rcvListdata.setLayoutManager(linearLayoutManager);

            category_foodnew_adapter categoryFoodnewAdapter = new category_foodnew_adapter();
            categoryFoodnewAdapter.setData(listdata_model.getCategory_foodnew_models());

            holder.rcvListdata.setAdapter(categoryFoodnewAdapter);
        }
    }

    @Override
    public int getItemCount() {
        if(categoryListdataModels != null){
            return categoryListdataModels.size();
        }
        return 0;
    }
}
