package com.example.ezcook.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.adapter.p_AdapterViewPager;
import com.example.ezcook.p_AuthorActivity;
import com.example.ezcook.p_SettingActivity;
import com.example.ezcook.p_SettingUserActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {
//    public static final int MY_REQUEST_CODE = 10;
//    private ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if(result.getResultCode() == RESULT_OK){
//                Intent intent = result.getData();
//                if(intent == null){
//                    return;
//                }
//                Uri uri = intent.getData();
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(, uri);
//
//            }
//        }
//    });

    private View view_home;
    private MainActivity mainActivity;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private p_AdapterViewPager mViewPagerAdapter;
    private Button bottomsheet;
    private Button btn_cscanhan;
    private ImageView imageuserprofile;
    private TextView idnameuserprofile, nameuserprofile, emailuserprofile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view_home = inflater.inflate(R.layout.fragment_profile, container, false);
        mainActivity = (MainActivity) getActivity();

        Anhxa(view_home);
        Action();
        showUserProfileInfo();

        return view_home;
    }
    private void Anhxa(View view){
        idnameuserprofile = view.findViewById(R.id.id_username);

        imageuserprofile = view.findViewById(R.id.imageUserProfile);
        nameuserprofile = view.findViewById(R.id.nameUserProfile);
        emailuserprofile = view.findViewById(R.id.emailUserProfile);

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
        LinearLayout layoutAuthor = dialog.findViewById(R.id.layoutAuthor);

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
        layoutAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintenSettingUser = new Intent(mainActivity, p_AuthorActivity.class);
                startActivity(myintenSettingUser);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    public void showUserProfileInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        final String url = "https://kcfullstack.000webhostapp.com/getDataUser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i< response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String uid = object.getString("UID");
                                String nameprofile = object.getString("TENDANGNHAP");
                                String emailprofile = object.getString("EMAIL");
                                String avt = object.getString("AVT");
                                if (uid.equals(user.getUid())) {
                                    idnameuserprofile.setText(nameprofile);
                                    nameuserprofile.setText(nameprofile);
                                    emailuserprofile.setText(emailprofile);
                                    if (avt == null || avt.equals("NULL") || avt.isEmpty()){
                                        avt = String.valueOf(R.drawable.h_account_circle_24);
                                    }
                                    Picasso.get().load(avt).into(imageuserprofile);
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.toString());
                        Toast.makeText(mainActivity, "Lỗi truy cập đường dẫn !", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);

    }
    @Override
    public void onResume() {
        super.onResume();
        showUserProfileInfo();
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
//
//    }

}
