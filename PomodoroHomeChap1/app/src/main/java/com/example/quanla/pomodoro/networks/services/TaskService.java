package com.example.quanla.pomodoro.networks.services;

import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.networks.jsonmodels.LoginResponseJson;
import com.example.quanla.pomodoro.networks.jsonmodels.RegisterResponseJson;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by QuanLA on 2/24/2017.
 */

public interface TaskService {
    @POST("login")
    Call<LoginResponseJson> login(@Body RequestBody body);

    @POST("register")
    Call<RegisterResponseJson> register(@Body RequestBody body);

    @GET("task")
    Call<List<Task>> getAllTask();

    @POST("task")
    Call<Task> addTask(@Body RequestBody body);

    @FormUrlEncoded
    @PUT("task/{" +
            "localID}")
    Call<Task> editATask(@Path("localID") String localID,
                         @Field("name") String newName,
                         @Field("color") String newColor,
                         @Field("payment_per_hour") float newPayment,
                         @Field("local_id") String newLocalID);
    @DELETE("task/{localID}")
    Call<Task> deleteATask(@Path("localID") String localID);
}
