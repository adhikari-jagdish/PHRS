package com.vayamtech.healthe_cord.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.vayamtech.healthe_cord.Activity.RegisterActivity;
import com.vayamtech.healthe_cord.Interface.APIService;
import com.vayamtech.healthe_cord.Interface.FragmentToActivity;
import com.vayamtech.healthe_cord.Model.RegisterPojo.RegisterPojo;
import com.vayamtech.healthe_cord.Model.RegisterPojo.masterList;
import com.vayamtech.healthe_cord.NetworkCalls.RetrofitClient;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.CustomProgressBar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class Reg_twoFragment extends Fragment implements View.OnClickListener {

private EditText etAddress, etContactNo, etPincode, etCity;

    private FragmentToActivity mCallback;
    private Button btnSubmit;
    AlertDialog.Builder alertDialog;
    private Spinner spinnerListState;
    private String itemId;
    private static CustomProgressBar progressBar = new CustomProgressBar();


    public Reg_twoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (FragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToActivity");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reg_two, container, false);

        etAddress = v.findViewById(R.id.etRAddress);

        etContactNo = v.findViewById(R.id.etRcontactNo);

        etCity = v.findViewById(R.id.etcity);

        etPincode = v.findViewById(R.id.etpinCode);

        alertDialog = new AlertDialog.Builder(getActivity());

        spinnerListState = v.findViewById(R.id.spinner_State);


        btnSubmit = v.findViewById(R.id.RbtnSubmit);
        btnSubmit.setOnClickListener(this);


        requestMasterList();
        progressBar.show(getActivity(), "Fetching Data...Please Wait");

        return v;
    }

    private void sendData(String name, String dob, String gender, String email, String password, String address, String contactNo, String cityId, String city, String pincode) {
            mCallback.communicate(name, dob, gender, email, password,address, contactNo, cityId, city, pincode);
    }


    @Override
    public void onClick(View v) {
        if(etAddress.getText().toString().equalsIgnoreCase("") || etContactNo.getText().toString().equalsIgnoreCase("") || etCity.getText().toString().equalsIgnoreCase("") || etPincode.getText().toString().equalsIgnoreCase(""))
        {
            alertDialog.setMessage("Fill in all the input Fields");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etAddress.requestFocus();
                }
            });
            alertDialog.show();
        }
        else{

            validate();
        }
    }

    public void validate()
    {
        String vCity = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";
        String address = etAddress.getText().toString();
        String contactNo = etContactNo.getText().toString();
        String city = etCity.getText().toString();
        String pincode = etPincode.getText().toString();

        if(!(address.length()<50)){

            alertDialog.setMessage("Address too long");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etAddress.requestFocus();
                }
            });
            alertDialog.show();
        }
        else if(!(contactNo.length()<11)){

            alertDialog.setMessage("Enter a valid contact number");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etContactNo.requestFocus();
                }
            });
            alertDialog.show();
        }
       else if(!(city.matches(vCity))){

            alertDialog.setMessage("Enter a valid City name");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etCity.requestFocus();
                }
            });
            alertDialog.show();
        }
        else if(!(pincode.length()<7)){

            alertDialog.setMessage("Enter a valid Pincode");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etPincode.requestFocus();
                }
            });
            alertDialog.show();
        }

        else{
            Bundle bundle = getArguments();
            String name = bundle.getString("Name");
            String dob = bundle.getString("Dob");
            String gender = bundle.getString("Gender");
            String email = bundle.getString("EmailId");
            String password = bundle.getString("Password");
            String cityId = itemId;
            sendData(name, dob, gender, email, password,address, contactNo, cityId, city, pincode);
        }



    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void requestMasterList()
    {
        String state = "STATE";
        Map<String, String> map = new HashMap<>();
        map.put("masterName", state);

        getMasterlist(TAG, map);
    }

    private void getMasterlist(final String tag, Map<String, String> data) {
        APIService apiService = RetrofitClient.getClient().create(APIService.class);
        Call<RegisterPojo> listStates = apiService.callRegistercombo(data);
        listStates.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                progressBar.getDialog().dismiss();
                if(response.isSuccessful()){
                    RegisterPojo registerPojo = response.body();
                    final ArrayList<masterList> stateArray = registerPojo.getMasterLists();
                    String[] stateName = new String[stateArray.size()];
                    final String[] stateValue = new String[stateArray.size()];


                    for(int i=0; i<stateArray.size(); i++)
                    {
                        stateName[i] = stateArray.get(i).getStateName();
                        stateValue[i] = stateArray.get(i).getStateValue();

                    }
                 //  Log.i("Tag", "Resonse+++"+spinnerString);
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, stateName);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spinnerListState.setAdapter(spinnerAdapter);

                    spinnerListState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            itemId = stateValue[position];

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }
                else{
                    Log.e(TAG, "Error in response");
                }
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
               progressBar.getDialog().dismiss();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
