package com.example.androidserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.androidserver.Fragment.Cart_Fragment;
import com.example.androidserver.Fragment.Home_Fragment;
import com.example.androidserver.Fragment.Product_Fragment;
import com.example.androidserver.Fragment.User_Fragment;
import com.example.androidserver.Models.User;
import com.example.androidserver.storage.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this,R.color.whiteBodyColor));// set status background white



        //Xet dieu kien dau tien cho fragment_Home khi su kien onStar bat dau
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,
                    new Home_Fragment()).commit();
        }
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            hideSystemUI();
//        }
//    }
//
//    private void hideSystemUI() {
//        // Enables regular immersive mode.
//        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
//        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_IMMERSIVE
//                        // Set the content to appear under the system bars so that the
//                        // content doesn't resize when the system bars hide and show.
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        // Hide the nav bar and status bar
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()) {
                        case R.id.home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,
                                    new Home_Fragment()).commit();
                            break;
                        case R.id.product:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,
                                    new Product_Fragment()).commit();
                             break;
                        case R.id.card:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,
                                    new Cart_Fragment()).commit();
                            break;
                        case R.id.user:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,
                                    new User_Fragment()).commit();
//                            Toast.makeText(Home.this, "User", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return false;
                }
            };

}
