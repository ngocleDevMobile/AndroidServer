package com.example.androidserver.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.androidserver.Modal_SQL.Card;
import com.example.androidserver.R;
import com.example.androidserver.SQL.Card_DAO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.androidserver.Fragment.Cart_Fragment.card_adapter;
import static com.example.androidserver.Fragment.Cart_Fragment.listCard;
import static com.example.androidserver.Fragment.Cart_Fragment.totalExpenses;
import static com.example.androidserver.Fragment.Cart_Fragment.tv_Tong;


public class Card_Adapter extends BaseAdapter {
    public ArrayList<Card> dssp;
    public Context context;

    public Card_Adapter(ArrayList<Card> dssp, Context context) {
        this.dssp = dssp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dssp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inf = ((Activity)context).getLayoutInflater();
        view = inf.inflate(R.layout.addtocard,parent,false);

         ImageView ivAvatar = view.findViewById(R.id.imageaddCard);
         TextView tvName = view.findViewById(R.id.tvspcard);
         TextView tvPrice = view.findViewById(R.id.tvPriceCard);
         ImageView del = view.findViewById(R.id.delete);

        Card mt = dssp.get(position);
        tvName.setText(mt.namesp);
        tvPrice.setText("Giá: "+mt.price+" VNĐ");
        Picasso.get().load(mt.path).into(ivAvatar);

         del.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                 dialog.setTitle("Giỏ hàng");
                 dialog.setMessage("Bạn chắc chắn muốn xáo sản phẩm naỳ?");
                 dialog.setCancelable(true);

// Specifying a listener allows you to take an action before dismissing the dialog.
// The dialog is automatically dismissed when a dialog button is clicked.

                 dialog.setPositiveButton(
                         "Yes",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 // Continue with some operation
                                 String idpreset = mt._idpreset;
                                 Card_DAO mucThuDao = new Card_DAO(context);
                                 mucThuDao.xoa_card(idpreset);

                                 Card_DAO card_dao = new Card_DAO(context);
                                 dssp = card_dao.getcard();
                                 card_adapter = new Card_Adapter(dssp, context);
                                 listCard.setAdapter(card_adapter);

                                 totalExpenses = card_dao.getTotalExpenses();
                                 tv_Tong.setText("Tổng tiền: "+totalExpenses+"");


                             }
                         });

// A null listener allows the button to dismiss the dialog and take no further action.

                 dialog.setNegativeButton(
                         "No",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 // User cancelled the dialog
                                 dialog.cancel();
                             }
                         });

                 AlertDialog alert = dialog.create();
                 alert.show();

             }
         });


        return view;
    }
}
