package com.example.androidserver.Fragment;

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
import com.example.androidserver.Models.LoginResponse;
import com.example.androidserver.R;
import com.example.androidserver.RetrofitInterface;
import com.example.androidserver.api.RetrofitClient;
import com.example.androidserver.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginFragment extends Fragment {



    Button loginBtn;
     EditText emailEdit,passwordEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginBtn = view.findViewById(R.id.login);
        emailEdit = view.findViewById(R.id.emailEdit);
        passwordEdit = view.findViewById(R.id.passwordEdit);
//        handleLoginDialog();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            Intent intent = new Intent(getActivity(), Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private void userLogin() {

        String email = emailEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();

        if (email.isEmpty()) {
            emailEdit.setError("Email không để trống!");
            emailEdit.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEdit.setError("Email sai định dạng!");
            emailEdit.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEdit.setError("Password không để trống!");
            passwordEdit.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordEdit.setError("Password phải hơn 6 ký tự!");
            passwordEdit.requestFocus();
            return;
        }
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (!loginResponse.isError()) {
                    SharedPrefManager.getInstance(getActivity())
                            .saveUser(loginResponse.getUser());
                    Intent intent = new Intent(getActivity(), Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }


}
