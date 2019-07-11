package com.example.pro.sofranewapp.ui.frgment.resturant.item;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.MyItemAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.myitem.MyItem;
import com.example.pro.sofranewapp.data.model.resturant.myitem.MyItemDatum;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.ui.frgment.resturant.item.AddItemFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyItemFragment extends Fragment {


    @BindView(R.id.rcycler_new_item)
    RecyclerView rcyclerNewItem;
    Unbinder unbinder;
    private String api_tokenResturant;
    ApiSofraModel apiSofraModel;
    MyItemAdapter myItemAdapter;
    @BindView(R.id.bttadd_newitem)
    Button bttaddNewitem;
    private List<MyItemDatum> myItemsDatumList = new ArrayList<>();

    public MyItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        api_tokenResturant = "7jiWQQbN9afm8LTiO4VrmMObYz2lFig117PPCa1vxcK6VsXWy0pGWeq8MA4j";
        getMyFoodItem();
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getMyFoodItem() {

        apiSofraModel.getMyItemFood(api_tokenResturant, 1)

                .enqueue(new Callback<MyItem>() {
                    @Override
                    public void onResponse(Call<MyItem> call, Response<MyItem> response) {
//                        Integer status = response.body().getStatus();
//                        String msg = response.body().getMsg();

                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getActivity(), "Tru", Toast.LENGTH_SHORT).show();
                            rcyclerNewItem.setHasFixedSize(true);
                            rcyclerNewItem.setLayoutManager(new LinearLayoutManager(getContext()));
                            myItemAdapter = new MyItemAdapter(myItemsDatumList, getContext(), getActivity());
                            rcyclerNewItem.setAdapter(myItemAdapter);
                            List<MyItemDatum> data = response.body().getData().getData();
                            myItemsDatumList.addAll(data);
                            myItemAdapter.notifyDataSetChanged();


                        } else {
                            Toast.makeText(getContext(), "NotShow", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<MyItem> call, Throwable t) {

                        Toast.makeText(getActivity(), "Erorr", Toast.LENGTH_SHORT).show();

                    }
                });


    }


    @OnClick(R.id.bttadd_newitem)
    public void onViewClicked() {
        AddItemFragment addItemFragment = new AddItemFragment();
        HelperMethod.replaceFrag(addItemFragment, getFragmentManager(), R.id.id_fram_Home_nvigation1);
    }
}
