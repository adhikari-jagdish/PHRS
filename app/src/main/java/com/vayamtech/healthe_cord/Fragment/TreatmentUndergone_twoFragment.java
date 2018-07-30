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

import com.vayamtech.healthe_cord.R;


public class TreatmentUndergone_twoFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private EditText treatmentUndergoneDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_treatment_undergone_two, container, false);
        Button btn = v.findViewById(R.id.btnTreatmentDateSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                TreatmentUndergone_oneFragment treatmentUndergone_oneFragment = new TreatmentUndergone_oneFragment() ;
                fragmentTransaction.replace(R.id.fragment_TreatmentUndergone_container, treatmentUndergone_oneFragment);
                fragmentTransaction.commit();
            }
        });
        treatmentUndergoneDate = v.findViewById(R.id.et_treatmentDate);
        treatmentUndergoneDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.setTargetFragment(TreatmentUndergone_twoFragment.this, 0);
                dialogFragment.show(getActivity().getFragmentManager(), "DatePicker");
            }
        });
        return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year);
        String formattedDate = sb.toString();
        treatmentUndergoneDate.setText(formattedDate);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
