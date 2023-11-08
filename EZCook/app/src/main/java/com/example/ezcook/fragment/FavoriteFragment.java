package com.example.ezcook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.adapter.f_ViewPagerAdapterFavorite_adapter;
import com.example.ezcook.f_CreateActivity;
import com.google.android.material.tabs.TabLayout;
public class FavoriteFragment extends Fragment {
    private MainActivity mainActivity;

    private TabLayout mTabLayout_Favotie;
    private ViewPager mViewPager_Favotie;
    private ImageView im_btn_create;
    private static final int REQUEST_CREATE_ACTIVITY = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_favorite, container, false);
        mainActivity = (MainActivity) getActivity();

        Anhxa(view_home);
        Action();
        return view_home;
    }
    private void Anhxa(View view){
        im_btn_create = view.findViewById(R.id.image_btn_create);
        mTabLayout_Favotie = view.findViewById(R.id.tabLayout_library);
        mViewPager_Favotie = view.findViewById(R.id.viewpager_library);
    }
    private void Action(){

        im_btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, f_CreateActivity.class);
//                startActivityForResult(intent, REQUEST_CREATE_ACTIVITY);
                startActivity(intent);
//                mainActivity.finish();
            }
        });

        f_ViewPagerAdapterFavorite_adapter fViewPagerAdapterFavoriteAdapter = new f_ViewPagerAdapterFavorite_adapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager_Favotie.setAdapter(fViewPagerAdapterFavoriteAdapter);
        mTabLayout_Favotie.setupWithViewPager(mViewPager_Favotie);
    }

}
