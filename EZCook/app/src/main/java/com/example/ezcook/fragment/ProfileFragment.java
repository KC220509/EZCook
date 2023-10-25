package com.example.ezcook.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.adapter.p_AdapterViewPager;
import com.example.ezcook.p_SettingActivity;
import com.example.ezcook.p_SettingUserActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {
    private View view_home;
    private MainActivity mainActivity;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private p_AdapterViewPager mViewPagerAdapter;
    private Button bottomsheet;
    private Button btn_cscanhan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view_home = inflater.inflate(R.layout.fragment_profile, container, false);
        mainActivity = (MainActivity) getActivity();

        Anhxa(view_home);
        Action();
        return view_home;
    }
    private void Anhxa(View view){
        bottomsheet = view.findViewById(R.id.bottom_sheet);
        btn_cscanhan = view.findViewById(R.id.btn_cscanhan);

        mTabLayout = view.findViewById(R.id.tablayout);
        mViewPager = view.findViewById(R.id.viewpaper);
    }

    private void Action(){

        bottomsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });



        btn_cscanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintenSettingUser = new Intent(mainActivity, p_SettingUserActivity.class);
                startActivity(myintenSettingUser);
            }
        });

        setTabLayAnimation();


        mViewPagerAdapter = new p_AdapterViewPager(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setTabLayAnimation() {
        final CollapsingToolbarLayout collapsingToolbarLayout = view_home.findViewById(R.id.CollapsingToolbarLayout);
        int myColor = getResources().getColor(R.color.colorPrimary); // Sử dụng màu cố định
        collapsingToolbarLayout.setContentScrimColor(myColor);
    }



    private void showDialog() {
        final Dialog dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.p_bottomsheetlayout);

        LinearLayout layoutSetting = dialog.findViewById(R.id.layoutSetting);
        LinearLayout layoutCaNhan = dialog.findViewById(R.id.layoutCaNhan);
        LinearLayout layoutQR = dialog.findViewById(R.id.layoutQR);
        LinearLayout layoutHD = dialog.findViewById(R.id.layoutHD);

        layoutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintenSetting = new Intent(mainActivity, p_SettingActivity.class);
                startActivity(myintenSetting);
            }
        });

        layoutCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintenSettingUser = new Intent(mainActivity, p_SettingUserActivity.class);
                startActivity(myintenSettingUser);
            }
        });

        layoutQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity,"QR is click",Toast.LENGTH_SHORT).show();
            }
        });

        layoutHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity,"Hoạt động is click",Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}
