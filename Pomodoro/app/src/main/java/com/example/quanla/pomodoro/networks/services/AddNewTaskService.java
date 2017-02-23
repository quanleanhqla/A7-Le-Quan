package com.example.quanla.pomodoro.networks.services;

import com.example.quanla.pomodoro.databases.models.Task;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by QuanLA on 2/21/2017.
 */

public interface AddNewTaskService {
    @POST("task")
    Call<Task> addTask(@Body RequestBody body);
}
