package com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import com.example.pro.sofranewapp.data.model.clint.ecitprofilCliint.EditProfileClint;
import com.example.pro.sofranewapp.data.model.general.cities.Cities;
import com.example.pro.sofranewapp.data.model.general.cities.CitiesDatum;
import com.example.pro.sofranewapp.data.model.general.regions.Regions;
import com.example.pro.sofranewapp.data.model.general.regions.RegionsDatum;
import com.example.pro.sofranewapp.helper.SharedPrefManager;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileClintFragment extends Fragment {


    @BindView(R.id.name_user_profil)
    EditText nameUserProfil;
    @BindView(R.id.email_user_profil)
    EditText emailUserProfil;
    @BindView(R.id.phone_number_user_profil)
    EditText phoneNumberUserProfil;
    @BindView(R.id.user_spiner_city_profil)
    Spinner userSpinerCityProfil;
    @BindView(R.id.user_spiner_regions_profil)
    Spinner userSpinerRegionsProfil;
    @BindView(R.id.descrip_adress_profil)
    EditText descripAdressProfil;
    @BindView(R.id.password_user_profil)
    EditText passwordUserProfil;
    @BindView(R.id.confirm_password_user_profil)
    EditText confirmPasswordUserProfil;
    @BindView(R.id.register_user_profil)
    Button registerUserProfil;
    Unbinder unbinder;
    private String u_name,u_email,u_phone,u_password,u_confirmPasword,u_decription;
    ApiSofraModel apiSofraModel ;
    String api_token;
    private int startCityId;
    int  userId;

    public ProfileClintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_clint, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        userId = getActivity().getIntent().getIntExtra("user_id", 0);
        api_token =SharedPrefrancClass.LoadStringData(getActivity(),"api_token_clint");

        getCities();
        getProfil();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    public void  getProfil (){
        apiSofraModel.getClintProfil(api_token)
                .enqueue(new Callback<EditProfileClint>() {
                    @Override
                    public void onResponse(Call<EditProfileClint> call, Response<EditProfileClint> response) {
                   if (response.body().getStatus() == 1){

                       Toast.makeText(getActivity(), "This Your ReviewsData", Toast.LENGTH_SHORT).show();
//                       shardLogin();

                       nameUserProfil.setText(response.body().getData().getClient().getName());
                       emailUserProfil.setText(response.body().getData().getClient().getEmail());
                       phoneNumberUserProfil.setText(response.body().getData().getClient().getPhone());
                       descripAdressProfil.setText(response.body().getData().getClient().getAddress());
//                       userSpinerCityProfil.setSelection(SharedPrefManagerClient.getInstance(getContext()).getClientLoginCity());
//                       userSpinerRegionsProfil.setSelection(SharedPrefManagerClient.getInstance(getContext()).getClientLoginRegionId());





                   }else {
                       Toast.makeText(getActivity(), "Your ReviewsData Erorr", Toast.LENGTH_SHORT).show();
                   }
                    }

                    @Override
                    public void onFailure(Call<EditProfileClint> call, Throwable t) {
                        Toast.makeText(getActivity(), " Erorrrrrrrrrrrrrrrrr", Toast.LENGTH_SHORT).show();

                    }
                });




    }
    @OnClick(R.id.register_user_profil)
    public void onViewClicked() {
       u_name= nameUserProfil.getText().toString();
       u_email = emailUserProfil.getText().toString();
        u_phone = phoneNumberUserProfil.getText().toString();
        u_decription = descripAdressProfil.getText().toString();
       u_password = passwordUserProfil.getText().toString();
        u_confirmPasword = confirmPasswordUserProfil.getText().toString();
        apiSofraModel.editClintProfil(api_token,u_name,u_phone,u_email,u_password,u_confirmPasword, String.valueOf(startCityId),u_decription)
                   .enqueue(new Callback<EditProfileClint>() {
                       @Override
                       public void onResponse(Call<EditProfileClint> call, Response<EditProfileClint> response) {
                           if (response.body().getStatus() == 1){
                               Toast.makeText(getActivity(), "Profile ReviewsData Chang ", Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(getActivity(), "Profile ReviewsData Not Chang ", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<EditProfileClint> call, Throwable t) {
                           Toast.makeText(getActivity(), "Profile ReviewsData Erorrrrrrrrr ", Toast.LENGTH_SHORT).show();

                       }
                   });


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
                userSpinerCityProfil.setAdapter(adapter);

                userSpinerCityProfil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        userSpinerRegionsProfil.setAdapter(adapter);

                        userSpinerRegionsProfil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
}
