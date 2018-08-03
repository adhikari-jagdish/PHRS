package com.vayamtech.healthe_cord.NetworkCalls;

import android.annotation.SuppressLint;

import com.vayamtech.healthe_cord.Interface.APIService;
import com.vayamtech.healthe_cord.Model.RegisterPojo.RegisterPojo;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//*Created by Jagadish on 7/31/2018.*/
public class RetrofitClient {

   private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://10.32.9.160:8080/HealthEcord/service/rest/phr/";

    public static Retrofit getClient() {
        if (retrofit==null) {

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }


}
