package com.vayamtech.healthe_cord.Handler;

//*Created by Jagadish on 7/3/2018.*/

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vayamtech.healthe_cord.Activity.ForgotPasswordActivity;
import com.vayamtech.healthe_cord.Activity.LoginActivity;
import com.vayamtech.healthe_cord.Activity.MainActivity;
import com.vayamtech.healthe_cord.Activity.RegisterActivity;
import com.vayamtech.healthe_cord.Activity.RegisterSecActivity;
import com.vayamtech.healthe_cord.Activity.TabsActivity;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.BaseActivity;

public class ButtonHandler extends Activity { // class to handle all the click of application
public AlertDialog alertDialog;
    private Context mcontext;

    public ButtonHandler(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void onClickButton(View view)
    {
        if(view instanceof Button)
        {
            switch (view.getId()){
                case R.id.btnLogin:
                    if(!((LoginActivity) mcontext).loginBinding.etEmailId.getText().toString().equalsIgnoreCase("") && !((LoginActivity) mcontext).loginBinding.etPassword.getText().toString().equalsIgnoreCase(""))
                    {
                        ((LoginActivity) mcontext).gotoNext(mcontext, MainActivity.class, false, Bundle.EMPTY, false);
                    }
                    else {
                        ((BaseActivity) mcontext).showValidationMessage(mcontext, "Email-Id / Password cannot be blank");

                    }

                    break;

                case R.id.btnNewUser:
                    ((BaseActivity) mcontext).gotoNext(mcontext, RegisterActivity.class, false, Bundle.EMPTY, false);
                    break;

                case R.id.RbtnNext:
                    ((RegisterActivity) mcontext).gotoNext(mcontext, RegisterSecActivity.class, false, Bundle.EMPTY, false);

                    break;




            }
        }
        else if(view instanceof TextView)
        {
            switch (view.getId()){
                case R.id.txtForgotPass:
                    ((LoginActivity) mcontext).gotoNext(mcontext, ForgotPasswordActivity.class, false, Bundle.EMPTY, false);
                    break;


            }


        }


    }


}
