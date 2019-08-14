package com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.registerClient.RegisterClient;
import com.example.pro.sofranewapp.data.model.general.cities.Cities;

import com.example.pro.sofranewapp.data.model.general.cities.CitiesDatum;
import com.example.pro.sofranewapp.data.model.general.regions.Regions;
import com.example.pro.sofranewapp.data.model.general.regions.RegionsDatum;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint.LoginOrderFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pro.sofranewapp.helper.ValidationHelper.error;
import static com.example.pro.sofranewapp.helper.ValidationHelper.verbose;


public class UserRegisterOrderFragment extends Fragment {
    ApiSofraModel apiSofraModel;
    @BindView(R.id.name_user)
    EditText nameUser;
    @BindView(R.id.email_user)
    EditText emailUser;
    @BindView(R.id.phone_number_user)
    EditText phoneNumberUser;
    @BindView(R.id.password_user)
    EditText passwordUser;
    @BindView(R.id.confirm_password_user)
    EditText confirmPasswordUser;
    @BindView(R.id.register_user)
    Button registerUser;
    Unbinder unbinder;
    @BindView(R.id.user_spiner_city)
    Spinner userSpinerCity;
    @BindView(R.id.user_spiner_regions)
    Spinner userSpinerRegions;
    @BindView(R.id.descrip_adress)
    EditText descripAdress;
    private String u_name, u_email, u_phone, u_adress, u_password, u_confirmPassword;
    private int startCityId ;
    String api_token;

    public UserRegisterOrderFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_register_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        getCities();

        return view;
    }


    public void getCities() {

       apiSofraModel.getCities().enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                List<CitiesDatum> citiesDatumList =response.body().getData().getData() ;
                ArrayList<String> city= new ArrayList<>();
                final ArrayList<Integer> idcity = new ArrayList<>();
                city.add(getString(R.string.string_city));
                idcity.add(0);

                for (int i = 0; i < citiesDatumList.size(); i++) {

                    String cityName = citiesDatumList.get(i).getName();
                    Integer cityId = citiesDatumList.get(i).getId();
                    city.add(cityName);
                    idcity.add(cityId);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, city);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                userSpinerCity.setAdapter(adapter);

                userSpinerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            getDataFromRegion((idcity.get(position)));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }


            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                error("GavernoratesResponse Onfailure: " + t.getMessage());



            }

        });
    }

    private void getDataFromRegion(int cityId) {
        apiSofraModel.getRegions(cityId)
                .enqueue(new Callback<Regions>() {
                    @Override
                    public void onResponse(Call<Regions> call, Response<Regions> response) {
                        List<RegionsDatum> regionsDatumList = response.body().getData().getData();
                        ArrayList<String> region = new ArrayList<>();
                        final ArrayList<Integer> idRegion = new ArrayList<>();

                        region.add(getString(R.string.string_adress));
                        idRegion.add(0);

                        for (int i = 0; i < regionsDatumList.size(); i++) {
                            String regionName = regionsDatumList.get(i).getName();
                            Integer regionId = regionsDatumList.get(i).getId();

                            region.add(regionName);
                            idRegion.add(regionId);
                        }

                        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_spinner_item, region);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        userSpinerRegions.setAdapter(adapter);

                        userSpinerRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    verbose("onCityItemSelected: " + idRegion.get(position));
                                    startCityId = idRegion.get(position);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Regions> call, Throwable t) {
                        error("CitiesResponse Onfailure: " + t.getMessage());

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    public void shardPrefRegister() {
//        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("user", MODE_PRIVATE).edit();
//        editor.putBoolean("login", true);
//        editor.putString("user", u_name);
//        editor.putString("email", u_email);
//        editor.putString("phone", u_phone);
//        editor.putString("description", u_adress);
//        editor.putInt   ("startCityId",startCityId);
//        editor.putString("password", u_password);
////         editor.putString("api_token",api_token);
//        editor.apply();
//
//
//    }


    @OnClick(R.id.register_user)
    public void onViewClicked() {

        u_name = nameUser.getText().toString();
        u_email = emailUser.getText().toString();
        u_phone = phoneNumberUser.getText().toString();
        u_adress = descripAdress.getText().toString();
        u_password = passwordUser.getText().toString();
        u_confirmPassword = confirmPasswordUser.getText().toString();

        apiSofraModel.userAddRegister(u_name, u_email, u_phone, u_adress, u_password, u_confirmPassword, startCityId)
                .enqueue(new Callback<RegisterClient>() {
                    @Override
                    public void onResponse(Call<RegisterClient> call, Response<RegisterClient> response) {
                        if (response.body().getStatus() ==1) {
                            Toast.makeText(getActivity(), "Register is input", Toast.LENGTH_SHORT).show();
                            api_token = response.body().getData().getApiToken();
                            SharedPrefrancClass.SaveData(getActivity(),"api_token ",api_token);

                            Fragment loginFragment = new LoginOrderFragment();
                            selectFragment(loginFragment);
                        } else  {
                            Toast.makeText(getActivity(), "Register is Not Input", Toast.LENGTH_SHORT).show();

                        }


                    }


                    @Override
                    public void onFailure(Call<RegisterClient> call, Throwable t) {
                        Toast.makeText(getActivity(), "register is error", Toast.LENGTH_SHORT);
                    }
                });


    }


    public void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.id_fram_Home_nvigation1, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
}
