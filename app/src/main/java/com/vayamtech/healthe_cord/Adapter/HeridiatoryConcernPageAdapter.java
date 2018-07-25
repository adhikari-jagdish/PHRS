package com.vayamtech.healthe_cord.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vayamtech.healthe_cord.Fragment.HeridiatoryConcern_fatherFragment;
import com.vayamtech.healthe_cord.Fragment.HeridiatoryConcern_motherFragment;

//*Created by Jagadish on 7/25/2018.*/
public class HeridiatoryConcernPageAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"Father", "Mother"};
    public HeridiatoryConcernPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HeridiatoryConcern_fatherFragment();
        }
        else {
            return new HeridiatoryConcern_motherFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
