package com.example.pro.sofranewapp.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.model.clint.listrestitems.ListRestItemsDatum;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.general.DisplayFoodItemFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pro.sofranewapp.helper.HelperMethod.replaceFrag;

public class ListResturantItemAdapter extends RecyclerView.Adapter<ListResturantItemAdapter.ViewHolder> {

    List<ListRestItemsDatum> myItems;
    Activity activity;
    Context context;
    public String resturantId;

//    public static  ListRestItemsDatum  itemFoodDataRoom;

    public ListResturantItemAdapter(List<ListRestItemsDatum> myItemsData, Activity activity, Context context) {
        this.myItems = myItemsData;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_my_food, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        ListRestItemsDatum  itemsData = myItems.get(position);
       final String photo = itemsData.getPhotoUrl();
        Glide.with(context).load(photo).into(viewHolder.photoMyitem);
        final String name = itemsData.getName();
        viewHolder.textItemName.setText(name);
        final String description =itemsData.getDescription();
        viewHolder.textDescripName.setText(description);
        final String price = itemsData.getPrice();
        viewHolder.textItemPric.setText(price);
        final String perparingTime = itemsData.getPreparingTime();
        final int id =itemsData.getId();

        resturantId = itemsData.getRestaurantId();
        viewHolder.myIteCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                itemFoodDataRoom=myItems.get(position);
                DisplayFoodItemFragment displayFoodItemFragment = new DisplayFoodItemFragment();
                displayFoodItemFragment.getitem_photo = photo;
                displayFoodItemFragment.getitem_desc = description;
                displayFoodItemFragment.getitem_name = name;
                displayFoodItemFragment.getitem_price = price;
                displayFoodItemFragment.getitem_wait = perparingTime;
                displayFoodItemFragment.idItem = id;
                displayFoodItemFragment.id_Resturant = resturantId;

                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                replaceFrag(displayFoodItemFragment,fragmentManager,R.id.id_fram_Home_nvigation1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_myitem)
        ImageView photoMyitem;
        @BindView(R.id.text_item_name)
        TextView textItemName;
        @BindView(R.id.text_descrip_name)
        TextView textDescripName;
        @BindView(R.id.neme_pric)
        TextView nemePric;
        @BindView(R.id.text_item_pric)
        TextView textItemPric;
//        @BindView(R.id.text_item_curancy)
//        TextView textItemCurancy;
        @BindView(R.id.my_ite_cardview)
        CardView myIteCardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
