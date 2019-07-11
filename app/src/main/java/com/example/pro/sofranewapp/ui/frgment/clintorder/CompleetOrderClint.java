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
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.local.dataroom.ItemFoodDataModel;
import com.example.pro.sofranewapp.data.model.clint.compleetneworderclint.CompleetNeworderClint;
import com.example.pro.sofranewapp.data.model.general.paymentmethods.PayMentDatum;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleetOrderClint extends Fragment {


    Unbinder unbinder;
    ApiSofraModel apiSofraModel;
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
    @BindView(R.id.copleet_order_prepair_time)
    TextView copleetOrderPrepairTime;
    @BindView(R.id.layout_tow)
    LinearLayout layoutTow;
    @BindView(R.id.complet_order_allTotal)
    TextView completOrderAllTotal;
    @BindView(R.id.layout_three)
    LinearLayout layoutThree;
    @BindView(R.id.Complete_Order_btn_pay)
    Button CompleteOrderBtnPay;
    private String api_token_clint;

    private int payment_id;
    public String name_clint ;
    private String phone_clint;
    public String address_clint;
    private List<Integer> itemId = new ArrayList<>();
    private List<Integer> listOFQuantities = new ArrayList<>();
    private List<String> listOfNotes = new ArrayList<>();
    boolean chicked = false;
    List<PayMentDatum> methoPaymentDtum = new ArrayList<>();
    String restaurantId;



    public CompleetOrderClint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compleet_order_clint, container, false);
        unbinder = ButterKnife.bind(this, view);
//        getPayment();
        api_token_clint = SharedPrefrancClass.LoadStringData(getActivity(), "api_token_clint");
        name_clint = SharedPrefrancClass.LoadStringData(getActivity(), "name_clint");
        phone_clint = SharedPrefrancClass.LoadStringData(getActivity(), "phone_clint");
        address_clint = SharedPrefrancClass.LoadStringData(getActivity(), "address_clint");
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);

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


    @OnClick(R.id.Complete_Order_btn_pay)
    public void onViewClicked() {orderCompleet();}



    public void orderCompleet(){
//        if (casheOnDelevary.isChecked()) {
//            payment_id = 1 ;}
//        if (casheOnDelevary.isChecked()) {
//            payment_id = 2 ;}


        ItemFoodDataModel modelCompleet = new ItemFoodDataModel();
        for (int i = 0; i < itemId.size(); i++) {
            itemId.add(modelCompleet.getId());
            listOfNotes.add(modelCompleet.getNotes());
            listOFQuantities.add(Integer.parseInt(modelCompleet.getQuantity()));
        }


        if (chicked == false) {

            SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
            dialog.setTitleText("you have check on way to pay");
            dialog.show();
        }

         restaurantId = modelCompleet.getRestaurantId();
        apiSofraModel.compleetOrderClint(restaurantId, textAddNot.getText().toString(), addAdressDiliver.getText().toString(),
                payment_id, phone_clint, name_clint,
                api_token_clint, itemId, listOFQuantities, listOfNotes).enqueue(new Callback<CompleetNeworderClint>() {
            @Override
            public void onResponse(Call<CompleetNeworderClint> call, Response<CompleetNeworderClint> response) {
                String msg = response.body().getMsg();
                if (response.body().getStatus() == 1) {


                    HelperMethod.replaceFrag(new BasketOrderFragment(),getFragmentManager(),R.id.id_fram_Home_nvigation1);

                    SweetAlertDialog dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                    dialog.setTitleText(msg);
                    dialog.show();
                }else {

                    SweetAlertDialog dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText(msg);
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<CompleetNeworderClint> call, Throwable t) {
                Toast.makeText(getContext(), " Order Find Erorrr", Toast.LENGTH_SHORT).show();
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
