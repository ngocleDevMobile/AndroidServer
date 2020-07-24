package com.example.androidserver.api;


import com.example.androidserver.Models.DefaultResponse;
import com.example.androidserver.Models.LoginResponse;
import com.example.androidserver.Models.UsersResponse;
import com.example.androidserver.Response.Response;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {


    @FormUrlEncoded
    @POST("/api/client")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String name

    );

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("path") String path
    );

    @FormUrlEncoded
    @POST("/updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);


}
