package com.vayamtech.healthe_cord.Activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.vayamtech.healthe_cord.Fragment.drugAllergy_oneFragment;
import com.vayamtech.healthe_cord.Fragment.drugAllergy_twoFragment;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.BaseActivity;

public class DrugAllergyActivity extends BaseActivity implements drugAllergy_twoFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_allergy);
        //For fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Custom ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_mainactivity);
        TextView title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Drug Allergy");

        //Navigation Drawer Start
        drawerLayout = findViewById(R.id.DrugAllergy_drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.DrugAllergy_navigation);
        nv.setNavigationItemSelectedListener(this);
        //Navigation Drawer End

        //Fragment Starts
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        drugAllergy_oneFragment dof = new drugAllergy_oneFragment() ;
        fragmentTransaction.replace(R.id.fragment_container, dof);
        fragmentTransaction.commit();




    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

              return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
