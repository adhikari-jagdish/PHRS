package com.vayamtech.healthe_cord.Fragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.vayamtech.healthe_cord.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Immunization_twoFragment extends Fragment implements DatePickerDialog.OnDateSetListener   {
    private EditText immunizationDate;



    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_immunization_two, container, false);
        Button btn = v.findViewById(R.id.btnImmunizationSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Immunization_oneFragment iof = new Immunization_oneFragment() ;
                fragmentTransaction.replace(R.id.fragment_immunization_container, iof);
                fragmentTransaction.commit();
            }
        });
        immunizationDate = v.findViewById(R.id.et_immunizationDate);
        immunizationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment(); // creating DialogFragment which creates DatePickerDialog
                newFragment.setTargetFragment(Immunization_twoFragment.this,0); // Passing this fragment DatePickerFragment.
                newFragment.show(getActivity().getFragmentManager(), "datePicker");
            }
        });
        return v;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year);
        String formattedDate = sb.toString();
        immunizationDate.setText(formattedDate);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
