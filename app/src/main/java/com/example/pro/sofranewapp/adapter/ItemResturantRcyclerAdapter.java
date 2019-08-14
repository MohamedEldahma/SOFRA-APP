package com.example.pro.sofranewapp.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;

import com.example.pro.sofranewapp.data.model.general.restaurantslslt.RestaurantsLsltCategory;
import com.example.pro.sofranewapp.data.model.general.restaurantslslt.RestaurantsLsltDatum;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.helper.SharedPrefManager;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.clintorder.InformationResturantViewPAgerFragment;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.constraint.Constraints.TAG;
import static com.example.pro.sofranewapp.helper.HelperMethod.replaceFrag;

public class ItemResturantRcyclerAdapter extends RecyclerView.Adapter<ItemResturantRcyclerAdapter.ViewHolder> {

    private List<RestaurantsLsltDatum> restaurantsList ;

  private    Context context;
   private   Activity activity;


    public ItemResturantRcyclerAdapter(List<RestaurantsLsltDatum> restaurantsList, Context context, Activity activity) {
        this.restaurantsList = restaurantsList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_resturant_food, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int postion) {


        RestaurantsLsltDatum datum = restaurantsList.get(postion);
        String photo = datum.getPhotoUrl();
        final String name =datum.getName();
        final String avilabilty = datum.getAvailability();
        int rateResturant= Integer.parseInt(datum.getRate());
        final String delevryCost=datum.getDeliveryCost();
        String  minimumdelevr = datum.getMinimumCharger();


        Glide.with(context).load(photo).into(viewHolder.itemImagOrder);
         viewHolder.resturantName.setText(name);
        viewHolder.restaurantAvilbltyTv.setText(avilabilty);
        viewHolder.rtingFood.setNumStars(rateResturant);
        viewHolder.costOrder.setText(delevryCost);
        viewHolder.minmumOrder.setText(minimumdelevr);
        final StringBuilder builder = new StringBuilder();
        List<RestaurantsLsltCategory> categories = datum.getCategories();
        for (int j = 0; j < categories.size(); j++) {
            builder.append(categories.get(j).getName()+",");

        }
        viewHolder.textResturantTyp.setText(builder.toString());
        Log.d(TAG, "onBindViewHolder: ");


        viewHolder.cardviewResturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationResturantViewPAgerFragment informationResturantViewPAgerFragment = new InformationResturantViewPAgerFragment();
                SharedPrefrancClass.SaveData(activity,"id_Resturant",restaurantsList.get(postion).getId());
                SharedPrefrancClass.SaveData(activity,"Item_Delivary_Cost",delevryCost);
                replaceFrag(new InformationResturantViewPAgerFragment(), ((FragmentActivity)context).getSupportFragmentManager(),R.id.id_fram_Home_nvigation1);


            }
        });



////        Picasso.get().load(RestaurantsLsltDatum.getPhotoUrl()).into(viewHolder.itemImagOrder);
//
//        Glide.with(context)
//                .load(RestaurantsLsltDatum.getPhotoUrl())
//                .into(viewHolder.itemImagOrder);
//        viewHolder.textResturantTyp.setText(RestaurantsLsltDatum.getCategories().iterator().next().getName());
//        viewHolder.resturantName.setText(RestaurantsLsltDatum.getName());
//        viewHolder.rtingFood.setRating(Float.parseFloat(RestaurantsLsltDatum.getRate()));
//        viewHolder.numbMinmumOrder.setText(RestaurantsLsltDatum.getMinimumCharger());
//        viewHolder.numbCostOrder.setText(RestaurantsLsltDatum.getDeliveryCost());

    }

    @Override
    public int getItemCount() {

        return restaurantsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        TextView  minmumOrder;
        @BindView(R.id.cost_order)
        TextView costOrder;
        @BindView(R.id.cardview_resturant)
        CardView cardviewResturant;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
