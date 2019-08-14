package com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.example.pro.sofranewapp.data.model.clint.loginClint.LoginClint;
import com.example.pro.sofranewapp.data.model.clint.loginClint.LoginRegion;
import com.example.pro.sofranewapp.data.model.clint.pushregistertokenclient.PushRegisterTokenClient;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.activity.OrderFoodActivity;
import com.example.pro.sofranewapp.ui.frgment.clintorder.HomeOrderFragment;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;


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
    public String lname ;
    private String phone;
    private String address;
    private String email;
    LoginRegion region;
    private int idCity ;
    private String pass;
    String refreshedToken;

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
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG,"onCreat,"+refreshedToken);
        idEditEmail.setText(email);
        idEditPassword.setText(pass);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//public void shardLogin() {
//    SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
//    editor.putBoolean("login",true);
//    editor.putString("api_token",api_token);
//    editor.apply();
//
//}
    public void loginClint(){

        String l_email=idEditEmail.getText().toString();
        final String l_password=idEditPassword.getText().toString();
        apiSofraModel.userAddLogin(l_email,l_password)
                .enqueue(new retrofit2.Callback<LoginClint>() {
                    @Override
                    public void onResponse(Call<LoginClint> call, Response<LoginClint> response) {

                        if (response.body().getStatus()==1 ){
                            Toast.makeText(getActivity(), "RestaurantUser Login Tru", Toast.LENGTH_SHORT).show();
//                            SharedPrefManagerClient.getInstance(getContext()).setClientApiToken(response.body().getData().getApiToken());
//                            SharedPrefManagerClient.getInstance(getContext()).saveClientLoginCity
//                                    (response.body().getData().getUser().getRegion().getCity().getId(),
//                                            response.body().getData().getUser().getRegion().getId());
                            api_token = response.body().getData().getApiToken();
                            lname = response.body().getData().getClient().getName();
                            phone = response.body().getData().getClient().getPhone();
                            address = response.body().getData().getClient().getAddress();
                            email = response.body().getData().getClient().getEmail();
                            region = response.body().getData().getClient().getRegion();
                            idCity = region.getCity().getId();


                            
//                            // save data to SharedPreference
                            pass = idEditPassword.getText().toString();
                            SharedPrefrancClass.SaveData(getActivity(),"pass_clint",pass);
                            SharedPrefrancClass.SaveData(getActivity(),"api_token_clint",api_token);
                            SharedPrefrancClass.SaveData(getActivity(),"name_clint",lname);
                            SharedPrefrancClass.SaveData(getActivity(),"email_clint",email);
                            SharedPrefrancClass.SaveData(getActivity(),"id_city_clint",idCity);
                            SharedPrefrancClass.SaveData(getActivity(),"phone_clint",phone);
                            SharedPrefrancClass.SaveData(getActivity(),"address_clint",address);

                            Fragment homOrder=new HomeOrderFragment();
                            selectFragment(homOrder);
                         
                        }else {
                            Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginClint> call, Throwable t) {
                        Toast.makeText(getActivity(),"RestaurantUser Login Error",Toast.LENGTH_SHORT).show();

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
