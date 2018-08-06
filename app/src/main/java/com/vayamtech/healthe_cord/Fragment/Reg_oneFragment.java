package com.vayamtech.healthe_cord.Fragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
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
    private EditText etName, etDate, etEmailid, etPassword;
    private RadioGroup radioGroupGender;
    private RadioButton radioSexButton;

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


        String name = etName.getText().toString();
        String dob = etDate.getText().toString();
        String gender = radioSexButton.getText().toString();
        String email = etEmailid.getText().toString();
        String password = etPassword.getText().toString();



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
        fragmentTransaction.replace(R.id.fragment_registration_container, reg_twoFragment);
        fragmentTransaction.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
