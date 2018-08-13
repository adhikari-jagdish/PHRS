package com.vayamtech.healthe_cord.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.vayamtech.healthe_cord.Activity.RegisterActivity;
import com.vayamtech.healthe_cord.Interface.FragmentToActivity;
import com.vayamtech.healthe_cord.R;

import java.util.Date;


public class Reg_oneFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private FragmentToActivity mCallback;
    private Button btn_next;
    private EditText registrationDate;
    private EditText etName, etDate, etEmailid, etPassword, etCpassword;
    private RadioGroup radioGroupGender;
    private RadioButton radioSexButton;
    AlertDialog.Builder builder;

    public Reg_oneFragment() {
        // Required empty public constructor
    }

       @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reg_one, container, false);
        etName = v.findViewById(R.id.etRname);
        radioGroupGender = v.findViewById(R.id.radiogroupGender);
        int selectedId=radioGroupGender.getCheckedRadioButtonId();
        radioSexButton= v.findViewById(selectedId);
        etDate = v.findViewById(R.id.etRdob);
        etEmailid = v.findViewById(R.id.etRemailId);
        etPassword = v.findViewById(R.id.etRpassword);
        etCpassword = v.findViewById(R.id.etRCpassword);

        builder = new AlertDialog.Builder(getActivity());
        
        btn_next = v.findViewById(R.id.RbtnNext);
        registrationDate = v.findViewById(R.id.etRdob);
      btn_next.setOnClickListener(this);
        registrationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.setTargetFragment(Reg_oneFragment.this, 0);
                dialogFragment.show(getActivity().getFragmentManager(), "DatePicker");
            }
        });
        return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year);
        String formattedDate = sb.toString();
        registrationDate.setText(formattedDate);
    }

    @Override
    public void onClick(View v) {


        if(etName.getText().toString().equalsIgnoreCase("") || etDate.getText().toString().equalsIgnoreCase("") || etEmailid.getText().toString().equalsIgnoreCase("") || etPassword.getText().toString().equalsIgnoreCase("") ){


            builder.setMessage("Fill in all the input Fields");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etName.requestFocus();
                }
            });
            builder.show();

        }

        else{
                validate();
        }


    }

    public void validate()
    {
        String vName = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";
        String vEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String name = etName.getText().toString();
        String dob = etDate.getText().toString();
        String gender = radioSexButton.getText().toString();
        String email = etEmailid.getText().toString();
        String password = etPassword.getText().toString();
        String cpassword = etCpassword.getText().toString();
        if(!(name.matches(vName))){

            builder.setMessage("Enter a valid name");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etName.requestFocus();
                }
            });
            builder.show();
        }
        else if(!(email.matches(vEmail)))
        {
            builder.setMessage("Enter a valid email-id");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etEmailid.requestFocus();
                }
            });
            builder.show();
        }
        else if(!(password.length()>7))
        {
            builder.setMessage("The minimum length of Password is 8 Characters");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etPassword.requestFocus();
                }
            });
            builder.show();
        }
        else if(!(cpassword.equals(password)))
        {
            builder.setMessage("Password and Confirm Password don't match");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etEmailid.requestFocus();
                }
            });
            builder.show();
        }

        else{
            Bundle bundle = new Bundle();
            bundle.putString("Name", name);
            bundle.putString("Dob", dob);
            bundle.putString("Gender", gender);
            bundle.putString("EmailId", email);
            bundle.putString("Password", password);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            Reg_twoFragment reg_twoFragment = new Reg_twoFragment();
            reg_twoFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_registration_container, reg_twoFragment).addToBackStack(null);
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
