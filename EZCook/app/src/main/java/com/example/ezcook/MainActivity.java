package com.example.ezcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ezcook.adapter.h_MyViewPagerMain_adapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 view_main;
    private BottomNavigationView bottomNavigationView;
    private boolean isChangingPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_main);

        Anhxa();
        Action();
    }
    private void Anhxa(){
        view_main =  findViewById(R.id.viewpager2_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void Action() {
        // Ngăn người dùng vuốt trang
        view_main.setUserInputEnabled(false);

        h_MyViewPagerMain_adapter myViewPagerAdapter = new h_MyViewPagerMain_adapter(this);
        view_main.setAdapter(myViewPagerAdapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                int position = getPositionFromItemId(id);
                if (position != -1) {
                    view_main.setCurrentItem(position, false);
                }
                return true;
            }
        });
    }
    private int[] fragmentIds = {
            R.id.bottom_home,
            R.id.bottom_trend,
            R.id.bottom_post,
            R.id.bottom_note,
            R.id.bottom_profile
    };
    private int getPositionFromItemId(int itemId) {
        for (int i = 0; i < fragmentIds.length; i++) {
            if (itemId == fragmentIds[i]) {
                return i;
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy ID phù hợp.
    }


    //    private void Action(){
//        //ngăn người dùng vuốt trang
//        view_main.setUserInputEnabled(false);
//
//        MyViewPager_adapter myViewPagerAdapter = new MyViewPager_adapter(this);
//        view_main.setAdapter(myViewPagerAdapter);
//
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.bottom_home){
//                    view_main.setCurrentItem(0);
//                } else if (id == R.id.bottom_trend) {
//                    view_main.setCurrentItem(1);
//                } else if (id == R.id.bottom_post) {
//                    view_main.setCurrentItem(2);
//                } else if (id == R.id.bottom_note) {
//                    view_main.setCurrentItem(3);
//                } else if (id == R.id.bottom_profile) {
//                    view_main.setCurrentItem(4);
//                }
//
//                return true;
//            }
//
//        });
//        view_main.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                switch (position){
//                    case 0:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(false);
//                        break;
//                    case 1:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_trend).setChecked(true);
//                        break;
//                    case 2:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_post).setChecked(true);
//                        break;
//                    case 3:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_note).setChecked(true);
//                        break;
//                    case 4:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_profile).setChecked(true);
//                        break;
//
//                }
//            }
//        });
//    }
}









