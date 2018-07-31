package com.vayamtech.healthe_cord.Utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vayamtech.healthe_cord.Interface.IHttpresponse;
import com.vayamtech.healthe_cord.NetworkCalls.HttpRequests;

import retrofit2.Response;

//*Created by Jagadish on 7/31/2018.*/
public abstract class SocialBaseActivity extends AppCompatActivity implements IHttpresponse {
    public HttpRequests httpRequests;
    SocialBaseActivity socialBaseActivity;
   Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpRequests = new HttpRequests(SocialBaseActivity.this);
        this.context = SocialBaseActivity.this;
    }

    
}
