package com.example.androidserver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidserver.Adapter.Card_Adapter;
import com.example.androidserver.Modal_SQL.Card;
import com.example.androidserver.SQL.Card_DAO;
import com.example.androidserver.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.androidserver.Fragment.Cart_Fragment.card_adapter;
import static com.example.androidserver.Fragment.Cart_Fragment.listCard;
import static com.example.androidserver.Fragment.Cart_Fragment.totalExpenses;
import static com.example.androidserver.Fragment.Cart_Fragment.tv_Tong;

public class Profile_Product extends AppCompatActivity {
    TextView tv_nameSP, tv_gia,tv_loai,tv_content;
    ImageView img_Sp;
    Button add,see;
    Card_DAO card_dao;
    //String idsp;
    public static ArrayList<Card> ds1 = new ArrayList<Card>();
    boolean test = true ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__product);

        //set full layout
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Profile_Product.this,R.color.whiteBodyColor));// set status background white


        tv_nameSP = findViewById(R.id.tv_namePr);
        tv_gia = findViewById(R.id.tv_prices);
        tv_content = findViewById(R.id.tv_content);
        img_Sp = findViewById(R.id.img_product);
        add = findViewById(R.id.addtocard);


        String name = getIntent().getStringExtra("namtype");
        String content = getIntent().getStringExtra("content");
        String price = getIntent().getStringExtra("price");
        String path = getIntent().getStringExtra("path");
        String idsp  = getIntent().getStringExtra("_id");
        String iduser = SharedPrefManager.getInstance(Profile_Product.this).getUser().get_id();

        Float gia = Float.parseFloat(price);
        tv_nameSP.setText(name);
        tv_gia.setText("Giá sản phẩm: "+price + "VNĐ");
        tv_content.setText(content);
        Picasso.get().load(path).into(img_Sp);

        card_dao = new Card_DAO(Profile_Product.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = new Card(iduser,gia,content,idsp,name,path);

               // test = false;
                for (int i=0; i< card_dao.getArrayId().size(); i++){
                    String id = card_dao.getArrayId().get(i);
                    if (getIntent().getStringExtra("_id").equals(id)){
                        Toast.makeText(Profile_Product.this, "SAn pham da co", Toast.LENGTH_SHORT).show();
                        Log.i("Hello", id+getIntent().getStringExtra("_id") +card_dao.getArrayId().size());
                        test = false;
                        break;
                    }

                }

                if (test){
                    card_dao.addCard(card);
                    Toast.makeText(Profile_Product.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    Log.i("Hello", "them thanh cong");
                    finish();
                }


            }
        });
    }


}
