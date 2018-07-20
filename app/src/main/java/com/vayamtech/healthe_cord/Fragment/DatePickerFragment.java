package com.vayamtech.healthe_cord.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

//*Created by Jagadish on 7/20/2018.*/
public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener dateSetListener; // listener object to get calling fragment listener
    DatePickerDialog myDatePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dateSetListener = (DatePickerDialog.OnDateSetListener)getTargetFragment(); // getting passed fragment
        myDatePicker = new DatePickerDialog(getActivity(), dateSetListener, year, month, day); // DatePickerDialog gets callBack listener as 2nd parameter
        // Create a new instance of DatePickerDialog and return it
        return myDatePicker;
    }
}
