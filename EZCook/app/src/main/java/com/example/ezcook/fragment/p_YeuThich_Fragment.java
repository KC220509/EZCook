package com.example.ezcook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.adapter.p_AdapterMonAn;
import com.example.ezcook.model.p_MonAn_model;

import java.util.ArrayList;
import java.util.List;

public class p_YeuThich_Fragment extends Fragment {

    private RecyclerView rcvMonAn;
    private View mytView;
    public p_YeuThich_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mytView =  inflater.inflate(R.layout.p_fragment_yeu_thich, container, false);
        rcvMonAn = mytView.findViewById(R.id.recycler_monan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvMonAn.setLayoutManager(linearLayoutManager);

        p_AdapterMonAn adapterMonAn = new p_AdapterMonAn();
        adapterMonAn.setData(getListMonAn());

        rcvMonAn.setAdapter(adapterMonAn);

        return mytView;
    }
    private List<p_MonAn_model> getListMonAn(){
        List<p_MonAn_model> list = new ArrayList<>();
        list.add(new p_MonAn_model("Thịt ","15 phút", "280 kcal", R.drawable.thanh_pham));
        list.add(new p_MonAn_model("Bún bò huế","25 phút", "370 kcal", R.drawable.thanh_pham_bunbo));
        list.add(new p_MonAn_model("Canh mướp đắng","45 phút", "330 kcal", R.drawable.thanh_pham_canhmuopdang));
        list.add(new p_MonAn_model("Sườn rim ngọt","30 phút", "330 kcal", R.drawable.thanh_pham_suonrim));
        list.add(new p_MonAn_model("Thị kho","45 phút", "330 kcal", R.drawable.thanh_pham));
        list.add(new p_MonAn_model("Canh mướp đắng","45 phút", "330 kcal", R.drawable.thanh_pham_canhmuopdang));
        list.add(new p_MonAn_model("Sườn rim ngọt","30 phút", "330 kcal", R.drawable.thanh_pham_suonrim));
        list.add(new p_MonAn_model("Thị kho","45 phút", "330 kcal", R.drawable.thanh_pham));
        return list;
    }
}