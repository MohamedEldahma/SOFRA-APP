package com.example.pro.sofranewapp.ui.frgment.resturant.order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.OrderResturantAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.myorderresturant.MyOrderDatum;
import com.example.pro.sofranewapp.data.model.resturant.myorderresturant.MyOrderResturant;

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
public class CompletOrderResturant extends Fragment {


    @BindView(R.id.complet_empty)
    TextView completEmpty;
    @BindView(R.id.compleet_order_recycler)
    RecyclerView compleetOrderRecycler;
    Unbinder unbinder;
    ApiSofraModel apiSofraModel;
   private String   api_tokenResturant;
   private OrderResturantAdapter orderResturantAdapter;
   private List<MyOrderDatum> orderDatumList = new ArrayList<>();
    public CompletOrderResturant() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complet_order_resturant, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        api_tokenResturant = "quW3tUS7GVL5lv1BfAT0Orm4CXBtmRVREu3tCP6B5WebYsVaIQYdeoyg7yay";
        completedMyOrder();

        return view;
    }



    public void completedMyOrder() {
        apiSofraModel.getMyOrderResturant(api_tokenResturant, "completed", 1)
                .enqueue(new Callback<MyOrderResturant>() {
                    @Override
                    public void onResponse(Call<MyOrderResturant> call, Response<MyOrderResturant> response) {
                        if (response.body().getStatus() == 1) {
                           compleetOrderRecycler.setHasFixedSize(true);
                            compleetOrderRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                            orderResturantAdapter = new OrderResturantAdapter(orderDatumList, getActivity(), getContext(), 3);
                            compleetOrderRecycler.setAdapter(orderResturantAdapter);
                            List<MyOrderDatum> data = response.body().getData().getData();
                            orderDatumList.addAll(data);
                            orderResturantAdapter.notifyDataSetChanged();


                            if (orderDatumList.isEmpty()) {
                                completEmpty.setVisibility(View.VISIBLE);
                            } else {
                                completEmpty.setVisibility(View.GONE);
                            }

                        } else {
                            Toast.makeText(getActivity(), "No CompleetOrder AddNewOfferData", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<MyOrderResturant> call, Throwable t) {
                        Toast.makeText(getActivity(), "Erorr", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
