package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.MyOrderAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.myorderclint.MyOrderClint;
import com.example.pro.sofranewapp.data.model.clint.myorderclint.MyOrderDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousOrderFragment extends Fragment {


    @BindView(R.id.compleet_oreder_recycler)
    RecyclerView compleetOrederRecycler;
    Unbinder unbinder;
    ApiSofraModel apiSofraModel;
    private String api_Token_clint;
     private MyOrderAdapter myOrderAdapter;
    private List<MyOrderDatum> myOrderDatum = new ArrayList<>();

    public PreviousOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        api_Token_clint = "HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB";
        compleetOrder();
        return view;
    }

    public void  compleetOrder(){
        apiSofraModel.getMyOrder(api_Token_clint,"completed",1)
                .enqueue(new Callback<MyOrderClint>() {
                    @Override
                    public void onResponse(Call<MyOrderClint> call, Response<MyOrderClint> response) {

                        compleetOrederRecycler.setHasFixedSize(true);
                        compleetOrederRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                        myOrderAdapter = new MyOrderAdapter(myOrderDatum,getActivity(),getContext(),2);
                        compleetOrederRecycler.setAdapter(myOrderAdapter);
                        List<MyOrderDatum> data = response.body().getData().getData();
                        myOrderDatum.addAll(data);
                        myOrderAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<MyOrderClint> call, Throwable t) {

                    }
                });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
