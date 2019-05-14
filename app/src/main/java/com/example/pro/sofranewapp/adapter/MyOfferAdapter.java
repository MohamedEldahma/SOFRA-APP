package com.example.pro.sofranewapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.model.resturant.myoffers.MyOffersDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOfferAdapter extends RecyclerView.Adapter<MyOfferAdapter.ViewHolder> {


    private List<MyOffersDatum> myOffersDatumList = new ArrayList<>();
    private Activity activity;
    private Context context;

    public MyOfferAdapter(List<MyOffersDatum> myOffersDatumList, Activity activity, Context context) {
        this.myOffersDatumList = myOffersDatumList;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_my_offers, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        MyOffersDatum datum = myOffersDatumList.get(i);
        String photo = datum.getPhotoUrl();
        Glide.with(context).load(photo).into(viewHolder.myOffersImg);
        String  name = datum.getName();
        viewHolder.myOffersName.setText(name);
        String nameResturant = datum.getRestaurant().getName();
        viewHolder.myOffersNameRest.setText(nameResturant);
        String date = datum.getUpdatedAt();
        viewHolder.myOffersDate.setText(date);
        String price = datum.getPrice();
        viewHolder.myOffersPrice.setText(price);


    }


    @Override
    public int getItemCount() {
        return myOffersDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.my_offers_img)
        ImageView myOffersImg;
        @BindView(R.id.my_offers_name)
        TextView myOffersName;
        @BindView(R.id.my_offers_name_rest)
        TextView myOffersNameRest;
        @BindView(R.id.my_offers_date)
        TextView myOffersDate;
        @BindView(R.id.my_offers_price)
        TextView myOffersPrice;
        @BindView(R.id.my_offers_Layout)
        RelativeLayout myOffersLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
