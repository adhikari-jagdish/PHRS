package com.vayamtech.healthe_cord.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.Interface.APIService;
import com.vayamtech.healthe_cord.Model.RegisterPojo.RegisterPojo;
import com.vayamtech.healthe_cord.Model.ResponsePojo.ResponsePojo;
import com.vayamtech.healthe_cord.NetworkCalls.RetrofitClient;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.TypefaceUtil;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.Utils.CustomProgressBar;
import com.vayamtech.healthe_cord.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.luongvo.widget.iosswitchview.SwitchView;

public class LoginActivity extends BaseActivity implements TextWatcher, SwitchView.OnCheckedChangeListener {
public ActivityLoginBinding loginBinding;
    private static CustomProgressBar progressBar = new CustomProgressBar();
    private String TAG = LoginActivity.class.getSimpleName();
    private TextView txtEmailError;
    AlertDialog.Builder alert;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERID = "userid";
    private static final String KEY_PASS = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide(); //hide the title bar
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ButtonHandler buttonHandler = new ButtonHandler(LoginActivity.this);
        loginBinding.setButtonHandler(buttonHandler);

        //for alert dialog
        alert = new AlertDialog.Builder(LoginActivity.this);


        txtEmailError = findViewById(R.id.txt_email_error);

        //Instantiate SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            loginBinding.switchRememberPass.setChecked(true);
        else
            loginBinding.switchRememberPass.setChecked(false);

        loginBinding.etEmailId.setText(sharedPreferences.getString(KEY_USERID,""));
        loginBinding.etPassword.setText(sharedPreferences.getString(KEY_PASS, ""));

        loginBinding.etEmailId.addTextChangedListener(this);
        loginBinding.etPassword.addTextChangedListener(this);
        loginBinding.switchRememberPass.setOnCheckedChangeListener(this);


    }

    public void blankValidationCall()
    {
        /*if(loginBinding.etEmailId.getText().toString().equalsIgnoreCase("")){
            loginBinding.etEmailId.requestFocus();
            loginBinding.etEmailId.setError("Field cannot be blank");

        }
        else if(loginBinding.etPassword.getText().toString().equalsIgnoreCase(""))
        {
            loginBinding.etPassword.requestFocus();
            loginBinding.etPassword.setError("Field cannot be blank");
        }
        else{
            validationCall();
        }*/

        alert.setMessage("Email-Id / Password fields can't be empty");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loginBinding.etEmailId.requestFocus();
            }
        });
        alert.show();

    }

    public void validationCall(){

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(loginBinding.etEmailId.getText().toString().matches(emailPattern))
        {
            loginCall();
        }
        else {
            alert.setMessage(R.string.emailerror);
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    loginBinding.etEmailId.requestFocus();
                }
            });
            alert.show();
        }

    }

    public void loginCall(){
        String userId = loginBinding.etEmailId.getText().toString();
        String password = loginBinding.etPassword.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("p1", userId);
        map.put("p2", password);

        loginCheck(TAG, map);


        progressBar.show(this, "Processing Your Request...Please Wait");

    }

    private void loginCheck(final String TAG, Map<String, String> data) {
        try{
            APIService apiService = RetrofitClient.getClient().create(APIService.class);
            Call<RegisterPojo> call = apiService.calllogin(data);
            call.enqueue(new Callback<RegisterPojo>() {
                @Override
                public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                    Response<RegisterPojo> hres = response;
                    progressBar.getDialog().dismiss();

                    try{
                        if(hres.body().getResponseStatus().getStatusCode())
                        {
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                            gotoNext(LoginActivity.this, MainActivity.class, true, Bundle.EMPTY, true);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), hres.body().getResponseStatus().getMessageCode(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    catch (Exception ex){
                        Log.i(TAG, "Exception++++" + ex.toString());
                    }

                }

                @Override
                public void onFailure(Call<RegisterPojo> call, Throwable t) {
                    // progressDialog.dismiss();
                    progressBar.getDialog().dismiss();
                    Toast.makeText(getApplicationContext(), "Login Failed..Couldnot Connect to Server", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception t)
        {
            Log.i(TAG, "Exception++++" + t.toString());
        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(SwitchView switchView, boolean b) {
        managePrefs();
    }

    private void managePrefs() {
        if(loginBinding.switchRememberPass.isChecked()){
            editor.putString(KEY_USERID, loginBinding.etEmailId.getText().toString().trim());
            editor.putString(KEY_PASS, loginBinding.etPassword.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }
        else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.putString(KEY_USERID,"");
            editor.putString(KEY_PASS,"");
            editor.apply();

        }
    }
}
