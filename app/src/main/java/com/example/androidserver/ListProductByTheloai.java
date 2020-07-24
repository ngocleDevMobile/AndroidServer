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
import android.widget.Toast;

import com.example.androidserver.Adapter.Product_By_Type_Adapter;
import com.example.androidserver.Models.Product_Type;
import com.example.androidserver.Models.Product_Type_Response;
import com.example.androidserver.getData.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductByTheloai extends AppCompatActivity {
    private static final String TAG = ListProductByTheloai.class.getSimpleName();
    RecyclerView recyclerView;
    private ProgressBar progressBar;
    public static TextView tv_Type;
    public static String types;
    String API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_by_theloai);

        progressBar = findViewById(R.id.progressBarProducts);
        recyclerView =  findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tv_Type = findViewById(R.id.tv_Type);
        types = getIntent().getStringExtra("namtype");


        if (getIntent().hasExtra("theloai") && getIntent().hasExtra("namtype")){
            API_KEY = getIntent().getStringExtra("theloai");
            tv_Type.setText(getIntent().getStringExtra("namtype"));
        }


        //set full layout
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(ListProductByTheloai.this,R.color.whiteBodyColor));// set status background white

         getList(API_KEY);


    }

    private void getList(String API){
        RetrofitInterface apiService =
                RetrofitClient.getClient().create(RetrofitInterface.class);

        Call<Product_Type_Response> call = apiService.getProductByType(API);
        call.enqueue(new Callback<Product_Type_Response>() {
            @Override
            public void onResponse(Call<Product_Type_Response> call, Response<Product_Type_Response> response) {
                int statusCode = response.code();
                List<Product_Type> productTypes = response.body().getData();
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(new Product_By_Type_Adapter(productTypes, R.layout.card_product, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<Product_Type_Response> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
