package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaDetailCity;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaDetailData;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaDetailRegion;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaurantDetail;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestoreInformationFragment extends Fragment {


    @BindView(R.id.restaurant_info_status)
    TextView restaurantInfoStatus;
    @BindView(R.id.restaurant_info_city)
    TextView restaurantInfoCity;
    @BindView(R.id.restaurant_info_region)
    TextView restaurantInfoRegion;
    @BindView(R.id.restaurant_info_min_order)
    TextView restaurantInfoMinOrder;
    @BindView(R.id.restaurant_info_delivery_cost)
    TextView restaurantInfoDeliveryCost;
    Unbinder unbinder;
    ApiSofraModel apiSofraModel;
    private int id_resturant;

    public RestoreInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_information, container, false);
        unbinder = ButterKnife.bind(this, view);
        id_resturant = SharedPrefrancClass.LoadIntegerData(getActivity(), "id_Resturant");
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        getResturantData();
        return view;
    }



    public  void  getResturantData(){
        apiSofraModel.getResturantDetail(id_resturant)
                     .enqueue(new Callback<RestaurantDetail>() {
                         @Override
                         public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {
                         if (response.body().getStatus() == 1){
                             RestaDetailData data = response.body().getData();
                             String  avilabelty = data.getAvailability();
                             RestaDetailRegion region = data.getRegion();
                             String     regionNAme = region.getName();
                             RestaDetailCity city = region.getCity();
                             String cityName  = city.getName();
                             String mnimumOrder = data.getMinimumCharger();
                             String delevaryCost = data.getDeliveryCost();

                             restaurantInfoStatus.setText(avilabelty);
                             restaurantInfoCity.setText(cityName);
                             restaurantInfoRegion.setText(regionNAme);
                             restaurantInfoMinOrder.setText(mnimumOrder);
                             restaurantInfoDeliveryCost.setText(delevaryCost);
                         }else {
                             Toast.makeText(getContext(), "False", Toast.LENGTH_SHORT).show();
                         }
                         }

                         @Override
                         public void onFailure(Call<RestaurantDetail> call, Throwable t) {
                             Toast.makeText(getContext(), "Erorr", Toast.LENGTH_SHORT).show();
                         }
                     });




    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
