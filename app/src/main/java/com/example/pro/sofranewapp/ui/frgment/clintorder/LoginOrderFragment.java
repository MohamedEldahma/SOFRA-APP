package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.pro.sofranewapp.data.ApiSofraModel;
import com.example.pro.sofranewapp.data.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.loginclient.LoginClient;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginOrderFragment extends Fragment {


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
    ApiSofraModel apiSofraModel;
    SharedPreferences sharedPreferences;
    private String api_token;
    Unbinder unbinder;

    public LoginOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel= RerofitSofraClint.getClient().create(ApiSofraModel.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

public void shardLogin() {
    SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
    editor.putBoolean("login",true);
    editor.putString("api_token",api_token);
    editor.apply();

}
    public void loginClint(){

        String l_email=idEditEmail.getText().toString();
        String l_password=idEditPassword.getText().toString();
        apiSofraModel.userAddLogin(l_email,l_password)
                .enqueue(new retrofit2.Callback<LoginClient>() {
                    @Override
                    public void onResponse(Call<LoginClient> call, Response<LoginClient> response) {
                        if (response.body().getStatus() ==1){
                            Toast.makeText(getActivity(), "User Login Tru", Toast.LENGTH_SHORT).show();
                            shardLogin();
                            Fragment homOrder=new HomeOrderFragment();
                            selectFragment(homOrder);
                         
                        }else {
                            Toast.makeText(getActivity(),"User Not Login",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginClient> call, Throwable t) {
                        Toast.makeText(getActivity(),"User Login Error",Toast.LENGTH_SHORT).show();

                    }
                });



    }

    @OnClick({R.id.ptt_login, R.id.ptt_text_forget_password, R.id.ptt_new_acount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ptt_login:loginClint();
                break;
            case R.id.ptt_text_forget_password:
                Fragment forgetPasword = new ForgetPasswordFragment();
                selectFragment(forgetPasword);
                break;
            case R.id.ptt_new_acount:
              Fragment register = new UserRegisterOrderFragment();
              selectFragment(register);
                break;
        }
    }


    public void selectFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.id_fram_Home_nvigation1,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
}
