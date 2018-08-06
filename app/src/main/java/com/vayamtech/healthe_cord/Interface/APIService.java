package com.vayamtech.healthe_cord.Interface;



import com.vayamtech.healthe_cord.Model.RegisterPojo.RegisterPojo;
import com.vayamtech.healthe_cord.Model.ResponsePojo.ResponsePojo;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//*Created by Jagadish on 7/31/2018.*/
public interface APIService {
    @POST("register.htm")
    Call<ResponsePojo> registerUser(@Body Map<String, String> data);

    @POST("login.htm")
    Call<RegisterPojo> calllogin(@Body Map<String, String> data);


}

