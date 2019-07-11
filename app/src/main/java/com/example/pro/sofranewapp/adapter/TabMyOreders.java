package com.example.pro.sofranewapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pro.sofranewapp.ui.frgment.clintorder.CurentOrderFragment;
import com.example.pro.sofranewapp.ui.frgment.clintorder.PreviousOrderFragment;

public class TabMyOreders extends FragmentPagerAdapter {

    public TabMyOreders(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new CurentOrderFragment();
            case 1:
                return new PreviousOrderFragment();
            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return " طلبات حاليه ";
            case 1:
                return "طلبات سابقه";
        }
        return super.getPageTitle(position);
    }
}