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


public class drugAllergy_twoFragment extends Fragment implements View.OnClickListener {

    private Button btn_submit;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_drug_allergy_two, container, false);
        btn_submit = v.findViewById(R.id.btndrugallergyLogin);
        btn_submit.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        drugAllergy_oneFragment dof = new drugAllergy_oneFragment() ;
        fragmentTransaction.replace(R.id.fragment_container, dof);
        fragmentTransaction.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
