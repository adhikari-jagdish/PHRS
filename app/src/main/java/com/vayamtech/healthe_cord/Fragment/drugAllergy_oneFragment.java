package com.vayamtech.healthe_cord.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.vayamtech.healthe_cord.R;


public class drugAllergy_oneFragment extends Fragment implements View.OnClickListener {
    private AdapterView.OnItemSelectedListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_drug_allergy_one, null);
        FloatingActionButton fab = v.findViewById(R.id.fabD);
        fab.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        drugAllergy_twoFragment dof = new drugAllergy_twoFragment() ;
        fragmentTransaction.replace(R.id.fragment_container, dof);
        fragmentTransaction.commit();
    }
}
