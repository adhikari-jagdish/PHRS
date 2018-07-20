package com.vayamtech.healthe_cord.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vayamtech.healthe_cord.Fragment.HeridiatoryConcern_fatherFragment;
import com.vayamtech.healthe_cord.Fragment.HeridiatoryConcern_motherFragment;

//*Created by Jagadish on 7/20/2018.*/
public class PageAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;
    public PageAdapter(FragmentManager fm, int NumofTabs) {
        super(fm);
        this.mNumOfTabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                HeridiatoryConcern_fatherFragment heridiatoryConcern_fatherFragment = new HeridiatoryConcern_fatherFragment();
                return heridiatoryConcern_fatherFragment;

            case 1:
                HeridiatoryConcern_motherFragment heridiatoryConcern_motherFragment = new HeridiatoryConcern_motherFragment();
                return heridiatoryConcern_motherFragment;
        default:
            return null;
        }


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
