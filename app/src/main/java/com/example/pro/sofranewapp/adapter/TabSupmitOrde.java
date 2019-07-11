package com.example.pro.sofranewapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.pro.sofranewapp.ui.frgment.resturant.order.CompletOrderResturant;
import com.example.pro.sofranewapp.ui.frgment.resturant.order.CurrentOrderResturantFragment;
import com.example.pro.sofranewapp.ui.frgment.resturant.order.NewOrderResturantFragment;

public class TabSupmitOrde extends FragmentPagerAdapter {

    public TabSupmitOrde(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new NewOrderResturantFragment();
            case 1:
                return new CurrentOrderResturantFragment();
            case 2:
                return new CompletOrderResturant();
            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "طلبات جديده";
            case 1:
                return "طلبات حاليه";
            case 2:
                return "طلبات سابقه";
        }
        return super.getPageTitle(position);
    }
}