package com.example.pro.sofranewapp.ui.frgment.resturant.loginResturant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.restaurantlogin.RestaurantLogin;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.resturant.HomeSelFragment;

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
public class LoginResturantFragment extends Fragment {


    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.id_edit_email)
    EditText idEditEmail;
    @BindView(R.id.id_edit_Password)
    EditText idEditPassword;
    @BindView(R.id.ptt_login)
    Button pttLogin;
    @BindView(R.id.ptt_text_forget_password)
    TextView pttTextForgetPassword;
    @BindView(R.id.ptt_new_acount)
    Button pttNewAcount;
    Unbinder unbinder;
  ApiSofraModel apiSofraModel;
    public LoginResturantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick(R.id.ptt_new_acount)
    public void onViewClicked() {
        Fragment resturantRegister1 = new ResturantRegisterFirstkFragment();
        HelperMethod.replaceFrag(resturantRegister1, getFragmentManager(), R.id.id_fram_Home_nvigation1);


    }





    @OnClick({R.id.ptt_login, R.id.ptt_text_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ptt_login:
                String res_email = idEditEmail.getText().toString();
                String res_password = idEditPassword.getText().toString();
                apiSofraModel.addLoginResturant(res_email,res_password)
                            .enqueue(new Callback<RestaurantLogin>() {
                                @Override
                                public void onResponse(Call<RestaurantLogin> call, Response<RestaurantLogin> response) {
                                    long status = response.body().getStatus();
                                    if (status == 1){
                                        Toast.makeText(getActivity(), "Login Tru", Toast.LENGTH_SHORT).show();
                                        String api_resturant_token = response.body().getData().getApiToken();
                                        int  id_resturant = response.body().getData().getUser().getId();
                                        SharedPrefrancClass.SaveData(getActivity()," id_resturant",id_resturant);
                                        SharedPrefrancClass.SaveData(getActivity(),"api_resturant_token ",api_resturant_token);



                                         HomeSelFragment homeSelFragment = new HomeSelFragment();
                                         HelperMethod.replaceFrag(homeSelFragment,getFragmentManager(),R.id.id_fram_Home_nvigation1);
                                    }else {
                                        Toast.makeText(getActivity(), "Not Login", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<RestaurantLogin> call, Throwable t) {
                                    Toast.makeText(getActivity(), "Login Erorr", Toast.LENGTH_SHORT).show();
                                }
                            });

                break;
            case R.id.ptt_text_forget_password:
                break;
        }
    }
}
