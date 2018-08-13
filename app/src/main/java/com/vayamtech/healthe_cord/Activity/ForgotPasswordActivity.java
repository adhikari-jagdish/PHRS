package com.vayamtech.healthe_cord.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.Interface.APIService;
import com.vayamtech.healthe_cord.Model.RegisterPojo.RegisterPojo;
import com.vayamtech.healthe_cord.Model.ResponsePojo.ResponsePojo;
import com.vayamtech.healthe_cord.NetworkCalls.RetrofitClient;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.TypefaceUtil;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.Utils.CustomProgressBar;
import com.vayamtech.healthe_cord.databinding.ActivityForgotPasswordBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {
public ActivityForgotPasswordBinding forgotPasswordBinding;
    android.app.ActionBar actionBar;
    private String TAG = ForgotPasswordActivity.class.getSimpleName();
    private  AlertDialog.Builder alert;
    private static CustomProgressBar progressBar = new CustomProgressBar();

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

        alert = new AlertDialog.Builder(ForgotPasswordActivity.this);


    }

    public void forgotPwValidation(){

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(forgotPasswordBinding.etEmailId.getText().toString().matches(emailPattern))
        {
            forgotpwCall();
        }
        else {
            alert.setMessage(R.string.emailerror);
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    forgotPasswordBinding.etEmailId.requestFocus();
                }
            });
            alert.show();
        }

    }

    public void forgotPwblankValidation()
    {

        alert.setMessage("Email-Id field can't be empty");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                forgotPasswordBinding.etEmailId.requestFocus();
            }
        });
        alert.show();
    }

    public void forgotpwCall()
    {
        String userId = forgotPasswordBinding.etEmailId.getText().toString().trim();

        Map<String, String> map = new HashMap<>();
        map.put("p1", userId);
        
        forgotpwCheck(TAG, map);

        progressBar.show(this, "Processing Your Request...Please Wait");
    }

    private void forgotpwCheck(final String TAG, Map<String, String> data) {
        try{
            APIService apiService = RetrofitClient.getClient().create(APIService.class);
            Call<ResponsePojo> call = apiService.callforgot(data);
            call.enqueue(new Callback<ResponsePojo>() {
                @Override
                public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                    Response<ResponsePojo> hres = response;
                    progressBar.getDialog().dismiss();
                    try {
                        Toast.makeText(getApplicationContext(), hres.body().getResponseStatus().getMessageCode(), Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onResponse++++" + response.body());
                    } catch (Exception t) {
                        Log.i(TAG, "Exception++++" + t.toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponsePojo> call, Throwable t) {
                    progressBar.getDialog().dismiss();
                    Toast.makeText(getApplicationContext(), "Couldnot Connect to Server", Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch (Exception t)
        {
            Log.i(TAG, "Exception++++" + t.toString());
        }

    }


}
