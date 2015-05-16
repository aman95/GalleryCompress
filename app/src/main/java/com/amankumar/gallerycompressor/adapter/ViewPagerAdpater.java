package com.amankumar.gallerycompressor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.amankumar.gallerycompressor.compress_fragment;

/**
 * Created by aman on 16/5/15.
 */
public class ViewPagerAdpater extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs;

    public ViewPagerAdpater(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment tab = null;
        switch (position) {
            case 0: {
                tab = new compress_fragment();
                break;
            }
            case 1: {
                tab = new compress_fragment();
                break;
            }
            case 2: {
                tab = new compress_fragment();
                break;
            }
        }

        return tab;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}