package com.example.ezcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ezcook.adapter.h_MyViewPagerMain_adapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
//    public static final int MY_REQUEST_CODE = 10;
//    public ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode() == RESULT_OK){
//                        Intent intent = result.getData();
//                        if(intent == null){
//                            return;
//                        }
//                        Uri uri = intent.getData();
//                        try {
//                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//
//                    }
//                }
//            });
    private ViewPager2 view_main;
    private BottomNavigationView bottomNavigationView;
    private boolean isChangingPage = false;

    private View viewEndAnimation;
    private ImageView SaveViewAnimation;

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

        viewEndAnimation = findViewById(R.id.view_end_animation);
        SaveViewAnimation = findViewById(R.id.save_view_animation);
    }

    public View getViewEndAnimation() {
        return viewEndAnimation;
    }

    public ImageView getSaveViewAnimation() {
        return SaveViewAnimation;
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

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == MY_REQUEST_CODE){
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                openGallery();
//            }else {
//
//            }
//        }
//    }
//    public void openGallery(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intentActivityResultLauncher.launch(Intent.createChooser(intent, "Select image"));
//    }

}









