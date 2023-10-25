package com.example.ezcook.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ezcook.fragment.FavoriteFragment;
import com.example.ezcook.fragment.HomeFragment;
import com.example.ezcook.fragment.PostFragment;
import com.example.ezcook.fragment.ProfileFragment;
import com.example.ezcook.fragment.TrendFragment;

public class h_MyViewPagerMain_adapter extends FragmentStateAdapter {
    public h_MyViewPagerMain_adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new TrendFragment();
            case 2:
                return new PostFragment();
            case 3:
                return new FavoriteFragment();
            case 4:
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
