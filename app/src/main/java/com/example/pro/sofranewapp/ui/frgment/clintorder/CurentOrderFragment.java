package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.MyOrderAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.myorderclint.MyOrderClint;
import com.example.pro.sofranewapp.data.model.clint.myorderclint.MyOrderDatum;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;

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
public class CurentOrderFragment extends Fragment {


    @BindView(R.id.curren_Recycler_View_Order)
    RecyclerView currenRecyclerViewOrder;
    Unbinder unbinder;
   private   List<MyOrderDatum> myOrderDatum = new ArrayList<>();
     String api_Token_clint;
    ApiSofraModel apiSofraModel;
    MyOrderAdapter myOrderAdapter;

    public CurentOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curent_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
//        api_Token_clint = "HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB";
        api_Token_clint = SharedPrefrancClass.LoadStringData(getActivity(), "api_token_clint");
        myOrder();
        return view;
    }


    public void  myOrder(){
        apiSofraModel.getMyOrder(api_Token_clint,"current",1)
                     .enqueue(new Callback<MyOrderClint>() {
                         @Override
                         public void onResponse(Call<MyOrderClint> call, Response<MyOrderClint> response) {
                             if (response.body().getStatus() == 1){
                                 currenRecyclerViewOrder.setHasFixedSize(true);
                                 currenRecyclerViewOrder.setLayoutManager(new LinearLayoutManager(getContext()));
                                 myOrderAdapter = new MyOrderAdapter(myOrderDatum,getActivity(),getContext(),1);
                                 currenRecyclerViewOrder.setAdapter(myOrderAdapter);

                                   List<MyOrderDatum> data = response.body().getData().getData();

                                 myOrderDatum.addAll(data);
                                 myOrderAdapter.notifyDataSetChanged();
                                 Toast.makeText(getActivity(), "MyOffersData Tru", Toast.LENGTH_SHORT).show();


                             }else {
                                 Toast.makeText(getActivity(), "MyOffersData False", Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(Call<MyOrderClint> call, Throwable t) {
                             Toast.makeText(getActivity(), "MyOffersData Erorr", Toast.LENGTH_SHORT).show();

                         }
                     });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
