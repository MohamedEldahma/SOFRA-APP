package com.example.pro.sofranewapp.ui.frgment.general;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.ListResturantItemAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.local.dataroom.ItemFoodDataModel;
import com.example.pro.sofranewapp.data.local.dataroom.RoomItemDao;
import com.example.pro.sofranewapp.data.local.dataroom.RoomManger;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.clintorder.BasketOrderFragment;

import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.ContentValues.TAG;
import static com.example.pro.sofranewapp.data.api.RerofitSofraClint.getClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFoodItemFragment extends Fragment {


    @BindView(R.id.img_item)
    ImageView imgItem;
    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.item_desc)
    TextView itemDesc;
    @BindView(R.id.item_price)
    TextView itemPrice;
    @BindView(R.id.display_item_wait)
    TextView displayItemWait;
    @BindView(R.id.edit_text_special_order)
    TextView editTextSpecialOrder;
    @BindView(R.id.item_special_order)
    EditText itemSpecialOrder;
    @BindView(R.id.item_btn_mins)
    Button itemBtnMins;
    @BindView(R.id.item_display_count)
    TextView itemDisplayCount;
    @BindView(R.id.item_btn_plus)
    Button itemBtnPlus;
    @BindView(R.id.btn_addTo_cart)
    Button btnAddToCart;
    Unbinder unbinder;
    public String getitem_photo;
    public String getitem_desc;
    public String getitem_wait;
    public String getitem_name;
    public String getitem_price;
    public int idItem;
    public static String id_Resturant;
    private ListResturantItemAdapter listResturantItemAdapter;
    private ItemFoodDataModel itemFoodDataModel;
    ApiSofraModel apiSofraModel;
    String api_tokent;
    int quantity = 1;


    public DisplayFoodItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_food_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData(getitem_photo, getitem_name, getitem_desc, getitem_wait, getitem_price);
        apiSofraModel = getClient().create(ApiSofraModel.class);
//        api_tokent="HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB";
        api_tokent = SharedPrefrancClass.LoadStringData(getActivity(), "api_token_clint");
        itemDisplayCount.setText(String.valueOf(quantity));


//        if (ListResturantItemAdapter.itemFoodDataRoom !=null){
//
//            Glide.with( getContext() ).load( ListResturantItemAdapter.itemFoodDataRoom.getPhotoUrl() )
//                    .into( imgItem );
//             itemDesc.setText( ListResturantItemAdapter.itemFoodDataRoom.getDescription() );
//            itemPrice.setText( ListResturantItemAdapter.itemFoodDataRoom.getPrice() + " EGP " );
//            displayItemWait.setText( getActivity().getResources().getString( R.string.time_item) + " " + ListResturantItemAdapter.itemFoodDataRoom.getPreparingTime() );
//            itemName.setText( ListResturantItemAdapter.itemFoodDataRoom.getName() );
//        }
//
        return view;
    }

    private void getData(String getitem_photo, String getitem_name, String getitem_desc, String getitem_wait, String getitem_price) {
        Glide.with(getContext()).load(getitem_photo).into(imgItem);
        itemName.setText(getitem_name);
        itemDesc.setText(getitem_desc);
        displayItemWait.setText("وقت التحضير: " + getitem_wait);
        itemPrice.setText(" السعر\n" + getitem_price);
    }


    public void addItemeCart() {


        itemFoodDataModel = new ItemFoodDataModel(itemSpecialOrder.getText().toString(), getitem_photo,
                itemDisplayCount.getText().toString(), getitem_price,
                itemName.getText().toString(), id_Resturant, idItem);

//                        List<ItemFoodDataModel> addAllData = roomItemDao.getAllData();
//                         for (int i =0 ;i<addAllData.size();i++){
        String resturantId = itemFoodDataModel.getRestaurantId();
        if (resturantId.trim().equals(id_Resturant.trim())) {


            final RoomItemDao roomItemDao = RoomManger.getInstance(getContext()).roomDao();


            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {


                    roomItemDao.insertAll(itemFoodDataModel);
                    HelperMethod.replaceFrag(new BasketOrderFragment(), ((FragmentActivity) getContext())
                            .getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);


                }
            });

        } else {


            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("انت تطلب من مطعم اخر ")
                    .setContentText("هل تريد ان تحزف الطلبات الاخري ")
                    .setConfirmText("نعم احزف ")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog
                                    .setTitleText("Deleted!")
                                    .setContentText("Your item  has been deleted!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            final RoomItemDao roomItemDao = RoomManger.getInstance(getContext()).roomDao();
                            roomItemDao.deletAll();
                            roomItemDao.insertAll(itemFoodDataModel);
                            HelperMethod.replaceFrag(new BasketOrderFragment(), ((FragmentActivity) getContext())
                                    .getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);

                        }
                    })
                    .show();

        }


//        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


//    public void savDataInCart() {
//
//
//
//
//        final RoomItemDao roomItemDao = RoomManger.getInstance(getContext()).roomDao();
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//
//
//
//                roomItemDao.insertAll(new ItemFoodDataModel(
//                        ListResturantItemAdapter.itemFoodDataRoom.getDescription(),
//                        ListResturantItemAdapter.itemFoodDataRoom.getPhotoUrl(),
//                        itemDisplayCount.getText().toString(),
//                        ListResturantItemAdapter.itemFoodDataRoom.getPrice(),
//                        ListResturantItemAdapter.itemFoodDataRoom.getName(),
//                        ListResturantItemAdapter.itemFoodDataRoom.getRestaurantId()
//                        ));
//
////                itemFoodDataModel = new ItemFoodDataModel(itemSpecialOrder.getText().toString(),getitem_photo,
////                        itemDisplayCount.getText().toString(), getitem_price,getitem_name,id_Resturant);
//
//                List<ItemFoodDataModel> itemsOrders =roomItemDao.getAllData();
//                for (int i = 0; i < itemsOrders.size(); i++) {
////                    String restaurantId = itemsOrders.get(i).getRestaurantId();
////                    if (restaurantId.trim().equals(id_Resturant.trim())) {
////                                roomItemDao.insertAll(itemFoodDataModel);
//                    Log.i(TAG, "savInCart: " + itemsOrders.get(i).getName());
//                    HelperMethod.replaceFrag(new BasketOrderFragment(),((FragmentActivity)getContext())
//                            .getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);
//
//                    }
//
//
//
//            }
//        });
//    }


    public void countQuantity(int quantirty) {
        itemDisplayCount.setText(quantirty);
    }

    @OnClick({R.id.item_btn_mins, R.id.item_btn_plus, R.id.btn_addTo_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_btn_mins:

                if (quantity == 1) {
                    Toast.makeText(getContext(), "Not Valid", Toast.LENGTH_SHORT).show();
                } else {
                    itemDisplayCount.setText(String.valueOf(--quantity));
                }
//                if (quantity ==0){
//                    return;
//
//                }
//                quantity = quantity -1;
//                countQuantity(quantity);

                break;
            case R.id.item_btn_plus:
                itemDisplayCount.setText(String.valueOf(++quantity));

//                if (quantity ==50){
//                    return;
//
//                }
//                quantity = quantity +1;
//
//                countQuantity(quantity);
                break;
            case R.id.btn_addTo_cart:
//                savDataInCart();
                addItemeCart();


                break;
        }
    }
}
