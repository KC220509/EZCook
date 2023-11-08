package com.example.ezcook.adapter;

import com.example.ezcook.R;
import com.example.ezcook.model.h_category_suggest_model;
import com.example.ezcook.myinterface.i_ClickItemListener_Suggest;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class h_category_suggest_adapter extends RecyclerView.Adapter<h_category_suggest_adapter.viewholder_suggest>{
    private i_ClickAddToSave iClickAddToSave;
    public interface i_ClickAddToSave{
        void onClickAddToSave(ImageView imgAddToSave, h_category_suggest_model hCategorySuggestModel);
    }

    private List<h_category_suggest_model> category_suggest_list;
    public i_ClickItemListener_Suggest iClickItemListenerSuggest;
    public void setData(List<h_category_suggest_model> suggest_list,  i_ClickAddToSave iClickAddToSave, i_ClickItemListener_Suggest iClickItemListenerSuggest){
        this.category_suggest_list = suggest_list;
        this.iClickAddToSave = iClickAddToSave;
        this.iClickItemListenerSuggest = iClickItemListenerSuggest;

        notifyDataSetChanged();
    }
    public class viewholder_suggest extends RecyclerView.ViewHolder{

        private ImageView  category_suggest_pic, category_suggest_picLevel;
        private TextView category_suggest_title, category_suggest_time, category_suggest_kcal;
        private CardView itemsuggest;
        private ImageView imgAddToSave;
        public viewholder_suggest(@NonNull View itemView){
            super(itemView);
            itemsuggest = itemView.findViewById(R.id.item_suggest);
             imgAddToSave = itemView.findViewById(R.id.image_save_suggest);

            category_suggest_pic = itemView.findViewById(R.id.category_suggest_pic);
            category_suggest_title = itemView.findViewById(R.id.category_suggest_title);
            category_suggest_time = itemView.findViewById(R.id.category_suggest_time);
            category_suggest_kcal = itemView.findViewById(R.id.category_suggest_kcal);
            category_suggest_picLevel = itemView.findViewById(R.id.category_suggest_picLevel);

        }
    }

    @NonNull
    @Override
    public viewholder_suggest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_suggest = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_item_category_suggest, parent, false);
        return new viewholder_suggest(view_suggest);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_suggest holder, int position) {
        final h_category_suggest_model categorySuggestModel = category_suggest_list.get(position);
        if(categorySuggestModel == null){
            return;
        }
        holder.category_suggest_title.setText(categorySuggestModel.getTitle());
        holder.category_suggest_time.setText(categorySuggestModel.getTime());
        holder.category_suggest_pic.setImageResource(categorySuggestModel.getPic());
        holder.category_suggest_kcal.setText(categorySuggestModel.getKcal());
        holder.category_suggest_picLevel.setImageResource(categorySuggestModel.getPic_level());

        holder.imgAddToSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!categorySuggestModel.isAddToSave()) {
                    iClickAddToSave.onClickAddToSave(holder.imgAddToSave, categorySuggestModel);
                }
            }
        });

        holder.itemsuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    iClickItemListenerSuggest.onClickItemListener_Suggest(categorySuggestModel);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(category_suggest_list != null){
            return category_suggest_list.size();
        }
        return 0;
    }

}
