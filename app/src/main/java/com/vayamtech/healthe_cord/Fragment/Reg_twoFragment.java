package com.vayamtech.healthe_cord.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vayamtech.healthe_cord.Interface.FragmentToActivity;
import com.vayamtech.healthe_cord.R;


public class Reg_twoFragment extends Fragment {

private EditText etAddress, etContactNo, etPincode;
    private FragmentToActivity mCallback;

    public Reg_twoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (FragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToActivity");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reg_two, container, false);

        etAddress = v.findViewById(R.id.etRaddress);
        String address = etAddress.getText().toString();

        etContactNo = v.findViewById(R.id.etRcontactNo);
        String contactNo = etContactNo.getText().toString();

        etPincode = v.findViewById(R.id.etpinCode);
        String pincode = etPincode.getText().toString();

        sendData(address, contactNo, pincode);



        return v;
    }

    private void sendData(String address, String contactNo, String pincode) {
        mCallback.communicate(address, contactNo, pincode);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
