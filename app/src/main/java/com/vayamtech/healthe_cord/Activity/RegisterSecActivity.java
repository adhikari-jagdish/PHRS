package com.vayamtech.healthe_cord.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.databinding.ActivityRegisterSecBinding;

import java.util.ArrayList;
import java.util.List;

public class RegisterSecActivity extends BaseActivity {
    public ActivityRegisterSecBinding registerSecBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSecBinding = DataBindingUtil.setContentView(this,R.layout.activity_register_sec);
        ButtonHandler buttonHandler = new ButtonHandler(RegisterSecActivity.this);
        registerSecBinding.setButtonHandler(buttonHandler);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_registration);
        TextView title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Registration");
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
