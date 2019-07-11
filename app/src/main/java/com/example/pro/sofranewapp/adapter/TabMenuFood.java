package com.example.pro.sofranewapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pro.sofranewapp.ui.frgment.clintorder.CommentEvaluationFragment;
import com.example.pro.sofranewapp.ui.frgment.clintorder.MenuFoodFragment;
import com.example.pro.sofranewapp.ui.frgment.clintorder.RestoreInformationFragment;

public class TabMenuFood extends FragmentPagerAdapter {

    public TabMenuFood(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new MenuFoodFragment();
            case 1:
                return new CommentEvaluationFragment();
            case 2:
                return new RestoreInformationFragment();
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
                return "قائمة الطعام ";
            case 1:
                return "التعليقات والتقييم";
            case 2:
                return "معلومات المتجر";
        }
        return super.getPageTitle(position);
    }
}