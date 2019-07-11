package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.TabMyOreders;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderViewPgerFragment extends Fragment {


    @BindView(R.id.tab_myReq_orede)
    TabLayout tabMyReqOrede;
    @BindView(R.id.viewpager_myrequest)
    ViewPager viewpagerMyrequest;
    Unbinder unbinder;

    public MyOrderViewPgerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order_vieawpager, container, false);
        unbinder = ButterKnife.bind(this, view);
        tabViewPager();
        return view;

    }

    private void tabViewPager(){
        TabMyOreders tabMyOreders = new TabMyOreders(getFragmentManager());
        viewpagerMyrequest.setAdapter(tabMyOreders);
        tabMyReqOrede.setupWithViewPager(viewpagerMyrequest);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
