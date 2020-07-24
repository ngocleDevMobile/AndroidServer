package com.example.androidserver.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidserver.Adapter.News_Adapter;
import com.example.androidserver.Adapter.Product_Adapter;
import com.example.androidserver.Adapter.Product_By_Type_Adapter;
import com.example.androidserver.Edit_user;
import com.example.androidserver.List_Theloai;
import com.example.androidserver.Models.DefaultResponse;
import com.example.androidserver.Models.News;
import com.example.androidserver.Models.News_Item;
import com.example.androidserver.Models.Product;
import com.example.androidserver.Models.Product_Res;
import com.example.androidserver.Models.Product_Type;
import com.example.androidserver.R;
import com.example.androidserver.RetrofitInterface;
import com.example.androidserver.getData.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_Fragment extends Fragment {

    RecyclerView rvProductList;
    private ProgressBar progressBar;
    private TextView tv_theloai;
    EditText search_field;
    List<Product> mlist;

    Product_Adapter product_adapter;
    Context mContext = getActivity() ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);

        rvProductList = view.findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(mContext));
        progressBar = view.findViewById(R.id.progressBarProduct);
        tv_theloai = view.findViewById(R.id.tv_theloai);
        search_field = view.findViewById(R.id.search_field);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    private void getData(){
        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        client.fetchProduct(10).enqueue(new Callback<Product_Res>() {
            @Override
            public void onResponse(Call<Product_Res> call, Response<Product_Res> response) {
                if (response.code() == 200) {
                    mlist = response.body().getData();
                    progressBar.setVisibility(View.INVISIBLE);

                    product_adapter = new Product_Adapter(mlist, getActivity().getApplicationContext());

                    rvProductList.setAdapter(product_adapter);
                    //Toast.makeText(getActivity(), "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 404) {
                    Toast.makeText(getContext(), "Chưa thành công", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Product_Res> call, Throwable t) {
                Log.e("Main", t.toString());
            }
        });

        search_field.setImeOptions(EditorInfo.IME_ACTION_DONE);
        search_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {
        ArrayList<Product> filteredList = new ArrayList<>();

        for (Product item : mlist) {
            if (item.getNamesp().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        product_adapter.filterList(filteredList);
    }
}
