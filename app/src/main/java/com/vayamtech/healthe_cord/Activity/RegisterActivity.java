package com.vayamtech.healthe_cord.Activity;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.vayamtech.healthe_cord.Handler.ButtonHandler;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.databinding.ActivityRegisterBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
public ActivityRegisterBinding registerBinding;
private EditText regdob;
private DatePickerDialog DatePickerDialog;
private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        ButtonHandler buttonHandler = new ButtonHandler(RegisterActivity.this);
        registerBinding.setButtonHandler(buttonHandler);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_registration);
        TextView title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Registration");
        View view = getSupportActionBar().getCustomView();
        ImageButton imageButton = view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();







  }

    private void setDateTimeField() {
        regdob.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();

       DatePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,  new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               Calendar newDate = Calendar.getInstance();
               newDate.set(year, month, dayOfMonth);
               regdob.setText(dateFormatter.format(newDate.getTime()));
           }
       },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
       DatePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void findViewsById() {
        regdob = findViewById(R.id.etRdob);
        regdob.setInputType(InputType.TYPE_NULL);
        regdob.requestFocus();
    }

    @Override
    public void onClick(View v) {
        if(v == regdob)
        {
            DatePickerDialog.show();
        }

    }

}
