package com.example.pro.sofranewapp.ui.frgment.clintorder.notificationclint;


//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.GitListNotifcationAdapterClint;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.listnotificationclint.ListNotificationDatum;

import com.example.pro.sofranewapp.data.model.clint.listnotificationclint.ListNotificationClint;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifcationOrderClint extends Fragment {


    @BindView(R.id.notificationCLintRecycler)
    RecyclerView notificationCLintRecycler;
    ApiSofraModel apiSofraModel;
    String api_Token;
    Unbinder unbinder;
    GitListNotifcationAdapterClint gitListNotifcationAdapterClint;
    List<ListNotificationDatum> dataListNotifications = new ArrayList<>();

    public NotifcationOrderClint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifcation_order_clint, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
          api_Token = "K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh";
//        api_Token = SharedPrefrancClass.LoadStringData(getActivity(), "api_token_clint");
        getOrderNotificationClint();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void  getOrderNotificationClint(){
        apiSofraModel.getListNotificationClint(api_Token).enqueue(new Callback<ListNotificationClint>() {
            @Override
            public void onResponse(Call<ListNotificationClint> call, Response<ListNotificationClint> response) {
                List<ListNotificationDatum> data = response.body().getData().getData();
                String msg = response.body().getMsg();
                if (response.body().getStatus() == 1){
                    Toast.makeText(getActivity(), "tru"+msg, Toast.LENGTH_SHORT).show();
                    notificationCLintRecycler.setHasFixedSize(true);
                    notificationCLintRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    gitListNotifcationAdapterClint = new GitListNotifcationAdapterClint(dataListNotifications,getContext(),getActivity());
                    notificationCLintRecycler.setAdapter(gitListNotifcationAdapterClint);
                    gitListNotifcationAdapterClint.notifyDataSetChanged();
                    dataListNotifications.addAll(data);

                }else {
                    Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ListNotificationClint> call, Throwable t) {
                Toast.makeText(getActivity(), "Erorrrrrrrrrr", Toast.LENGTH_SHORT).show();

            }
        });


    }





}
