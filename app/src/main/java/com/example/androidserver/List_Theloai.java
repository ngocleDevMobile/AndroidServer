package com.example.androidserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidserver.Adapter.Theloai_Adapter;
import com.example.androidserver.Models.Theloai;
import com.example.androidserver.Models.Theloai_Res;
import com.example.androidserver.getData.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Theloai extends AppCompatActivity {

    private RecyclerView rvTheloailist;
    private ProgressBar progressBar;
    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__theloai);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(List_Theloai.this,R.color.whiteBodyColor));// set status background white


        rvTheloailist = findViewById(R.id.rvTypeList);
        progressBar = findViewById(R.id.progressBarType);
        back = findViewById(R.id.tv_theloai);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



//        final Theloai_Adapter adapter = new Theloai_Adapter();
       rvTheloailist.setLayoutManager(new LinearLayoutManager(this));
//        rvTheloailist.setAdapter(adapter);



        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        client.fetchType(10).enqueue(new Callback<Theloai_Res>() {
            @Override
            public void onResponse(Call<Theloai_Res> call, Response<Theloai_Res> response) {
                List<Theloai> mlist = response.body().getData();
                progressBar.setVisibility(View.INVISIBLE);
                rvTheloailist.setAdapter(new Theloai_Adapter(mlist,R.layout.card_theloai,getApplicationContext()));

//                adapter.setData(mlist);
            }

            @Override
            public void onFailure(Call<Theloai_Res> call, Throwable t) {
                Log.e("Main", t.toString());
            }
        });
    }
}
