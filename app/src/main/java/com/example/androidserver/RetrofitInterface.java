package com.example.androidserver;

import com.example.androidserver.Modal_SQL.Card_Res;
import com.example.androidserver.Models.Giaodich_Res;
import com.example.androidserver.Models.Product_Type_Response;
import com.example.androidserver.Models.News_Item;
import com.example.androidserver.Models.Product_Res;
import com.example.androidserver.Models.Theloai_Res;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitInterface {
//    @POST("/api/login")
//    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);
//
//    @POST("/api/client")
//    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @GET("/news/list_news/")
    Call<News_Item> fetchNews(@Query("results")int results);

    @GET("/product/list_product/")
    Call<Product_Res> fetchProduct(@Query("results")int results);

    @GET("/product/list_type/")
    Call<Theloai_Res> fetchType(@Query("results")int results);

    @GET("/product/get_product_with_theloai")
    Call<Product_Type_Response> getProductByType(@Query("theloai") String apiKey);

    @Multipart
    @POST("/api/upload")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("upload") RequestBody name);

    @POST("/card/postdata")
    Call<JsonArray> postData(@Body JsonArray body);

    @GET("/card/get_by_id")
    Call<Giaodich_Res> getGiaodich(@Query("iduser") String apiKey);


}

