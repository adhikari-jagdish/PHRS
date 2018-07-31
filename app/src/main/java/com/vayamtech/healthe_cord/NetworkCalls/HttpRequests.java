package com.vayamtech.healthe_cord.NetworkCalls;

import android.provider.SyncStateContract;
import android.util.Log;

import com.vayamtech.healthe_cord.Interface.APIService;
import com.vayamtech.healthe_cord.Interface.IHttpresponse;
import com.vayamtech.healthe_cord.Model.ResponsePojo.ResponsePojo;
import com.vayamtech.healthe_cord.Utils.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//*Created by Jagadish on 7/31/2018.*/
public class HttpRequests {
    private IHttpresponse httpresponse;


    public HttpRequests(IHttpresponse httpresponse) {
        this.httpresponse = httpresponse;
    }

    public void getRegister(final String TAG, Map<String, String> data)
    {
        try{
            APIService apiService = RetrofitClient.getClient().create(APIService.class);
            Call<ResponsePojo> call = apiService.callRegister(data);
            call.enqueue(new Callback<ResponsePojo>() {
                @Override
                public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                    try{
                        Log.i(TAG, "OnResponse+++++"+response.body());
                        httpresponse.httpResponse(Constants.SUCCESS, response);
                    }
                    catch (Exception ex)
                    {
                        Log.i(TAG, "Exception++++" + ex.toString());
                        httpresponse.httpResponse(Constants.FAILURE, null);
                    }
                }

                @Override
                public void onFailure(Call<ResponsePojo> call, Throwable t) {
                    Log.i(TAG, "onFailure++++" + call.toString());
                    httpresponse.httpResponse(Constants.FAILURE, null);

                }
            });
        }
        catch (Exception ex)
        {
            Log.i(TAG, "Exception++++" + ex.toString());
        }
    }
}
