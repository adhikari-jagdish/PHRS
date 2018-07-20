package com.vayamtech.healthe_cord.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Window;

import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.TypefaceUtil;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
public ActivityLoginBinding loginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide(); //hide the title bar
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ButtonHandler buttonHandler = new ButtonHandler(LoginActivity.this);
        loginBinding.setButtonHandler(buttonHandler);

    }
}
