package com.vayamtech.healthe_cord;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.vayamtech.healthe_cord.Activity.LoginActivity;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity {

ActivitySplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide(); //hide the title bar
        splashBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       splashHandler(SplashActivity.this, LoginActivity.class);

    }
}
