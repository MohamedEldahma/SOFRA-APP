package com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.clientnewpassword.NewPasswordClient;
import com.example.pro.sofranewapp.ui.frgment.clintorder.HomeOrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangPasswordFragment extends Fragment {


    @BindView(R.id.cod_input)
    EditText codInput;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.new_confirm_password)
    EditText newConfirmPassword;
    @BindView(R.id.ptt_chang_password)
    Button pttChangPassword;
    ApiSofraModel apiSofraModel;
    Unbinder unbinder;

    public ChangPasswordFragment() {


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chang_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ptt_chang_password)
    public void onViewClicked() {

        String u_codInput = codInput.getText().toString();
        String u_newPassword = newPassword.getText().toString();
        String u_confirmPassword = newConfirmPassword.getText().toString();

        apiSofraModel.addNewPassword(u_codInput,u_newPassword,u_confirmPassword).enqueue(new Callback<NewPasswordClient>() {
            @Override
            public void onResponse(Call<NewPasswordClient> call, Response<NewPasswordClient> response) {
                if (response.body() !=null){

                    Toast.makeText(getActivity(),"Password chang tru",Toast.LENGTH_SHORT).show();



                    Fragment homeUserFragment = new HomeOrderFragment();
                    selectFragment(homeUserFragment);
                }else {Toast.makeText(getActivity(),"Password chang False",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<NewPasswordClient> call, Throwable t) {

                Toast.makeText(getActivity(),"Password chang Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void selectFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.id_fram_Home_nvigation1,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
} 
