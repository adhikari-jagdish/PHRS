package com.vayamtech.healthe_cord.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.Interface.APIService;
import com.vayamtech.healthe_cord.Model.RegisterPojo.RegisterPojo;
import com.vayamtech.healthe_cord.Model.ResponsePojo.ResponsePojo;
import com.vayamtech.healthe_cord.NetworkCalls.RetrofitClient;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.TypefaceUtil;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
public ActivityLoginBinding loginBinding;
    private String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide(); //hide the title bar
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ButtonHandler buttonHandler = new ButtonHandler(LoginActivity.this);
        loginBinding.setButtonHandler(buttonHandler);

    }

    public void loginCall(){
        String userId = loginBinding.etEmailId.getText().toString();
        String password = loginBinding.etPassword.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("p1", userId);
        map.put("p2", password);

        loginCheck(TAG, map);
    }

    private void loginCheck(final String TAG, Map<String, String> data) {
        APIService apiService = RetrofitClient.getClient().create(APIService.class);
        Call<RegisterPojo> call = apiService.calllogin(data);
        call.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                Response<RegisterPojo> hres = response;

                if(hres.body().getResponseStatus().getStatusCode())
                {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    gotoNext(LoginActivity.this, MainActivity.class, true, Bundle.EMPTY, true);
                }
                else{
                    Toast.makeText(getApplicationContext(), hres.body().getResponseStatus().getMessageCode(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Log.i(TAG, "onConnectionFailure++++" + call.toString());
            }
        });

    }
}
