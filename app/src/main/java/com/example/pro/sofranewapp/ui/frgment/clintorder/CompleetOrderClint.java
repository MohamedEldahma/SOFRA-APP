package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.BasketCartFoodAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.local.dataroom.ItemFoodDataModel;
import com.example.pro.sofranewapp.data.local.dataroom.RoomItemDao;
import com.example.pro.sofranewapp.data.local.dataroom.RoomManger;
import com.example.pro.sofranewapp.data.model.clint.completcrderclint.CompletOrderClint;
import com.example.pro.sofranewapp.data.model.general.paymentmethods.PayMentDatum;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.clintorder.notificationclint.NotifcationOrderClint;
import com.example.pro.sofranewapp.ui.frgment.general.DisplayFoodItemFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pro.sofranewapp.adapter.BasketCartFoodAdapter.totalAll;
import static com.example.pro.sofranewapp.ui.frgment.general.DisplayFoodItemFragment.id_Resturant;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleetOrderClint extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.text_not)
    TextView textNot;
    @BindView(R.id.text_add_not)
    EditText textAddNot;
    @BindView(R.id.adress_diliver)
    TextView adressDiliver;
    @BindView(R.id.add_adress_diliver)
    EditText addAdressDiliver;
    @BindView(R.id.text_bayment)
    TextView textBayment;
    @BindView(R.id.cashe_on_delevary)
    RadioButton casheOnDelevary;
    @BindView(R.id.internt_on_delevary)
    RadioButton interntOnDelevary;
    @BindView(R.id.textBayment_layout)
    LinearLayout textBaymentLayout;
    @BindView(R.id.copleet_order_total)
    TextView copleetOrderTotal;
    @BindView(R.id.layout_one)
    LinearLayout layoutOne;
    @BindView(R.id.copleet_order_DelivaryCost)
    TextView copleetOrderDelivaryCost;
    @BindView(R.id.layout_tow)
    LinearLayout layoutTow;
    @BindView(R.id.complet_order_allTotal)
    TextView completOrderAllTotal;
    @BindView(R.id.layout_three)
    LinearLayout layoutThree;
    @BindView(R.id.Complete_Order_btn_pay)
    Button CompleteOrderBtnPay;
    ApiSofraModel apiSofraModel;

    private String api_token_clint;
    private int payment_id;
    public String name_clint;
    private String phone_clint;
    public String address_clint;
    private List<Integer> itemId = new ArrayList<>();
    private List<String> itemNotes = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();
    //    String itemNotes ;
    boolean chicked = false;
    int restaurantId;
    String delivaryCost;
    String nots;


    public CompleetOrderClint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compleet_order_clint, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        addNewOrder();
//        api_token_clint = SharedPrefrancClass.LoadStringData(getActivity(), "api_token_clint");
        api_token_clint = "K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh";
        name_clint = SharedPrefrancClass.LoadStringData(getActivity(), "name_clint");
        phone_clint = SharedPrefrancClass.LoadStringData(getActivity(), "phone_clint");
        address_clint = SharedPrefrancClass.LoadStringData(getActivity(), "address_clint");
        delivaryCost = SharedPrefrancClass.LoadStringData(getActivity(), "Item_Delivary_Cost");
        restaurantId = SharedPrefrancClass.LoadIntegerData(getActivity(), "id_Resturant");


        casheOnDelevary.setText("نقدا عند الاستلام");
        interntOnDelevary.setText("شبكه عند الاستلام");

        casheOnDelevary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payment_id = 1;
                    chicked = true;
                } else {
                    chicked = false;
                    payment_id = 0;
                }
            }
        });

        interntOnDelevary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    payment_id = 1;
                    chicked = true;
                } else {
                    chicked = false;
                    payment_id = 0;
                }

            }
        });

        return view;

    }

//   public void getPayment() {
//
//        apiSofraModel.getPaymentmethods().enqueue(new Callback<PaymentMethods>() {
//            @Override
//            public void onResponse(Call<PaymentMethods> call, Response<PaymentMethods> response) {
//                if (response.body().getStatus() != null) {
//                    methoPaymentDtum = response.body().getData();
//                    casheOnDelevary.setText(methoPaymentDtum.get(1).getName());
//                    interntOnDelevary.setText(methoPaymentDtum.get(2).getName());
//
//
//                } else {
//                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PaymentMethods> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addNewOrder() {

        final RoomItemDao roomItemDao = RoomManger.getInstance(getContext()).roomDao();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<ItemFoodDataModel> modelList = roomItemDao.getAllData();
                for (ItemFoodDataModel itemFoodData : modelList) {
                    copleetOrderTotal.setText("" + totalAll);
                    copleetOrderDelivaryCost.setText(delivaryCost);
                    double delivaryCost_Compleet = Double.parseDouble(delivaryCost);
                    double total = (totalAll + delivaryCost_Compleet);
                    completOrderAllTotal.setText(total + "");

                    itemId.add(itemFoodData.getItemId());
                    itemNotes.add(itemFoodData.getNotes());
                    quantities.add(Integer.valueOf(itemFoodData.getQuantity()));
                }

            }
        });


    }


    @OnClick(R.id.Complete_Order_btn_pay)

    public void onViewClicked() {
        orderCompleet();
    }


    public void orderCompleet() {

        nots = textAddNot.getText().toString();
        apiSofraModel.compleetOrderClint(restaurantId, nots, addAdressDiliver.getText().toString(), payment_id, phone_clint, name_clint,
                api_token_clint, itemId, quantities, itemNotes).enqueue(new Callback<CompletOrderClint>() {
            @Override
            public void onResponse(Call<CompletOrderClint> call, Response<CompletOrderClint> response) {
                String msg = response.body().getMsg();
                if (response.body().getStatus() == 1) {
                    HelperMethod.replaceFrag(new NotifcationOrderClint(), getFragmentManager(), R.id.id_fram_Home_nvigation1);

                    SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                    dialog.setTitleText(msg);
                    dialog.show();
                } else {
                    Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();

                    SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText(msg);
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<CompletOrderClint> call, Throwable t) {
                Toast.makeText(getContext(), " CompletOrder Find Erorrr", Toast.LENGTH_SHORT).show();
            }
        });

    }


//    @OnClick({R.id.internt_on_delevary, R.id.textBayment_layout})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.internt_on_delevary:
//                break;
//            case R.id.textBayment_layout:
//                break;
//        }
//    }
}
