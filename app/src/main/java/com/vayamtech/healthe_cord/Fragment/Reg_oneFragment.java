package com.vayamtech.healthe_cord.Fragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.vayamtech.healthe_cord.Interface.FragmentToActivity;
import com.vayamtech.healthe_cord.R;

import java.util.Date;


public class Reg_oneFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private FragmentToActivity mCallback;
    private Button btn_next;
    private EditText registrationDate;
    private EditText etName, etDate, etEmailid, etPassword, etAddress, etContact, etPincode;
    private RadioButton rbtn;

    public Reg_oneFragment() {
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
        View v = inflater.inflate(R.layout.fragment_reg_one, container, false);

        etName = v.findViewById(R.id.etRname);
        etDate = v.findViewById(R.id.etRdob);
        rbtn = v.findViewById(R.id.rbtnMale);
        etEmailid = v.findViewById(R.id.etRemailId);
        etPassword = v.findViewById(R.id.etRpassword);

        String name = etName.getText().toString();
        String dob = etDate.getText().toString();
        String gender = rbtn.getText().toString();



        sendData(name, dob, gender);
        
        
        
        btn_next = v.findViewById(R.id.RbtnNext);
        registrationDate = v.findViewById(R.id.etRdob);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Reg_twoFragment rof = new Reg_twoFragment() ;
                fragmentTransaction.replace(R.id.fragment_registration_container, rof);
                fragmentTransaction.commit();
            }
        });
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

    private void sendData(String name, String dob, String gender) {
        mCallback.communicate(name, dob, gender);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year);
        String formattedDate = sb.toString();
        registrationDate.setText(formattedDate);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
