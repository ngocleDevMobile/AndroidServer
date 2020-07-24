package com.example.androidserver.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidserver.Edit_user;
import com.example.androidserver.MainActivity;
import com.example.androidserver.R;
import com.example.androidserver.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_Fragment extends Fragment {
    TextView editUser, tvUsername, tvEmail, tvLogout;
    private SharedPrefManager sharedPrefManager;
    CircleImageView imgprofile;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);
        editUser = view.findViewById(R.id.edtProfile);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvLogout = view.findViewById(R.id.tv_logout);
        imgprofile = view.findViewById(R.id.profileCircleImageView);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/loginphone-51f0e.appspot.com/o/Asset%204%40400x.png?alt=media&token=2b8e9f30-1a39-4539-891d-b3947594cdb9").into(imgprofile);

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Edit_user.class);
                startActivity(i);
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getActivity().getApplicationContext()).logout();
                getActivity().finish();
                startActivity(new Intent(getActivity(),MainActivity.class));


            }
        });
        tvUsername.setText("Hello "+ SharedPrefManager.getInstance(getActivity()).getUser().getUsername());
        tvEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());
        return view;
    }
}
