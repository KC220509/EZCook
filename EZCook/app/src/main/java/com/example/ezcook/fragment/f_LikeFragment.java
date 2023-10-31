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
        list.add(new f_Like_model(R.drawable.thanh_pham, "Sườn rim me", "15 phút", "310 kcal", "12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.thanh_pham_bunbo, "Bún bò", "16 phút", "310 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.thanh_pham_suonrim, "Sườn rim", "17 phút", "300 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.thanh_pham_canhmuopdang, "Canh mướp đắng", "18 phút", "30 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.fast_1, "Thiệt xiên nướng", "19 phút", "100 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.fast_2, "Suon Rim", "23 phút", "300 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.fast_3, "Cá viên chiên", "11 phút", "300 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.thanh_pham_suonrim, "Suon Rim", "15 phút", "300 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.fast_1, "Chả cuốn ram", "15 phút", "300 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.fast_2, "Bánh bèo nướng", "15 phút", "300 kcal","12", "123", "1234"));
        list.add(new f_Like_model(R.drawable.thanh_pham_canhmuopdang, "Canh mướp đắng", "15 phút", "300 kcal","12", "123", "1234"));


        return list;
    }
}