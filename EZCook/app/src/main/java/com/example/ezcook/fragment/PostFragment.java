package com.example.ezcook.fragment;

import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_FOODNEW;
import static com.example.ezcook.adapter.h_category_listdata_adapter.CATEGORY_SUGGEST;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.adapter.h_category_listdata_adapter;
import com.example.ezcook.adapter.h_category_regime_eat_adapter;
import com.example.ezcook.model.h_category_foodnew_model;
import com.example.ezcook.model.h_category_listdata_model;
import com.example.ezcook.model.h_category_regime_eat_model;
import com.example.ezcook.model.h_category_suggest_model;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_post, container, false);

        return view_home;
    }
}
