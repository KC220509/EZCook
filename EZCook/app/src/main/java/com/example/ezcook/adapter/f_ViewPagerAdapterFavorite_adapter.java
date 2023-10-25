package com.example.ezcook.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ezcook.fragment.f_CollectionFragment;
import com.example.ezcook.fragment.f_LikeFragment;
import com.example.ezcook.fragment.f_SaveFragment;

public class f_ViewPagerAdapterFavorite_adapter extends FragmentStatePagerAdapter {
    public f_ViewPagerAdapterFavorite_adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new f_LikeFragment();
            case 1:
                return new f_SaveFragment();
            case 2:
                return new f_CollectionFragment();
            default:
                return new f_LikeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title_Fr = "";
        switch (position){
            case 0:
                title_Fr = "Yêu thích";break;
            case 1:
                title_Fr = "Đã lưu";break;
            case 2:
                title_Fr = "Bộ sưu tập";break;
        }
        return title_Fr;
    }
}
