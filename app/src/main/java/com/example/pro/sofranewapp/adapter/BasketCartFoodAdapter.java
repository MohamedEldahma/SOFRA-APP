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

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.local.dataroom.ItemFoodDataModel;
import com.example.pro.sofranewapp.data.local.dataroom.RoomItemDao;
import com.example.pro.sofranewapp.data.local.dataroom.RoomManger;
import com.example.pro.sofranewapp.ui.frgment.general.DisplayFoodItemFragment;

import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasketCartFoodAdapter extends RecyclerView.Adapter<BasketCartFoodAdapter.ViewHolder> {

    public static double totalAll  = 0;
    private Context context;
    private Activity activity;
    List<ItemFoodDataModel> dataCart;
    TextView totalTv;
//   private DisplayFoodItemFragment displayFoodItem;
//    public static double sum;
//    private int quentity;
//    public double totalPrice;
//    private double price;
//    public static int totalquentity;
//    public static String deliverycost;

    public BasketCartFoodAdapter(Context context, Activity activity, List<ItemFoodDataModel> data,TextView totalTv) {
        this.context = context;
        this.activity = activity;
        this.dataCart = data;
        this.totalTv = totalTv;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_food_cart, viewGroup, false);
//        BasketCartFoodAdapter.ViewHolder viewHolder = new BasketCartFoodAdapter.ViewHolder(view);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        Glide.with(context).load(dataCart.get(position).getPhoto_Url()).into(viewHolder.itemFoodImg);
        viewHolder.RoomName.setText(dataCart.get(position).getName());
        viewHolder.RoomTvPrice.setText(dataCart.get(position).getPrice());
        viewHolder.RoomQunitityNum.setText(dataCart.get(position).getQuantity());
        final double price =  Double.parseDouble(dataCart.get(position).getPrice());
        int counter = Integer.parseInt(dataCart.get(position).getQuantity());
        String total = String.valueOf(price * counter);
        viewHolder.RoomTotel.setText(""+total);
        totalAll = Float.parseFloat(total)+ totalAll;
        totalTv.setText(""+totalAll);
//        viewHolder.RoomTotel.setText(calculationTotalPrice(dataCart.get(position).getQuantity(),dataCart.get(position).getPrice()));
        viewHolder.RoomBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(dataCart.get(position).getQuantity());
                dataCart.get(position).setQuantity(String.valueOf(++count));
                final int finalCount = count;
                final RoomItemDao roomItemDao = RoomManger.getInstance(context).roomDao();
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        roomItemDao.update(dataCart.get(position));

                    }
                });

                viewHolder.RoomQunitityNum.setText(dataCart.get(position).getQuantity());
                String totalPlus = String.valueOf(price * finalCount);
                viewHolder.RoomTotel.setText("" + totalPlus );
                totalAll = (price)+ totalAll;
                totalTv.setText(""+totalAll);

//                quentity = quentity +1;
//                viewHolder.RoomQunitityNum.setText(""+quentity);
//                totalPrice = price*quentity;
//                viewHolder.RoomTotel.setText(""+totalPrice);
//                sum = sum +total;

            }
        });

        viewHolder.RoomBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int count = Integer.parseInt(dataCart.get(position).getQuantity());
                dataCart.get(position).setQuantity(String.valueOf(--count));
                final RoomItemDao roomItemDao = RoomManger.getInstance(context).roomDao();
                final int finalCount = count;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        roomItemDao.update(dataCart.get(position));

                    }
                });

                viewHolder.RoomQunitityNum.setText(dataCart.get(position).getQuantity());
                String totalPlus = String.valueOf(price * finalCount);
                viewHolder.RoomTotel.setText("" + totalPlus );
                totalAll = (price)+ totalAll;
                totalTv.setText(""+totalAll);

//                if (quentity<=1){
//                    viewHolder.RoomQunitityNum.setText(""+quentity);
//                }else if (quentity>=1){
//                    quentity = quentity-1;
//                    viewHolder.RoomQunitityNum.setText(""+quentity);
//                    totalPrice=price*quentity;
//                    viewHolder.RoomTotel.setText("" +totalPrice);
//                }
            }
        });
        viewHolder.RoomDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCart.remove(position);
                final RoomItemDao roomItemDao = RoomManger.getInstance(context).roomDao();
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        roomItemDao.deleteItem(dataCart.get(position));
                        roomItemDao.notifyAll();

                    }
                });


            }
        });

    }


//    private String calculationTotalPrice(String orderQuantity, String orderPrice) {
//         quentity = Integer.valueOf(orderQuantity);
//        price = Double.valueOf(orderPrice);
//         totalPrice = price * quentity;
//
//        return String.valueOf(totalPrice);
//    }


    @Override
    public int getItemCount() {
        return dataCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_food_img)
        ImageView itemFoodImg;
        @BindView(R.id.Room_name)
        TextView RoomName;
        @BindView(R.id.Room_qunitity)
        TextView RoomQunitity;
        @BindView(R.id.Room_btn_plus)
        Button RoomBtnPlus;
        @BindView(R.id.Room_qunitity_num)
        TextView RoomQunitityNum;
        @BindView(R.id.Room_btn_minus)
        Button RoomBtnMinus;
        @BindView(R.id.Room_Layout)
        RelativeLayout RoomLayout;
        @BindView(R.id.Room_tv_price)
        TextView RoomTvPrice;
        @BindView(R.id.Room_totel)
        TextView RoomTotel;
        @BindView(R.id.Room_delete)
        TextView RoomDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
