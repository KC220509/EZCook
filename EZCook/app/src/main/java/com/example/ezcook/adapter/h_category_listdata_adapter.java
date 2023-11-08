package com.example.ezcook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.AnimationUtil;
import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.f_StepCookActivity;
import com.example.ezcook.model.h_category_listdata_model;
import com.example.ezcook.model.h_category_suggest_model;
import com.example.ezcook.myinterface.i_ClickItemListener_Suggest;

import java.util.List;

public class h_category_listdata_adapter extends RecyclerView.Adapter<h_category_listdata_adapter.viewholder_listdata> {
    private Context context;
    public static final int CATEGORY_SUGGEST = 1;
    public static final int CATEGORY_FOODNEW = 2;

    private List<h_category_listdata_model> categoryListdataModels;
    MainActivity getmainActivity;

    public h_category_listdata_adapter(Context context, MainActivity mainActivity) {
        this.context = context;
        this.getmainActivity = mainActivity;
    }

    public void setData(List<h_category_listdata_model> categorylistdataModels){
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
        View view_category = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_item_category_listdata, parent, false);
        return new viewholder_listdata(view_category);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_listdata holder, int position) {
        h_category_listdata_model listdata_model = categoryListdataModels.get(position);
        if(listdata_model == null){
            return;
        }

        holder.title_listdata.setText(listdata_model.getTitle_list());

        if (CATEGORY_SUGGEST == holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false);
            holder.rcvListdata.setLayoutManager(linearLayoutManager);

            h_category_suggest_adapter categorySuggestAdapter = new h_category_suggest_adapter();
            categorySuggestAdapter.setData(listdata_model.getCategory_suggest_models()
                    , new h_category_suggest_adapter.i_ClickAddToSave() {
                        @Override
                        public void onClickAddToSave(ImageView imgAddToSave, h_category_suggest_model hCategorySuggestModel) {
                            AnimationUtil.translateAnimation(getmainActivity.getSaveViewAnimation(), imgAddToSave, getmainActivity.getViewEndAnimation(), new Animation.AnimationListener() {

                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                    }, new i_ClickItemListener_Suggest() {
                        @Override
                        public void onClickItemListener_Suggest(h_category_suggest_model icategorySuggestModel) {
                            onClickGotoCookSuggest(icategorySuggestModel);
                        }

                    });
            holder.rcvListdata.setAdapter(categorySuggestAdapter);

        }else if(CATEGORY_FOODNEW == holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            holder.rcvListdata.setLayoutManager(linearLayoutManager);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            holder.rcvListdata.setLayoutManager(gridLayoutManager);

            h_category_foodnew_adapter categoryFoodnewAdapter = new h_category_foodnew_adapter();
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

    private void onClickGotoCookSuggest(h_category_suggest_model hCategorySuggestModel) {
        Intent intent = new Intent(context, f_StepCookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("stepcook_suggest", hCategorySuggestModel);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
