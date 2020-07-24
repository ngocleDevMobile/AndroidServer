package com.example.androidserver.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidserver.Home;
import com.example.androidserver.Models.DefaultResponse;
import com.example.androidserver.R;
import com.example.androidserver.RetrofitInterface;
import com.example.androidserver.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Register extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.7:5000";

    //
    Button signupBtn;
    EditText nameEdit,emailEdit,passwordEdit,passwordEdit2;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);


         signupBtn = view.findViewById(R.id.signup);
         nameEdit = view.findViewById(R.id.nameEdit);
         emailEdit = view.findViewById(R.id.emailEdit);
         passwordEdit = view.findViewById(R.id.passwordEdit);
         passwordEdit2 = view.findViewById(R.id.passwordEdit2);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();

            }
        });
        return view;
    }



    //Tao user
    private void userSignUp() {
        String email = emailEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String name = nameEdit.getText().toString().trim();
        String password2 = passwordEdit2.getText().toString().trim();


        //Bat loi
        if (email.isEmpty()) {
            emailEdit.setError("Email không để trống");
            emailEdit.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEdit.setError("Nhập đúng định dạng email");
            emailEdit.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEdit.setError("Mật khẩu không để trống");
            passwordEdit.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordEdit.setError("Mật khẩu lớn hơn 6 ký tự");
            passwordEdit.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            nameEdit.setError("Không để trống!");
            nameEdit.requestFocus();
            return;
        }
        if (password2.isEmpty()) {
            passwordEdit2.setError("Không để trống!");
            passwordEdit2.requestFocus();
            return;
        }
        if (!password2.equals(password)) {
            passwordEdit2.setError("Mật khẩu không trùng khớp!");
            passwordEdit2.requestFocus();
            Toast.makeText(getActivity(), password + password2, Toast.LENGTH_SHORT).show();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, password, name);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                    DefaultResponse dr = response.body();

                } else if (response.code() == 422) {
                    Toast.makeText(getContext(), "Chưa thành công", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }


}