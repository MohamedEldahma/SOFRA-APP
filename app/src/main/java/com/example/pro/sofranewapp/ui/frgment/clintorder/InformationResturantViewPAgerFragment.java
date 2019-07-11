package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaDetailCategory;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaDetailData;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaurantDetail;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.adapter.TabMenuFood;

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
public class InformationResturantViewPAgerFragment extends Fragment {


    @BindView(R.id.tab_menu_food)
    TabLayout tabMenuFood;
    @BindView(R.id.viewpager_menufood)
    ViewPager viewpagerMenufood;
    Unbinder unbinder;
    @BindView(R.id.item_imag_order)
    ImageView itemImagOrder;
    @BindView(R.id.resturant_Name)
    TextView resturantName;
    @BindView(R.id.text_resturant_typ)
    TextView textResturantTyp;
    @BindView(R.id.rting_food)
    RatingBar rtingFood;
    @BindView(R.id.restaurant_avilblty_tv)
    TextView restaurantAvilbltyTv;
    @BindView(R.id.minmum_order)
    TextView minmumOrder;
    @BindView(R.id.cost_order)
    TextView costOrder;
    ApiSofraModel apiSofraModel;
    private int id_resturant;

    public InformationResturantViewPAgerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information_resturant_view_pager, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel= RerofitSofraClint.getClient().create(ApiSofraModel.class);
        id_resturant = SharedPrefrancClass.LoadIntegerData(getActivity(),"id_Resturant");
        resturantDetail();

        menFoodViewPAger();
        return view;
    }


    private void menFoodViewPAger() {
        TabMenuFood tabMenuFod = new TabMenuFood(getFragmentManager());
        viewpagerMenufood.setAdapter(tabMenuFod);
        tabMenuFood.setupWithViewPager(viewpagerMenufood);
    }



   private void resturantDetail() {
        apiSofraModel.getResturantDetail(id_resturant)
                .enqueue(new Callback<RestaurantDetail>() {
                    @Override
                    public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {


                        long status = response.body().getStatus();
                        if (status == 1){
                            RestaDetailData  data = response.body().getData();
                        /*    String photo = data.getPhotoUrl();
                            final String name =data.getName();
                            String avilabilty = data.getAvailability();
                            int rateResturant= Integer.parseInt(data.getRate());
                            final String delevryCost=data.getDeliveryCost();
                            String  minimumdelevr = data.getMinimumCharger();


                            Glide.with(getContext()).load(photo).into(itemImagOrder);
                            resturantName.setText(name);
                           restaurantAvilbltyTv.setText(avilabilty);
                            rtingFood.setNumStars(rateResturant);
                            costOrder.setText(delevryCost);
                            minmumOrder.setText(minimumdelevr);
                            final StringBuilder builder = new StringBuilder();
                            List<RestaDetailCategory> categories = data.getCategories();
                            for (int j = 0; j < categories.size(); j++) {
                                builder.append(categories.get(j).getName()+",");

                            }
                            textResturantTyp.setText(builder.toString());*/
                            String name = data.getName();
                            String avilaPility=data.getAvailability();
                            String minimumCharg = data.getMinimumCharger();
                            String delviryCost = data.getDeliveryCost();
                            String photo =data.getPhotoUrl();
                            StringBuilder builder = new StringBuilder();
                            int rate = Integer.parseInt(data.getRate());
                            List<RestaDetailCategory> categories = data.getCategories();
                            for (int j = 0; j < categories.size(); j++) {
                                builder.append(categories.get(j).getName()+",");
                            }
                            Glide.with(getContext()).load(photo).into(itemImagOrder);
                            resturantName.setText(name);
                            restaurantAvilbltyTv.setText(avilaPility);
                            minmumOrder.setText(minimumCharg);
                            costOrder.setText(delviryCost);
                            textResturantTyp.setText(builder.toString());
                            rtingFood.setNumStars(rate);


                        }else {
                            Toast.makeText(getActivity(), "eroor", Toast.LENGTH_SHORT).show();}


                    }
                    @Override
                    public void onFailure(Call<RestaurantDetail> call, Throwable t) {

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
