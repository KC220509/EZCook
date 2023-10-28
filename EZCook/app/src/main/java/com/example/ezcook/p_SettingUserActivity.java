package com.example.ezcook;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ezcook.fragment.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class p_SettingUserActivity extends AppCompatActivity {

    View view_home;

    Button quaylai_btn;

    //profile
    private ImageView imageprofile;
    private EditText nameprofile, idprofile, emailprofile, phoneprofile, gioithieuprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_setting_user);

        Anhxa();
        Action();
    }

    private void Anhxa() {
        quaylai_btn = findViewById(R.id.quaylai_btn);
        imageprofile = findViewById(R.id.imgage_Profile);
        nameprofile = findViewById(R.id.editTextNameProfile);
        idprofile = findViewById(R.id.editTextIDProfile);
        emailprofile = findViewById(R.id.editTextEmailProfile);
        phoneprofile = findViewById(R.id.editTextPhoneProfile);
        gioithieuprofile = findViewById(R.id.editTextMotaProfile);
    }

    //    private void Anhxa(View view){
//        quaylai_btn = view.findViewById(R.id.quaylai_btn);
//        imageprofile = view.findViewById(R.id.imgage_Profile);
//        nameprofile = view.findViewById(R.id.editTextNameProfile);
//        idprofile = view.findViewById(R.id.editTextIDProfile);
//        emailprofile = view.findViewById(R.id.editTextEmailProfile);
//        phoneprofile = view.findViewById(R.id.editTextPhoneProfile);
//        gioithieuprofile = view.findViewById(R.id.editTextMotaProfile);
//    }
    private void Action() {
        quaylai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent = new Intent(getActivity(), ProfileFragment.class);
//                StartActivity(intent);
            }
        });

//        imageprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickRequestPermission();
//            }
//
//        });

    }


//    private void setUserProfileInfomation() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user == null){
//            return;
//        }
//        nameprofile.setText(user.getDisplayName());
//        idprofile.setText(user.getUid());
//        emailprofile.setText(user.getEmail());
//        phoneprofile.setText(user.getPhoneNumber());
//        Glide.with(this).load(R.drawable.h_account_circle_24).into(imageprofile);
////        gioithieuprofile.setText(user.get());
//    }
//    private void onClickRequestPermission() {
//        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentProfile);
////        ProfileFragment profileFragment = new ProfileFragment();
//        if (profileFragment == null){
//            return;
//        }
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
//            profileFragment.openGallery();
//            return;
//        }
//        if(this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            profileFragment.openGallery();
//
//        }else{
//            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
//            requestPermissions(permission, MY_REQUEST_CODE);
//        }
//    }
}