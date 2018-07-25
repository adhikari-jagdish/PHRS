package com.vayamtech.healthe_cord.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vayamtech.healthe_cord.Activity.RadiologicalInvestigationActivity;
import com.vayamtech.healthe_cord.R;

import static android.support.constraint.Constraints.TAG;


public class RadiologicalInvestigation_twoFragment extends Fragment {
    private Button btn_uf_ri;
    private static final int READ_REQUEST_CODE = 42;
    EditText et_up_ti;


    public RadiologicalInvestigation_twoFragment() {
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
        View v = inflater.inflate(R.layout.fragment_radiological_investigation_two, container, false);
        Button btn = v.findViewById(R.id.btnRadiologicalInvestigationSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                RadiologicalInvestigation_oneFragment rof = new RadiologicalInvestigation_oneFragment() ;
                fragmentTransaction.replace(R.id.fragment_RadiologicalInvestigation_container, rof);
                fragmentTransaction.commit();
            }
        });
        btn_uf_ri = v.findViewById(R.id.btn_uploadFile_radiological_inv);
        btn_uf_ri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/* application/pdf");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });
        et_up_ti = v.findViewById(R.id.etuploadFile_radiological_inv);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                et_up_ti.setText(uri.toString());


            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
