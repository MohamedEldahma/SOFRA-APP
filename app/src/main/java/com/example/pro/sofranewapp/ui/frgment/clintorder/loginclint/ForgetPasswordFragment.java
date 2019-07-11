package com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.pro.sofranewapp.data.model.clint.resetpasswordclient.ResetPasswordClient;
import com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint.ChangPasswordFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordFragment extends Fragment {


    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.Forget_edit_email)
    EditText ForgetEditEmail;
    @BindView(R.id.ptt_send)
    Button pttSend;
    Unbinder unbinder;
    ApiSofraModel apiSofraModel;

    public ForgetPasswordFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel= RerofitSofraClint.getClient().create(ApiSofraModel.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ptt_send)
    public void onViewClicked() {
        String u_resetEmail=ForgetEditEmail.getText().toString();



        apiSofraModel.addResetPassword(u_resetEmail)
                     .enqueue(new Callback<ResetPasswordClient>() {
                         @Override
                         public void onResponse(Call<ResetPasswordClient> call, Response<ResetPasswordClient> response) {
                             if (response.body() != null){

                                 response.body().getData();

                                 Toast.makeText(getActivity(),"Check Your Email",Toast.LENGTH_SHORT).show();
                                 Fragment changPassword = new ChangPasswordFragment();
                                 selectFragment(changPassword);

                             }
                         }

                         @Override
                         public void onFailure(Call<ResetPasswordClient> call, Throwable t) {

                         }
                     });


    }
    public void selectFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.id_fram_Home_nvigation1,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
}
