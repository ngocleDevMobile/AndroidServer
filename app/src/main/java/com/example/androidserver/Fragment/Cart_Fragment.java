package com.example.androidserver.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidserver.Adapter.Card_Adapter;
import com.example.androidserver.Adapter.Product_Adapter;
import com.example.androidserver.Adapter.Theloai_Adapter;
import com.example.androidserver.Modal_SQL.Card;
import com.example.androidserver.Modal_SQL.Card_Res;
import com.example.androidserver.Models.DefaultResponse;
import com.example.androidserver.Models.Product;
import com.example.androidserver.Models.Product_Res;
import com.example.androidserver.Models.Theloai;
import com.example.androidserver.Models.Theloai_Res;
import com.example.androidserver.R;
import com.example.androidserver.RetrofitInterface;
import com.example.androidserver.SQL.Card_DAO;
import com.example.androidserver.getData.RetrofitClient;
import com.example.androidserver.storage.SharedPrefManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_Fragment extends Fragment {
    public static ListView listCard;
    public static  ArrayList<Card> ds1;
    Card_DAO card_dao;
    public static Card_Adapter card_adapter;
    public static double totalExpenses;
    public static TextView tv_Tong;
    public Button btnThanhtoan;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    private String date;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        listCard = view.findViewById(R.id.rvlistcard);
        tv_Tong = view.findViewById(R.id.tvsum);
        btnThanhtoan = view.findViewById(R.id.btnThanhtoan);

        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(getActivity(), "Thanh toan thanh cong", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Giỏ hàng");
                dialog.setMessage("Bạn chắc chắn muốn mua sản phẩm naỳ?");
                dialog.setCancelable(true);

                dialog.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                card_dao = new Card_DAO(getActivity());
                                JsonArray jsonArray = new JsonArray();
                                calendar = Calendar.getInstance();



                                for (int i = 0; i < card_dao.getcard().size(); i++) {
                                    JsonObject jsonobject= new JsonObject();
                                    dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                    date = dateFormat.format(calendar.getTime());

                                    jsonobject.addProperty("iduser",card_dao.getcard().get(i).getIduser());
                                    jsonobject.addProperty("price",card_dao.getcard().get(i).getPrice());
                                    jsonobject.addProperty("content",card_dao.getcard().get(i).getContent());
                                    jsonobject.addProperty("_idpreset",card_dao.getcard().get(i).get_idpreset());
                                    jsonobject.addProperty("namesp",card_dao.getcard().get(i).getNamesp());
                                    jsonobject.addProperty("path",card_dao.getcard().get(i).getPath());
                                    jsonobject.addProperty("day",date);
                                    jsonobject.addProperty("email",SharedPrefManager.getInstance(getActivity()).getUser().getEmail());



                                    jsonArray.add(jsonobject);
                                }

                                // Log.d("TAG_NAME", String.valueOf(card_dao.getcard().size()));
                                RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
                                client.postData(jsonArray).enqueue(new Callback<JsonArray>() {
                                    @Override
                                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                        if (response.code() == 200) {
                                            Log.d("response","Getting response from server : "+jsonArray);


                                        } else if (response.code() == 422) {
                                            Toast.makeText(getContext(), "Chưa thành công", Toast.LENGTH_LONG).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<JsonArray> call, Throwable t) {

                                    }
                                });
                                card_dao = new Card_DAO(getActivity());
                                card_dao.xoa_card_all();
                                capnhat_chitiet_thu();
                                dialog.cancel();

                            }
                        });

                dialog.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alert = dialog.create();
                alert.show();

            }
        });
        capnhat_chitiet_thu();

        return view;
    }

    public void capnhat_chitiet_thu(){
        card_dao = new Card_DAO(getActivity());
        ds1 = card_dao.getcard();
        card_adapter = new Card_Adapter(ds1, getActivity());
        listCard.setAdapter(card_adapter);
        card_adapter.notifyDataSetChanged();

        load_sum();

    }
    public JSONObject createGroupInServer(
            Activity activity, String lastSyncDateTime,
            ArrayList<Card> groups)
            throws JSONException {

        JSONObject jResult = new JSONObject();
        jResult.putOpt("last_sync_date", lastSyncDateTime);

        JSONArray jArray = new JSONArray();

        for (int i = 0; i < groups.size(); i++) {
            JSONObject jGroup = new JSONObject();
            jGroup.put("id_preset", groups.get(i).get_idpreset());
            jGroup.put("name", groups.get(i).getNamesp());
            jGroup.put("content", groups.get(i).getContent());
            jGroup.put("id_user", groups.get(i).getContent());
            jGroup.put("path", groups.get(i).getContent());
            jGroup.put("price", groups.get(i).getPrice());
            // etcetera

            JSONObject jOuter = new JSONObject();
            jOuter.put("contact_group", jGroup);

            jArray.put(jOuter);
        }

        jResult.put("recordset", jArray);
        return jResult;
    }

    public void load_sum(){
        Card_DAO card_dao = new Card_DAO(getActivity());
        totalExpenses = card_dao.getTotalExpenses();
        tv_Tong.setText("Tổng tiền: "+totalExpenses+"");
    }
}
