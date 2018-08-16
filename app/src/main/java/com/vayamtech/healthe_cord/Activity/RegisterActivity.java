package com.vayamtech.healthe_cord.Activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.vayamtech.healthe_cord.Fragment.Reg_oneFragment;
import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.Interface.APIService;
import com.vayamtech.healthe_cord.Interface.FragmentToActivity;
import com.vayamtech.healthe_cord.Model.RegisterPojo.RegisterPojo;
import com.vayamtech.healthe_cord.Model.RegisterPojo.masterList;
import com.vayamtech.healthe_cord.Model.ResponsePojo.ResponsePojo;
import com.vayamtech.healthe_cord.NetworkCalls.RetrofitClient;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.BaseActivity;

import com.vayamtech.healthe_cord.Utils.CustomProgressBar;
import com.vayamtech.healthe_cord.databinding.ActivityRegisterBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements FragmentToActivity {
    public ActivityRegisterBinding registerBinding;
    private String TAG = RegisterActivity.class.getSimpleName();
    private SimpleDateFormat dateFormatter;
    private APIService mAPIService;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder builder;
    private static CustomProgressBar progressBar = new CustomProgressBar();


    private ArrayList<masterList> stateList;
    private ArrayAdapter<masterList> spinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        ButtonHandler buttonHandler = new ButtonHandler(RegisterActivity.this);
        registerBinding.setButtonHandler(buttonHandler);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_registration);
        TextView title = findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Registration");

        //For Back Button
        View view = getSupportActionBar().getCustomView();
        ImageButton imageButton = view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager().getBackStackEntryCount() == 0) {
                    finish();
                }
                else {
                    getFragmentManager().popBackStack();
                }
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        //Fragment Starts
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Reg_oneFragment rof = new Reg_oneFragment();
        fragmentTransaction.replace(R.id.fragment_registration_container, rof);
        fragmentTransaction.commit();




    }

    private void sendPost(final String TAG, Map<String, String> data) {
        builder = new AlertDialog.Builder(RegisterActivity.this);
        APIService apiService = RetrofitClient.getClient().create(APIService.class);
        Call<ResponsePojo> response = apiService.registerUser(data);
        response.enqueue(new Callback<ResponsePojo>() {
            @Override
            public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                progressBar.getDialog().dismiss();
                Response<ResponsePojo> hres = response;

                if(hres.body().getResponseStatus().getStatusCode())
                {
                    Toast.makeText(getApplicationContext(),hres.body().getResponseStatus().getMessageCode(), Toast.LENGTH_SHORT).show();
                    gotoNext(RegisterActivity.this, LoginActivity.class, true, Bundle.EMPTY, true);
                }
                else{
                    Toast.makeText(getApplicationContext(), hres.body().getResponseStatus().getMessageCode(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponsePojo> call, Throwable t) {
                progressBar.getDialog().dismiss();
                String msg = "Registration Failed...Couldnot Connect to Server";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    public void communicate(String name, String dob, String gender, String email, String password, String address, String contactNo,  String cityId, String city, String pincode) {

        String terms = "Y";
        //Creating a hashmap of inputs
        Map<String, String> map = new HashMap<>();
        map.put("p7", name);
        map.put("p4", dob);
        map.put("p6", gender);
        map.put("p5", email);
        map.put("p9", password);
        map.put("p1", address);
        map.put("p3", contactNo);
        map.put("p2", cityId);
        map.put("p12", city);
        map.put("p8", pincode);
        map.put("p10", terms);

        sendPost(TAG, map);
        progressBar.show(this, "Processing Your Request...Please Wait");
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }

    }
}


