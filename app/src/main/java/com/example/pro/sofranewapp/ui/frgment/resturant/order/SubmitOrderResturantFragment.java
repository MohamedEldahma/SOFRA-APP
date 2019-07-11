package com.example.pro.sofranewapp.ui.frgment.resturant.order;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.TabSupmitOrde;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitOrderResturantFragment extends Fragment {


    @BindView(R.id.tab_submit_orede)
    TabLayout tabSubmitOrede;
    @BindView(R.id.viewpager_submit_order)
    ViewPager viewpagerSubmitOrder;
    Unbinder unbinder;

    public SubmitOrderResturantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submit_order_resturant, container, false);
        unbinder = ButterKnife.bind(this, view);
        submitOrderVIewPAger();
        return view;
    }
    private void submitOrderVIewPAger() {
        TabSupmitOrde tabSupmitOrder = new TabSupmitOrde(getFragmentManager());
        viewpagerSubmitOrder.setAdapter(tabSupmitOrder);
        tabSubmitOrede.setupWithViewPager(viewpagerSubmitOrder);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
