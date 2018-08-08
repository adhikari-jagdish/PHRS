package com.vayamtech.healthe_cord.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vayamtech.healthe_cord.R;

//*Created by Jagadish on 8/8/2018.*/
public class CustomProgressBar {

    private Dialog dialog;

    public Dialog show(Context context) {
        return show(context, null);
    }

    public Dialog show(Context context, CharSequence title) {
        return show(context, title, false );
    }

    private Dialog show(Context context, CharSequence title, boolean cancelable) {
        return show(context, title, cancelable, null);
    }

    private Dialog show(Context context, CharSequence title, boolean cancelable,
                        DialogInterface.OnCancelListener cancelListener) {
        LayoutInflater inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View view = inflator.inflate(R.layout.progress_bar, null);
        if(title != null) {
            final TextView tv = view.findViewById(R.id.id_title);
            tv.setText(title);
        }
        dialog = new Dialog(context, R.style.NewDialog);
        dialog.setContentView(view);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.show();
        return dialog;
    }
    public Dialog getDialog() {
        return dialog;
    }
}
