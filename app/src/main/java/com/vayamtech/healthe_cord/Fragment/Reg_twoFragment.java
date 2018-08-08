package com.vayamtech.healthe_cord.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vayamtech.healthe_cord.Interface.FragmentToActivity;
import com.vayamtech.healthe_cord.R;


public class Reg_twoFragment extends Fragment implements View.OnClickListener {

private EditText etAddress, etContactNo, etPincode, etCity;
    private FragmentToActivity mCallback;
    private Button btnSubmit;
    AlertDialog.Builder alertDialog;

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

        btnSubmit = v.findViewById(R.id.RbtnSubmit);
        btnSubmit.setOnClickListener(this);

        return v;
    }

    private void sendData(String name, String dob, String gender, String email, String password, String address, String contactNo, String city, String pincode) {
            mCallback.communicate(name, dob, gender, email, password,address, contactNo, city, pincode);
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
        String vCity = "^[A-Za-z\\\\s]{1,}[\\\\.]{0,1}[A-Za-z\\\\s]{0,}$";
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

            sendData(name, dob, gender, email, password,address, contactNo, city, pincode);
        }



    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
