package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.ListResturantItemAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.listrestitems.ListRestItemsDatum;
import com.example.pro.sofranewapp.data.model.clint.listrestitems.ListResturantItems;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFoodFragment extends Fragment {


    @BindView(R.id.minu_food_recicleview)
    RecyclerView minuFoodRecicleview;
    Unbinder unbinder;
    ApiSofraModel apiSofraModel;
    ListResturantItemAdapter listResturantItemAdapter;
    private int id_resturant;
    private List<ListRestItemsDatum> listRestItemsDatumList;

    public MenuFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_food, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        id_resturant = SharedPrefrancClass.LoadIntegerData(getActivity(),"id_Resturant");
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        minuFoodRecicleview.setLayoutManager(manager);
       listRestItemsDatumList = new ArrayList<>();
        listMenuFood();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void listMenuFood(){
        apiSofraModel.getResturantItem(id_resturant,1)
                    .enqueue(new Callback<ListResturantItems>() {
                        @Override
                        public void onResponse(Call<ListResturantItems> call, Response<ListResturantItems> response) {
                            Log.d(TAG,"row"+response.raw());
                            try {
                            if (response.body().getStatus() == 1){
//                                minuFoodRecicleview.setHasFixedSize(true);
//                                minuFoodRecicleview.setLayoutManager(new LinearLayoutManager(getContext()));
                            listResturantItemAdapter =new ListResturantItemAdapter(listRestItemsDatumList,getActivity(),getContext());
                             minuFoodRecicleview.setAdapter(listResturantItemAdapter);
                             List<ListRestItemsDatum> data = response.body().getData().getData();
                             listRestItemsDatumList.addAll(data);
                             listResturantItemAdapter.notifyDataSetChanged();


                            }else {
                                Log.d(TAG, "onResponse: " + response.body().getMsg());
                                Toast.makeText(getActivity(), "ListMinewFoodEroor", Toast.LENGTH_SHORT).show();

                            }}catch (NullPointerException igsnored){}

                        }

                        @Override
                        public void onFailure(Call<ListResturantItems> call, Throwable t) {

                        }
                    });



    }
}
