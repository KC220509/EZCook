package com.example.ezcook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.adapter.f_CollectionAdapter;
import com.example.ezcook.model.f_Collection_model;

import java.util.ArrayList;
import java.util.List;

public class f_CollectionFragment extends Fragment {

    private RecyclerView rcvCollection;
    private View mviewCollection;
private Button btn_create;

    public f_CollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mviewCollection = inflater.inflate(R.layout.f_fragment_collection, container, false);

        rcvCollection = mviewCollection.findViewById(R.id.rcv_Collection);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvCollection.setLayoutManager(linearLayoutManager);

        f_CollectionAdapter collectionAdapter=  new f_CollectionAdapter();
        collectionAdapter.setData_Collection(getListCollection());

        rcvCollection.setAdapter(collectionAdapter);
        //chia cot
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        rcvCollection.setLayoutManager(gridLayoutManager);



        return mviewCollection;
    }
    private List<f_Collection_model> getListCollection(){
        List<f_Collection_model> list_col = new ArrayList<>();

        list_col.add(new f_Collection_model(R.drawable.fast_2,"An vat","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_1,"Món chính","Công khai"));
        list_col.add(new f_Collection_model(R.drawable.fast_3,"N","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_2,"An vat","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_1,"Món chính","Công khai"));
        list_col.add(new f_Collection_model(R.drawable.fast_3,"N","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_2,"An vat","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_1,"Món chính","Công khai"));
        list_col.add(new f_Collection_model(R.drawable.fast_3,"N","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_2,"An vat","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_1,"Món chính","Công khai"));
        list_col.add(new f_Collection_model(R.drawable.fast_3,"N","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_2,"An vat","Chỉ mình tôi"));
        list_col.add(new f_Collection_model(R.drawable.fast_1,"Món chính","Công khai"));
        list_col.add(new f_Collection_model(R.drawable.fast_3,"N","Chỉ mình tôi"));
        return list_col;
    }
}