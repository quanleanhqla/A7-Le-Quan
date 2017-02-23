package com.example.quanla.pomodoro.networks;

import android.util.Log;

import com.example.quanla.pomodoro.networks.services.LoginService;
import com.example.quanla.pomodoro.settings.SharedPrefs;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QuanLA on 1/18/2017.
 */

public class NetContext {
    private static final String TAG = "abc";
    public static final NetContext instance = new NetContext();

    private Retrofit retrofit;
    private NetContext(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggerInterceptor())
                .addInterceptor(new HeaderInterceptor()).build();



        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(LoginService.class);

    }

    public LoginService createLoginService(){
        return retrofit.create(LoginService.class);
    }


    class LoggerInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            //1 Get request
            Request request =  chain.request();

            //2 process request (print out)

            RequestBody body = request.body();
            if(body != null){
                Log.d(TAG, String.format("body %s", body.toString()));
            }

            Headers headers = request.headers();
            if(headers != null){
                Log.d(TAG, String.format("headers %s", headers.toString()));
            }

            Log.d(TAG, String.format("url %s", request.url()));
            //3 proceed
            Response response = chain.proceed(request);

            //4 process response

            Log.d(TAG, String.format("response %s", response.toString()));
            Log.d(TAG, String.format("response %s", getResponseString(response)));
            return response;

        }
        private String getResponseString(okhttp3.Response response) {
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            try {
                source.request(Long.MAX_VALUE); // Buffer the entire body.
            } catch (IOException e) {
                e.printStackTrace();
            }
            Buffer buffer = source.buffer();
            return buffer.clone().readString(Charset.forName("UTF-8"));
        }
    }

    class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            String token = SharedPrefs.getInstance().getAccessToken();
            if(token != null) {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", String.format("JWT %s", token))
                        .build();

                return chain.proceed(request);
            }

            return chain.proceed(chain.request());
        }
    }

    public <T> T create(Class<T> classz){
        return retrofit.create(classz);
    }


}
