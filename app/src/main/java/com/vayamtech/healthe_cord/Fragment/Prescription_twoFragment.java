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


public class Prescription_twoFragment extends Fragment {

    public Prescription_twoFragment() {
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
        View v = inflater.inflate(R.layout.fragment_prescription_two, container, false);
        Button btn = v.findViewById(R.id.btnPrescriptionSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Prescription_oneFragment prescription_oneFragment = new Prescription_oneFragment();
                fragmentTransaction.replace(R.id.fragment_Prescription_container, prescription_oneFragment);
                fragmentTransaction.commit();
            }
        });
        return v;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
