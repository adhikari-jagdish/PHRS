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


public class SurgicalHistory_twoFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private EditText surgicalHistoryDate;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_surgical_history_two, container, false);
        Button btn = v.findViewById(R.id.btnSurgeryDetailsSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                SurgicalHistory_oneFragment surgicalHistory_oneFragment = new SurgicalHistory_oneFragment() ;
                fragmentTransaction.replace(R.id.fragment_SurgicalHistory_container, surgicalHistory_oneFragment);
                fragmentTransaction.commit();
            }
        });
        surgicalHistoryDate = v.findViewById(R.id.et_SurgicalHistoryDate);
        surgicalHistoryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.setTargetFragment(SurgicalHistory_twoFragment.this, 0);
                dialogFragment.show(getActivity().getFragmentManager(), "DatePicker");
            }
        });
        return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year);
        String formattedDate = sb.toString();
        surgicalHistoryDate.setText(formattedDate);

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
