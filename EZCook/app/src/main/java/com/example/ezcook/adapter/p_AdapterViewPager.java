package com.example.ezcook.adapter;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ezcook.fragment.p_BaiDang_Fragment;
import com.example.ezcook.fragment.p_YeuThich_Fragment;

public class p_AdapterViewPager extends FragmentStatePagerAdapter {
    public p_AdapterViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new p_BaiDang_Fragment();
            case 1:
                return new p_YeuThich_Fragment();
            default:
                return new p_BaiDang_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Bài đăng";
                break;
            case 1:
                title = "Yêu thích";
                break;
        }

        // Tạo một SpannableString để đặt in đậm cho tiêu đề
        SpannableString spannableTitle = new SpannableString(title);
        spannableTitle.setSpan(new StyleSpan(Typeface.BOLD), 0, spannableTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableTitle;
    }
}
