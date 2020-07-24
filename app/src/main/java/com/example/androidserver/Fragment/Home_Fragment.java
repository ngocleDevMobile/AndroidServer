package com.example.androidserver.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidserver.Adapter.News_Adapter;
import com.example.androidserver.Adapter.SliderAdapterExample;
import com.example.androidserver.Chitietgiaodich;
import com.example.androidserver.ListProductByTheloai;
import com.example.androidserver.List_Theloai;
import com.example.androidserver.Models.News;
import com.example.androidserver.Models.News_Item;
import com.example.androidserver.Models.SliderItem;
import com.example.androidserver.R;
import com.example.androidserver.RetrofitInterface;
import com.example.androidserver.getData.RetrofitClient;
import com.example.androidserver.storage.SharedPrefManager;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment {


    private ProgressBar progressBar;
    TextView username;
    Button btnTheloai,btnGiaodich;
    private RecyclerView rvUserList;
    SliderView sliderView;
    private SliderAdapterExample adapter;

    CircleImageView imgName;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(false);


        rvUserList = view.findViewById(R.id.rvUserList);
        progressBar = view.findViewById(R.id.progressBar);
        username = view.findViewById(R.id.textViewUser);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListProductByTheloai.class);
                startActivity(i);
            }
        });
        username.setText("Hello "+SharedPrefManager.getInstance(getActivity()).getUser().getUsername());
        imgName = view.findViewById(R.id.imgName);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/loginphone-51f0e.appspot.com/o/Asset%204%40400x.png?alt=media&token=2b8e9f30-1a39-4539-891d-b3947594cdb9").into(imgName);


        btnTheloai = view.findViewById(R.id.btnTheloai);
        btnTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), List_Theloai.class);
                startActivity(i);
            }
        });

        btnGiaodich = view.findViewById(R.id.btnGiaodich);
        btnGiaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Chitietgiaodich.class);
                startActivity(i);

            }
        });
        final News_Adapter adapter = new News_Adapter();

        rvUserList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUserList.setAdapter(adapter);

        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        client.fetchNews(10).enqueue(new Callback<News_Item>() {
            @Override
            public void onResponse(Call<News_Item> call, Response<News_Item> response) {
                List<News> mlist = response.body().getData();
                progressBar.setVisibility(View.INVISIBLE);
                adapter.setData(mlist);
            }

            @Override
            public void onFailure(Call<News_Item> call, Throwable t) {
                Log.e("Main", t.toString());
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            } else {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }






}
