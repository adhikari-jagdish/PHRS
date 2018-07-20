package com.vayamtech.healthe_cord.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.vayamtech.healthe_cord.Fragment.SubstanceAllergy_oneFragment;
import com.vayamtech.healthe_cord.Fragment.SurgicalHistory_oneFragment;
import com.vayamtech.healthe_cord.R;
import com.vayamtech.healthe_cord.Utils.BaseActivity;

public class SurgicalHistoryActivity extends BaseActivity{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgical_history);

        //For fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Custom ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_mainactivity);
        TextView title=findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Surgical History");

        //Navigation Drawer Start
        drawerLayout = findViewById(R.id.SurgicalHistory_drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Navigation Drawer End

        //Fragment Part Starts
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        SurgicalHistory_oneFragment surgicalHistory_oneFragment = new SurgicalHistory_oneFragment()  ;
        fragmentTransaction.replace(R.id.fragment_SurgicalHistory_container, surgicalHistory_oneFragment);
        fragmentTransaction.commit();
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
