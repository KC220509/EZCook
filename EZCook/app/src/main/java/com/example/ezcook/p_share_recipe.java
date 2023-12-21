package com.example.ezcook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class p_share_recipe extends AppCompatActivity {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    Button btnbackpost, openImage;
    ImageView hinhanhmon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity_action);

        Anhxa();
        Action();
    }
    private void Anhxa(){

        btnbackpost = findViewById(R.id.btn_backpost);
        openImage = findViewById(R.id.openImage);
        hinhanhmon = findViewById(R.id.editHINHANHMON);
    }
    private void Action(){
        btnbackpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        openImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_CODE_PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            // Hiển thị ảnh trên ImageView
            if (selectedImageUri != null) {
//                ImageView imageView = findViewById(R.id.editHINHANHMON);
                hinhanhmon.setVisibility(View.VISIBLE);  // Hiển thị ImageView
                hinhanhmon.setImageURI(selectedImageUri);  // Đặt ảnh cho ImageView
            }

            // Hiển thị thông báo hoặc thực hiện các xử lý khác
//            Toast.makeText(this, "Ảnh đã được chọn!", Toast.LENGTH_SHORT).show();
        }
    }

}
