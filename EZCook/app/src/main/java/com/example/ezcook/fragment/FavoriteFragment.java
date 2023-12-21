package com.example.ezcook.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.ezcook.MainActivity;
import com.example.ezcook.R;
import com.example.ezcook.adapter.f_ViewPagerAdapterFavorite_adapter;
import com.example.ezcook.f_CreateActivity;
import com.example.ezcook.fcm.ImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FavoriteFragment extends Fragment {
    private MainActivity mainActivity;

    private TabLayout mTabLayout_Favotie;
    private ViewPager mViewPager_Favotie;
    private ImageView imageUserFavorite, im_btn_create;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_favorite, container, false);
        mainActivity = (MainActivity) getActivity();

        Anhxa(view_home);
        Action();
        showUserImage();
        return view_home;
    }
    private void Anhxa(View view){
        imageUserFavorite =view.findViewById(R.id.imageAVT);
        im_btn_create = view.findViewById(R.id.image_btn_create);
        mTabLayout_Favotie = view.findViewById(R.id.tabLayout_library);
        mViewPager_Favotie = view.findViewById(R.id.viewpager_library);
    }
    private void Action(){

        im_btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, f_CreateActivity.class);
                startActivity(intent);
//                mainActivity.finish();
            }
        });

        f_ViewPagerAdapterFavorite_adapter fViewPagerAdapterFavoriteAdapter = new f_ViewPagerAdapterFavorite_adapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager_Favotie.setAdapter(fViewPagerAdapterFavoriteAdapter);
        mTabLayout_Favotie.setupWithViewPager(mViewPager_Favotie);
    }
    public void showUserImage(){
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
                                String avt = object.getString("AVT");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (uid.equals(user.getUid())) {
                                    if(avt.equals("") || avt == null){
                                        Picasso.get().load(R.drawable.h_account_circle_24).into(imageUserFavorite);
                                    }
                                    else {
                                        Picasso.get().load(Uri.parse(avt)).into(imageUserFavorite);
                                    }

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

}
