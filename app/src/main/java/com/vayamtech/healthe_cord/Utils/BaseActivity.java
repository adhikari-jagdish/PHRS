package com.vayamtech.healthe_cord.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.vayamtech.healthe_cord.Activity.BehaviouralhealthriskActivity;
import com.vayamtech.healthe_cord.Activity.DiagnosticInvestigationActivity;
import com.vayamtech.healthe_cord.Activity.DiseaseHistoryActivity;
import com.vayamtech.healthe_cord.Activity.DrugAllergyActivity;
import com.vayamtech.healthe_cord.Activity.HealthRecordActivity;
import com.vayamtech.healthe_cord.Activity.HeridiatoryConcernActivity;
import com.vayamtech.healthe_cord.Activity.ImmunizationActivity;
import com.vayamtech.healthe_cord.Activity.LabReportActivity;
import com.vayamtech.healthe_cord.Activity.MainActivity;
import com.vayamtech.healthe_cord.Activity.PersonalprofileActivity;
import com.vayamtech.healthe_cord.Activity.PrescriptionActivity;
import com.vayamtech.healthe_cord.Activity.RadiologicalInvestigationActivity;
import com.vayamtech.healthe_cord.Activity.SubstanceAllergyActivity;
import com.vayamtech.healthe_cord.Activity.SurgicalHistoryActivity;
import com.vayamtech.healthe_cord.Activity.TreatmentUndergoneActivity;
import com.vayamtech.healthe_cord.R;

//*Created by Jagadish on 7/2/2018.*/
public abstract class BaseActivity extends AppCompatActivity {
    protected TextView txtLogout;


    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;



    }

    public void splashHandler(final Context context, final  Class aClass)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoNext(context, aClass, true, Bundle.EMPTY, false);
            }
        },2500);
    }

    public void funhandler(final Context context, final Class aClass)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoNext(context, aClass, true, Bundle.EMPTY, false);
            }
        },0);
    }

    public void gotoNext(Context context, Class nextClass, boolean finish, Bundle bundle, boolean clearAll) {

        Intent gotoNext = new Intent(context, nextClass);
        gotoNext.putExtras(bundle);
        if(clearAll)
            gotoNext.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(gotoNext);
        ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        if(finish)

            ((Activity) context).finish();
    }
    public void showValidationMessage(Context context, final String validationMessage) {
        final AlertDialog.Builder builder = new AlertDialog.Builder((BaseActivity) context);
        builder.setTitle("Alert");
        builder.setMessage(validationMessage);
        builder.setIcon(R.drawable.erroricon);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(validationMessage.equalsIgnoreCase("Invalid Credentials"))
                {

                }
                else {

                }
            }
        });
        builder.show();
    }
    protected void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                gotoNext(this, MainActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_personalProfile:
                gotoNext(this, PersonalprofileActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_behaviouralHr:
                gotoNext(this, BehaviouralhealthriskActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_drugAllergy:
                gotoNext(this, DrugAllergyActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_substanceAllergy:
                gotoNext(this, SubstanceAllergyActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_herediatoryConcern:
                gotoNext(this, HeridiatoryConcernActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_immunization:
                gotoNext(this, ImmunizationActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_diseaseHistory:
                gotoNext(this, DiseaseHistoryActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_treatmentUndergone:
                gotoNext(this, TreatmentUndergoneActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_surgicalHistory:
                gotoNext(this, SurgicalHistoryActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_healthRecord:
                gotoNext(this, HealthRecordActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_radiologicalInvestigation:
                gotoNext(this, RadiologicalInvestigationActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_prescription:
                gotoNext(this, PrescriptionActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_diagnosticInvestigation:
                gotoNext(this, DiagnosticInvestigationActivity.class, false, Bundle.EMPTY, false);
                break;

            case R.id.nav_labReport:
                gotoNext(this, LabReportActivity.class, false, Bundle.EMPTY, false);
                break;

            default:
                gotoNext(this, MainActivity.class, false, Bundle.EMPTY, false);
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

    }



}
