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
import com.example.pro.sofranewapp.adapter.MyOfferAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.myoffers.MyOffers;
import com.example.pro.sofranewapp.data.model.resturant.myoffers.MyOffersDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewOffer extends Fragment {

    @BindView(R.id.my_offers_recucl_view)
    RecyclerView myOffersRecuclView;
    Unbinder unbinder;
    private List<MyOffersDatum> myOffersData = new ArrayList<>();
    MyOfferAdapter myOfferAdapter;
    ApiSofraModel apiSofraModel;
    String api_token;

    public NewOffer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_offers, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        api_token = "OGqjF8iGLccLQqdOJ11gHDTzdwG6980twebZRnN66mOFWh2P0Qwb3UCFHboc";
        MyOffer();
        return view;
    }


    public void  MyOffer(){
        apiSofraModel.getmyOffer(api_token,1)
                .enqueue(new Callback<MyOffers>() {
                    @Override
                    public void onResponse(Call<MyOffers> call, Response<MyOffers> response) {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getActivity(), "Your Offer Tru", Toast.LENGTH_SHORT).show();
                            myOffersRecuclView.setHasFixedSize(true);
                            myOffersRecuclView.setLayoutManager(new LinearLayoutManager(getContext()));
                            myOfferAdapter = new MyOfferAdapter(myOffersData, getActivity(), getContext());
                            myOffersRecuclView.setAdapter(myOfferAdapter);
                            List<MyOffersDatum> data = response.body().getData().getData();
                            myOffersData.addAll(data);
                            myOfferAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getActivity(), "Your Offer False", Toast.LENGTH_SHORT).show();}

                    }

                    @Override
                    public void onFailure(Call<MyOffers> call, Throwable t) {

                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                    }
                });





    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
