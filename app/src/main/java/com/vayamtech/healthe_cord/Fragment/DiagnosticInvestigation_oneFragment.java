package com.vayamtech.healthe_cord.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vayamtech.healthe_cord.R;


public class DiagnosticInvestigation_oneFragment extends Fragment implements View.OnClickListener {


    public DiagnosticInvestigation_oneFragment() {
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
        View v = inflater.inflate(R.layout.fragment_diagnostic_investigation_one, container, false);
        FloatingActionButton fab = v.findViewById(R.id.btnfab_DiagnosticInvestigation);
        fab.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        DiagnosticInvestigation_twoFragment diagnosticInvestigation_twoFragment = new DiagnosticInvestigation_twoFragment() ;
        fragmentTransaction.replace(R.id.fragment_DiagnosticInvestigation_container, diagnosticInvestigation_twoFragment);
        fragmentTransaction.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
