package com.example.ezcook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.adapter.f_SaveAdapter;
import com.example.ezcook.model.f_Save_model;

import java.util.ArrayList;
import java.util.List;

public class f_SaveFragment extends Fragment {

    private RecyclerView rcvSave;
    private View mviewSave;
    public f_SaveFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mviewSave = inflater.inflate(R.layout.f_fragment_save, container, false);

        rcvSave = mviewSave.findViewById(R.id.rcv_Save);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvSave.setLayoutManager(linearLayoutManager);

        f_SaveAdapter saveAdapter =  new f_SaveAdapter();
        saveAdapter.setData(getListSave());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rcvSave.setLayoutManager(gridLayoutManager);

        rcvSave.setAdapter(saveAdapter);



        return mviewSave;
    }
    private List<f_Save_model> getListSave(){
        List<f_Save_model> list_save=new ArrayList<>();
        list_save.add(new f_Save_model(R.drawable.fast_3,"Lẩu thái", "15 phút", "300 kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_2,"Canh cá", "25 phút", "320 kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_1,"Sườn rim me", "10 phút", "310 kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_3,"Lẩu thái", "15 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_2,"Canh cá", "25 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_1,"Sườn rim me", "10 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_3,"Lẩu thái", "15 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_2,"Canh cá", "25 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_1,"Sườn rim me", "10 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_3,"Lẩu thái", "15 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_2,"Canh cá", "25 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_1,"Sườn rim me", "10 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_3,"Lẩu thái", "15 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_2,"Canh cá", "25 phút", "300kcal"));
        list_save.add(new f_Save_model(R.drawable.fast_1,"Sườn rim me", "10 phút", "300kcal"));

        return list_save;
    }
}
