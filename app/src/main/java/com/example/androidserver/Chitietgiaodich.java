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

import com.example.androidserver.Adapter.Giaodich_Adapter;
import com.example.androidserver.Adapter.Product_By_Type_Adapter;
import com.example.androidserver.Models.Giaodich;
import com.example.androidserver.Models.Giaodich_Res;
import com.example.androidserver.Models.Product_Type;
import com.example.androidserver.Models.Product_Type_Response;
import com.example.androidserver.getData.RetrofitClient;
import com.example.androidserver.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chitietgiaodich extends AppCompatActivity {
    private static final String TAG = Chitietgiaodich.class.getSimpleName();
    RecyclerView rvGiaodich;
    private ProgressBar progressBar;
    public static TextView tv_Type;
    public static String types;
    String iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietgiaodich);

        //set full layout
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Chitietgiaodich.this,R.color.whiteBodyColor));// set status background white


        progressBar = findViewById(R.id.progressBarGiaodich);
        rvGiaodich =  findViewById(R.id.rvGiaodich);
        rvGiaodich.setLayoutManager(new LinearLayoutManager(Chitietgiaodich.this));


        iduser = SharedPrefManager.getInstance(Chitietgiaodich.this).getUser().get_id();

        getList(iduser);


    }

    private void getList(String iduser){
        RetrofitInterface apiService =
                RetrofitClient.getClient().create(RetrofitInterface.class);

        Call<Giaodich_Res> call = apiService.getGiaodich(iduser);
        call.enqueue(new Callback<Giaodich_Res>() {
            @Override
            public void onResponse(Call<Giaodich_Res> call, Response<Giaodich_Res> response) {
                int statusCode = response.code();
                List<Giaodich> productTypes = response.body().getData();
                progressBar.setVisibility(View.INVISIBLE);
                rvGiaodich.setAdapter(new Giaodich_Adapter(productTypes, R.layout.card_giaodich, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<Giaodich_Res> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
