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
import android.widget.ProgressBar;
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

import com.vayamtech.healthe_cord.Utils.Constants;
import com.vayamtech.healthe_cord.Utils.CustomProgressBar;
import com.vayamtech.healthe_cord.databinding.ActivityRegisterBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    private Spinner spinnerListState;

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


        /*//Instance of Progress Dialogue
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Processing Your Request...Please Wait");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
*/

        //Fragment Starts
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Reg_oneFragment rof = new Reg_oneFragment();
        fragmentTransaction.replace(R.id.fragment_registration_container, rof);
        fragmentTransaction.commit();

        spinnerListState = findViewById(R.id.spinner_State);



       /* String address = "delhi";
        String name = "Jagdish";
        String dob = "02/01/1995";
        String gender = "Male";
        String emailId = "j@gmail.com";
        String password = "pass123";
        String contactNo = "9812683296";
        String cityId = "2";
        String pinCode = "110092";*/

       //this method is for requesting the android spinner
        requestMasterList();


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
    public void communicate(String name, String dob, String gender, String email, String password, String address, String contactNo, String city, String pincode) {

        String terms = "Y";
        String cityid = "2";
        //Creating a hashmap of inputs
        Map<String, String> map = new HashMap<>();
        map.put("p7", name);
        map.put("p4", dob);
        map.put("p6", gender);
        map.put("p5", email);
        map.put("p9", password);
        map.put("p1", address);
        map.put("p3", contactNo);
        map.put("p2", cityid);
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

    public void requestMasterList()
    {
        String state = "STATE";
        Map<String, String> map = new HashMap<>();
        map.put("masterName", state);

        getMasterlist(TAG, map);
    }

    private void getMasterlist(String tag, Map<String, String> data) {
        APIService apiService = RetrofitClient.getClient().create(APIService.class);
        Call<RegisterPojo> callList = apiService.callRegistercombo(data);

        callList.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                RegisterPojo registerPojo = response.body();
                stateList = new ArrayList<>(Arrays.asList(registerPojo.getMasterLists()));
                spinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stateList);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerListState.setAdapter(spinnerAdapter);

            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {

                Log.i(TAG, "Exception++++" + t.toString());
            }
        });

    }
}


