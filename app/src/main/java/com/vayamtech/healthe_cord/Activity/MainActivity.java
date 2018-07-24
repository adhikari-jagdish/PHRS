package com.vayamtech.healthe_cord.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.vayamtech.healthe_cord.Adapter.ImageAdapter;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.BaseActivity;
import com.vayamtech.healthe_cord.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding mainBinding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    GridView simpleGrid;
    public Integer[] mThumbIds = {
            R.drawable.gv_prof, R.drawable.gv_behavioural_hr,R.drawable.gv_drugallergy,
            R.drawable.gv_substanceallergy, R.drawable.gv_herediatoryconcern, R.drawable.gv_immunization,
            R.drawable.gv_diseasehistory, R.drawable.gv_treatmentundergone,R.drawable.gv_surgicalhistory,
            R.drawable.gv_healthrecord, R.drawable.gv_radiologicalinvestigation, R.drawable.gv_prescription,
            R.drawable.gv_diagonosticinvestigation,R.drawable.gv_labreport,R.drawable.gv_shareprofile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //For fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Custom ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_mainactivity);
        TextView title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Home");

        //Navigation Drawer Start
        drawerLayout = findViewById(R.id.main_drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.main_navigation);
        nv.setNavigationItemSelectedListener(this);
        //Navigation Drawer End

        simpleGrid = findViewById(R.id.simpleGridView);

        // Instance of ImageAdapter Class
        ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(), mThumbIds);
        simpleGrid.setAdapter(imageAdapter);
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = null;
                if(position == 0){
                    myIntent = new Intent(view.getContext(), PersonalprofileActivity.class);
                }
                if(position == 1){
                    myIntent = new Intent(view.getContext(), BehaviouralhealthriskActivity.class);
                }
                if(position == 2){
                    myIntent = new Intent(view.getContext(), DrugAllergyActivity.class);
                }
                if(position == 3){
                    myIntent = new Intent(view.getContext(), SubstanceAllergyActivity.class);
                }
                if(position == 4){
                    myIntent = new Intent(view.getContext(), HeridiatoryConcernActivity.class);
                }
                if(position == 5){
                    myIntent = new Intent(view.getContext(), ImmunizationActivity.class);
                }
                if(position == 6){
                    myIntent = new Intent(view.getContext(), DiseaseHistoryActivity.class);
                }
                if(position == 7){
                    myIntent = new Intent(view.getContext(), TreatmentUndergoneActivity.class);
                }
                if(position == 8){
                    myIntent = new Intent(view.getContext(), SurgicalHistoryActivity.class);
                }
                if(position == 9){
                    myIntent = new Intent(view.getContext(), HealthRecordActivity.class);
                }
                if(position == 10){
                    myIntent = new Intent(view.getContext(), RadiologicalInvestigationActivity.class);
                }
                if(position == 11){
                    myIntent = new Intent(view.getContext(), PrescriptionActivity.class);
                }
                if(position == 12){
                    myIntent = new Intent(view.getContext(), DiagnosticInvestigationActivity.class);
                }
                if(position == 13){
                    myIntent = new Intent(view.getContext(), LabReportActivity.class);
                }
                startActivity(myIntent);
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
