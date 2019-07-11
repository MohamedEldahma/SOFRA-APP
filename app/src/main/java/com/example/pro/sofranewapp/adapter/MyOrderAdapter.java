package com.example.pro.sofranewapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.myorderclint.Item;
import com.example.pro.sofranewapp.data.model.clint.myorderclint.MyOrderDatum;
import com.example.pro.sofranewapp.data.model.resturant.accceptorderresturant.AccceptOrderResturant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {


    private List<MyOrderDatum> myOrderDatumList = new ArrayList<>();
    private Activity activity;
    private Context context;
    int show;


    public MyOrderAdapter(List<MyOrderDatum> myOrderDatumList, Activity activity, Context context, int show) {
        this.myOrderDatumList = myOrderDatumList;
        this.activity = activity;
        this.context = context;
        this.show = show;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_my_order, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int postion) {
        MyOrderDatum datum = myOrderDatumList.get(postion);
        String name = datum.getRestaurant().getName();
        String price = datum.getCost();
        String total = datum.getTotal();
        String delevarycost = datum.getRestaurant().getDeliveryCost();
        final String id = String.valueOf(datum.getId());
        List<Item> items = datum.getItems();
        for (Item item : items) {
            String photo = item.getPhotoUrl();
            Glide.with(context).load(photo).into(viewHolder.myOrderImg);
        }
        viewHolder.myOrderName.setText(name);
        viewHolder.myOrderDelevary.setText(delevarycost);
        viewHolder.myOrderId.setText(id);
        viewHolder.myOrderPrice.setText(price);
        viewHolder.myOrderTotal.setText(total);
        final String api_Token_clint = "HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB";
        final ApiSofraModel apiSofraModel;
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);

        if (show == 1) {
            viewHolder.myOrderLayoutComplet.setVisibility(View.GONE);

        } else {
            viewHolder.myOrderLayoutComplet.setVisibility(View.VISIBLE);

            viewHolder.orderBttnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiSofraModel.confirmOrderClint(Integer.parseInt(id),api_Token_clint)
                                .enqueue(new Callback<AccceptOrderResturant>() {
                                    @Override
                                    public void onResponse(Call<AccceptOrderResturant> call, Response<AccceptOrderResturant> response) {
                                        if (response.body().getStatus() == 1){
                                            Toast.makeText(activity, "CompleetOrder Confirm", Toast.LENGTH_SHORT).show();
                                            
                                        }else {
                                            Toast.makeText(activity, "CompleetOrder Not Confirm", Toast.LENGTH_SHORT).show();
                                            myOrderDatumList.remove(postion);

                                            notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AccceptOrderResturant> call, Throwable t) {
                                        Toast.makeText(activity, "Erorr", Toast.LENGTH_SHORT).show();

                                    }
                                });

                }
            });

            viewHolder.orderBttnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiSofraModel.cancelOrderClint(Integer.parseInt(id),api_Token_clint)
                               .enqueue(new Callback<AccceptOrderResturant>() {
                                   @Override
                                   public void onResponse(Call<AccceptOrderResturant> call, Response<AccceptOrderResturant> response) {
                                       if (response.body().getStatus() == 1){
                                           myOrderDatumList.remove(postion);

                                           notifyDataSetChanged();
                                           Toast.makeText(activity, "CompleetOrder Canceld", Toast.LENGTH_SHORT).show();
                                       }else {
                                           Toast.makeText(activity, "order Not Canceld", Toast.LENGTH_SHORT).show();
                                       }
                                   }

                                   @Override
                                   public void onFailure(Call<AccceptOrderResturant> call, Throwable t) {

                                   }
                               });
                }
            });

        }


    }


    @Override
    public int getItemCount() {
        return myOrderDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_Order_img)
        ImageView myOrderImg;
        @BindView(R.id.my_Order_name)
        TextView myOrderName;
        @BindView(R.id.my_Order_price)
        TextView myOrderPrice;
        @BindView(R.id.my_Order_delevary)
        TextView myOrderDelevary;
        @BindView(R.id.my_Order_total)
        TextView myOrderTotal;
        @BindView(R.id.my_Order_layout)
        RelativeLayout myOrderLayout;
        @BindView(R.id.my_Order_id)
        TextView myOrderId;
        @BindView(R.id.order_bttn_confirm)
        Button orderBttnConfirm;
        @BindView(R.id.order_bttn_cancel)
        Button orderBttnCancel;
        @BindView(R.id.my_Order_layout_complet)
        RelativeLayout myOrderLayoutComplet;
        @BindView(R.id.restaurant_edit_card_view)
        RelativeLayout restaurantEditCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
