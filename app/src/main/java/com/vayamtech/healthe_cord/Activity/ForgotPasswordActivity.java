package com.vayamtech.healthe_cord.Activity;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.TypefaceUtil;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends BaseActivity {
public ActivityForgotPasswordBinding forgotPasswordBinding;
    android.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgotPasswordBinding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButtonHandler buttonHandler = new ButtonHandler(ForgotPasswordActivity.this);
        forgotPasswordBinding.setButtonHandler(buttonHandler);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_fpw);
        TextView title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Forgot Password");
        View view = getSupportActionBar().getCustomView();
        ImageButton imageButton = view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
