package com.example.pro.sofranewapp.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.model.resturant.myitem.MyItemDatum;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.ViewHolder> {

   private List<MyItemDatum> myItemsData;
    private Context context;
    private Activity activity;
   public  String idResturant;

    public MyItemAdapter(List<MyItemDatum> myItemsData, Context context, Activity activity) {
        this.myItemsData = myItemsData;
        this.context = context;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_my_food, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MyItemDatum  itemsDatum = myItemsData.get(position);

       final String photo = itemsDatum.getPhotoUrl();
        Glide.with(context).load(photo).into(viewHolder.photoMyitem);
       final String name = itemsDatum.getName();
        viewHolder.textItemName.setText(name);
       final String description = itemsDatum.getDescription();
        viewHolder.textDescripName.setText(description);
       final String price = itemsDatum.getPrice();
        viewHolder.textItemPric.setText(price);
//        final long id =itemsDatum.getId();
//        idResturant = itemsDatum.getRestaurantId();

    }

    @Override
    public int getItemCount() {
        return myItemsData.size();
    }

    static

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
        @BindView(R.id.my_ite_cardview)
        CardView myIteCardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
