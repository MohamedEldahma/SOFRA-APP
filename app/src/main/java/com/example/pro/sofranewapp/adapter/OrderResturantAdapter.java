package com.example.pro.sofranewapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.accceptorderresturant.AccceptOrderResturant;
import com.example.pro.sofranewapp.data.model.resturant.myorderresturant.MyOrderDatum;
import com.example.pro.sofranewapp.ui.frgment.resturant.order.CompletOrderResturant;
import com.example.pro.sofranewapp.ui.frgment.resturant.order.CurrentOrderResturantFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pro.sofranewapp.helper.HelperMethod.replaceFrag;

public class OrderResturantAdapter extends RecyclerView.Adapter<OrderResturantAdapter.ViewHolder> {

    private List<MyOrderDatum> myOrderDatumList = new ArrayList<>();
    private Activity activity;
    private Context context;
    private int show;

    public OrderResturantAdapter(List<MyOrderDatum> myOrderDatumList, Activity activity, Context context, int show) {
        this.myOrderDatumList = myOrderDatumList;
        this.activity = activity;
        this.context = context;
        this.show = show;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_submit_order_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        MyOrderDatum datum = myOrderDatumList.get(i);
        final String name = datum.getClient().getName();
        String cost = datum.getCost();
        String delevriCost = datum.getDeliveryCost();
        String total   = datum.getTotal();
        String adress = datum.getAddress();
        final String id= String.valueOf(datum.getId());
        final String phone = datum.getClient().getPhone();

        viewHolder.submitOrderName.setText(name);
        viewHolder.submitOrderPrice.setText(cost);
        viewHolder.submitOrderDeliveryCost.setText(delevriCost);
        viewHolder.submitOrderTotal.setText(total);
        viewHolder.submitOrderAddress.setText(adress);
        viewHolder.submitOrderId.setText("رقم الطلب :"+id);
        viewHolder.submitOrderBttnNumber.setText(phone);

        final String api_tokenResturant = "quW3tUS7GVL5lv1BfAT0Orm4CXBtmRVREu3tCP6B5WebYsVaIQYdeoyg7yay";
        final ApiSofraModel apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        viewHolder.submitOrderBttnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+Uri.encode(phone.trim())));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);

            }
        });
        if (show == 1) {
            viewHolder.submitOrderCompleteOrder.setVisibility(View.GONE);
            viewHolder.submitOrderBttnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiSofraModel.acceptOrderRestrant(api_tokenResturant,id)
                                .enqueue(new Callback<AccceptOrderResturant>() {
                                    @Override
                                    public void onResponse(Call<AccceptOrderResturant> call, Response<AccceptOrderResturant> response) {
                                        if (response.body().getStatus() ==1){
                                            myOrderDatumList.remove(i);
                                            Toast.makeText(activity, "CompleetOrder Accept", Toast.LENGTH_SHORT).show();
                                            replaceFrag(new CurrentOrderResturantFragment(),((FragmentActivity)context).getSupportFragmentManager()
                                                    ,R.id.id_fram_Home_nvigation1);

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AccceptOrderResturant> call, Throwable t) {

                                    }
                                });

                }
            });

            viewHolder.submitOrderBttnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 final String   api_tokenResturant = "EuqQtEiKiG4OfshU49UltxUnvySicD3T1eW4BBjdjIlMqyGJPlYauzTOH0l";
                 apiSofraModel.cancelOrderRestrant(api_tokenResturant,id)
                         .enqueue(new Callback<AccceptOrderResturant>() {
                             @Override
                             public void onResponse(Call<AccceptOrderResturant> call, Response<AccceptOrderResturant> response) {
                                 if (response.body().getStatus() == 1){
                                     Toast.makeText(activity, "CompleetOrder Cancel", Toast.LENGTH_SHORT).show();
                                     myOrderDatumList.remove(i);
                                     notifyDataSetChanged();

                                 }else {

                                     Toast.makeText(activity, "CompleetOrder Not Cancel", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onFailure(Call<AccceptOrderResturant> call, Throwable t) {
                                 Toast.makeText(activity, "CompleetOrder Erorr", Toast.LENGTH_SHORT).show();


                             }
                         });

                }
            });



        }else if (show == 2){
            viewHolder.submitOrderCompleteOrder.setVisibility(View.GONE);
            viewHolder.submitOrderBttnCancel.setVisibility(View.GONE);
            viewHolder.submitOrderBttnAccept.setText("تاكيد التسليم");
            viewHolder.submitOrderBttnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiSofraModel.confirmOrderRestrant(api_tokenResturant,id)
                            .enqueue(new Callback<AccceptOrderResturant>() {
                                @Override
                                public void onResponse(Call<AccceptOrderResturant> call, Response<AccceptOrderResturant> response) {
                                    if (response.body().getStatus() == 1){
                                        Toast.makeText(activity, "CompleetOrder Confirmd", Toast.LENGTH_SHORT).show();
                                        replaceFrag(new CompletOrderResturant(),((FragmentActivity)context).getSupportFragmentManager()
                                                ,R.id.id_fram_Home_nvigation1);
                                    }else {Toast.makeText(activity, "CompleetOrder Not Confirmd", Toast.LENGTH_SHORT).show();}
                                }

                                @Override
                                public void onFailure(Call<AccceptOrderResturant> call, Throwable t) {
                                    Toast.makeText(activity, "CompleetOrder Eroro", Toast.LENGTH_SHORT).show();

                                }
                            });

                }
            });



        }else {
            viewHolder.submitOrderLayoutBttn.setVisibility(View.GONE);
            viewHolder.submitOrderCompleteOrder.setVisibility(View.VISIBLE);
            viewHolder.submitOrderCompleteOrder.setText("الطلب مكتمل");

        }


    }


    @Override
    public int getItemCount() {
        return myOrderDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.submit_order_img_item)
        ImageView submitOrderImgItem;
        @BindView(R.id.submit_order_name)
        TextView submitOrderName;
        @BindView(R.id.submit_order_id)
        TextView submitOrderId;
        @BindView(R.id.submit_order_price)
        TextView submitOrderPrice;
        @BindView(R.id.submit_order_delivery_cost)
        TextView submitOrderDeliveryCost;
        @BindView(R.id.submit_order_total)
        TextView submitOrderTotal;
        @BindView(R.id.submit_order_address)
        TextView submitOrderAddress;
        @BindView(R.id.submit_order_layout)
        LinearLayout submitOrderLayout;
        @BindView(R.id.submit_order_bttn_number)
        Button submitOrderBttnNumber;
        @BindView(R.id.submit_order_bttn_accept)
        Button submitOrderBttnAccept;
        @BindView(R.id.submit_order_bttn_cancel)
        Button submitOrderBttnCancel;
        @BindView(R.id.submit_order_layout_bttn)
        LinearLayout submitOrderLayoutBttn;
        @BindView(R.id.submit_order_complete_order)
        TextView submitOrderCompleteOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
