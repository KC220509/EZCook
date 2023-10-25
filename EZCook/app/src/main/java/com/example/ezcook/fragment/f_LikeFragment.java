package com.example.ezcook.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ezcook.R;
import com.example.ezcook.model.f_Like_model;

import java.util.ArrayList;
import java.util.List;

import com.example.ezcook.adapter.f_LikeAdapter;
import com.example.ezcook.model.f_Like_model;

public class f_LikeFragment extends Fragment {

    private RecyclerView rcvLike;
    private View mviewLike;

    public f_LikeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mviewLike = inflater.inflate(R.layout.f_fragment_like, container, false);

        rcvLike = mviewLike.findViewById(R.id.rcv_Like);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvLike.setLayoutManager(linearLayoutManager);

        f_LikeAdapter likeAdapter =  new f_LikeAdapter();
        likeAdapter.setData(getListLike());

        rcvLike.setAdapter(likeAdapter);



        return mviewLike;
    }

    private List<f_Like_model> getListLike(){
        List<f_Like_model> list =new ArrayList<>();
        list.add(new f_Like_model(R.drawable.cat_1, "Sườn rim me", "15 phút", "310 kcal", "12", "20", "100" ));
        list.add(new f_Like_model(R.drawable.cat_2, "Chả cuốn", "16 phút", "310 kcal", "13", "210", "1100" ));
        list.add(new f_Like_model(R.drawable.cat_3, "Cơm rang", "17 phút", "300 kcal", "14", "10", "100" ));
        list.add(new f_Like_model(R.drawable.cat_4, "Suon Rim", "18 phút", "30 kcal", "15", "60", "1300" ));
        list.add(new f_Like_model(R.drawable.fast_1, "Suon Rim", "19 phút", "100 kcal", "15", "10", "1400" ));
        list.add(new f_Like_model(R.drawable.fast_2, "Suon Rim", "23 phút", "300 kcal", "14", "20", "1040" ));
        list.add(new f_Like_model(R.drawable.fast_3, "Suon Rim", "11 phút", "300 kcal", "13", "14", "180" ));
        list.add(new f_Like_model(R.drawable.cat_1, "Suon Rim", "15 phút", "300 kcal", "10", "10", "100" ));
        list.add(new f_Like_model(R.drawable.cat_3, "Suon Rim", "15 phút", "300 kcal", "09", "20", "1020" ));
        list.add(new f_Like_model(R.drawable.cat_2, "Suon Rim", "15 phút", "300 kcal", "12", "20", "100" ));
        list.add(new f_Like_model(R.drawable.cat_1, "Suon Rim", "15 phút", "300 kcal", "12", "20", "100" ));


        return list;
    }
}