package com.vayamtech.healthe_cord.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vayamtech.healthe_cord.R;

public class SubstanceAllergy_oneFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_substance_allergy_one, container, false);
        FloatingActionButton fab = v.findViewById(R.id.btnfab_SubstanceAllergy);
        fab.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        SubstanceAllergy_twoFragment dof = new SubstanceAllergy_twoFragment() ;
        fragmentTransaction.replace(R.id.fragment_SubstanceAllergy_container, dof);
        fragmentTransaction.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
