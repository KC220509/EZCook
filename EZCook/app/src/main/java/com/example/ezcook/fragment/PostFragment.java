package com.example.ezcook.fragment;

import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_FOODNEW;
import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_SUGGEST;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.StartActivity;
import com.example.ezcook.adapter.h_category_listdata_adapter;
import com.example.ezcook.adapter.h_category_regime_eat_adapter;
import com.example.ezcook.model.h_category_foodnew_model;
import com.example.ezcook.model.h_category_listdata_model;
import com.example.ezcook.model.h_category_regime_eat_model;
import com.example.ezcook.model.h_category_suggest_model;
import com.example.ezcook.p_share_recipe;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {
    Button btn_sharecongthuc;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_bg_post, container, false);
        mainActivity = (MainActivity) getActivity();
        Anhxa(view_home);
        Action();
        return view_home;
    }
    private void Anhxa(View view){
        btn_sharecongthuc = view.findViewById(R.id.btn_sharecongthucmoi);
    }
    private void Action(){
        btn_sharecongthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, p_share_recipe.class);
                startActivity(intent);
            }
        });
    }
}
