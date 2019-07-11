package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.ItemResturantRcyclerAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.general.restaurantslslt.RestaurantsLslt;
import com.example.pro.sofranewapp.data.model.general.restaurantslslt.RestaurantsLsltDatum;

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
public class HomeOrderFragment extends Fragment {


    Unbinder unbinder;

    ApiSofraModel apiSofraModel;
    @BindView(R.id.sarch_order_food)
    SearchView sarchOrderFood;
    @BindView(R.id.spienr_order_food)
    Spinner spienrOrderFood;
    @BindView(R.id.recycler_resturant_List)
    RecyclerView recyclerResturantList;
    private List<RestaurantsLsltDatum> restaurantData = new ArrayList<>();
    ItemResturantRcyclerAdapter itemResturantRcyclerAdapter;

    public HomeOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
//        recyclerResturantList.setHasFixedSize(true);
//        recyclerResturantList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getRecyclerResturant();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getRecyclerResturant() {
//        icProgresBar.setVisibility(View.VISIBLE);
        apiSofraModel.getResturantList(1)
                .enqueue(new Callback<RestaurantsLslt>() {
                    @Override
                    public void onResponse(Call<RestaurantsLslt> call, Response<RestaurantsLslt> response) {
                        if (response.body().getStatus() == 1) {
                            recyclerResturantList.setHasFixedSize(true);
                            recyclerResturantList.setLayoutManager(new LinearLayoutManager(getContext()));
                            itemResturantRcyclerAdapter = new ItemResturantRcyclerAdapter(restaurantData, getContext(), getActivity());
                            recyclerResturantList.setAdapter(itemResturantRcyclerAdapter);

                            List<RestaurantsLsltDatum> data = response.body().getData().getData();
                            restaurantData.addAll(data);
                            itemResturantRcyclerAdapter.notifyDataSetChanged();

                       /*
                          List<RestaurantsLsltDatum> restaurantsListData = response.body().getData().getData();
                          ItemResturantRcyclerAdapter adapter = new ItemResturantRcyclerAdapter(restaurantsListData, getContext(),getActivity());
                             recyclerResturantList.setAdapter(adapter);*/

                        } else {
                            Toast.makeText(getContext(), "recycle eroor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantsLslt> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();


                    }
                });


    }
}
