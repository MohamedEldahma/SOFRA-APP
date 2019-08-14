package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.BasketCartFoodAdapter;
import com.example.pro.sofranewapp.data.local.dataroom.ItemFoodDataModel;
import com.example.pro.sofranewapp.data.local.dataroom.RoomItemDao;
import com.example.pro.sofranewapp.data.local.dataroom.RoomManger;
import com.example.pro.sofranewapp.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasketOrderFragment extends Fragment {


    //    @BindView(R.id.basket_recycler_view)
//    RecyclerView basketRecyclerView;
    RecyclerView basketRecyclerView;
    @BindView(R.id.my_total)
    TextView myTotal;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.texts)
    LinearLayout texts;
    @BindView(R.id.excut_order_and_sgin_in)
    Button excutOrderAndSginIn;
    @BindView(R.id.addmore_btn)
    Button addmoreBtn;
    Unbinder unbinder;
    List<ItemFoodDataModel> itemFoodDataModels = new ArrayList<>();
    BasketCartFoodAdapter cartFoodAdapter;
    TextView sumText;
    View view;

    private double tsum, total;


    public BasketOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basket_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        sumText = view.findViewById(R.id.sum);
        getMyOrder();

//        for (int i = 0 ; i<= itemFoodDataModels.size() ; i++){
//            total = cartFoodAdapter.totalPrice;
//          tsum =tsum + total ;
//        }
//
//
//       sumText.setText("" + tsum);
//        BasketCartFoodAdapter adapterRoomCart = new BasketCartFoodAdapter(getContext(), itemFoodDataModels,myTotal);
//        basketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        basketRecyclerView.setAdapter(adapterRoomCart);
//        RoomManger db = Room.databaseBuilder(getContext(), RoomManger.class, "db").allowMainThreadQueries()
//                .build();
//        List<ItemFoodDataModel> allData = db.roomDao().getAllData();
//
//        itemFoodDataModels.addAll(allData);
////       float totalAll =  AdapterRoomCart.totalAll;
////       OrderCartTVTotal.setText(""+totalAll);
//
//        adapterRoomCart.notifyDataSetChanged();

        return view;
    }

    public void getMyOrder() {
        basketRecyclerView = view.findViewById(R.id.basket_recycler_view);
        basketRecyclerView.setHasFixedSize(true);
        basketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RoomManger roomManger = RoomManger.getInstance(getActivity());
        final RoomItemDao roomItemDao = roomManger.roomDao();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                itemFoodDataModels = roomItemDao.getAllData();


                cartFoodAdapter = new BasketCartFoodAdapter(getContext(), getActivity(), itemFoodDataModels,sumText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        basketRecyclerView.setAdapter(cartFoodAdapter);
                    }
                });

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.excut_order_and_sgin_in, R.id.addmore_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.excut_order_and_sgin_in:
                HelperMethod.replaceFrag(new CompleetOrderClint(),getFragmentManager(),R.id.id_fram_Home_nvigation1);
                break;
            case R.id.addmore_btn:
                HelperMethod.replaceFrag(new InformationResturantViewPAgerFragment(),getFragmentManager(),R.id.id_fram_Home_nvigation1);
                break;
        }
    }
}
