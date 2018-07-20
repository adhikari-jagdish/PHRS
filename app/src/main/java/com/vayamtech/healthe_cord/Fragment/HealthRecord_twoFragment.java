package com.vayamtech.healthe_cord.Fragment;

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

import com.vayamtech.healthe_cord.R;


public class HealthRecord_twoFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health_record_two, container, false);
        Button btn = v.findViewById(R.id.btnhealthRecordSubmit);
        btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        HealthRecord_oneFragment healthRecord_oneFragment = new HealthRecord_oneFragment() ;
        fragmentTransaction.replace(R.id.fragment_HealthRecord_container, healthRecord_oneFragment);
        fragmentTransaction.commit();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
