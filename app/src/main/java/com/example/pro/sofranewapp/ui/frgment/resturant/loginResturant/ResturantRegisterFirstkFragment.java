package com.example.pro.sofranewapp.ui.frgment.resturant.loginResturant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.general.cities.Cities;
import com.example.pro.sofranewapp.data.model.general.cities.CitiesDatum;
import com.example.pro.sofranewapp.data.model.general.regions.Regions;
import com.example.pro.sofranewapp.data.model.general.regions.RegionsDatum;
import com.example.pro.sofranewapp.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pro.sofranewapp.helper.ValidationHelper.error;
import static com.example.pro.sofranewapp.helper.ValidationHelper.verbose;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResturantRegisterFirstkFragment extends Fragment {


    @BindView(R.id.name_resturant)
    EditText nameResturant;
    @BindView(R.id.resturant_spiner_city)
    Spinner resturantSpinerCity;
//    @BindView(R.id.resturant_text_spin_city)
//    TextView resturantTextSpinCity;
    @BindView(R.id.resturant_spiner_destrict)
    Spinner resturantSpinerDestrict;
//    @BindView(R.id.resturant_text_spin_destrict)
//    TextView resturantTextSpinDestrict;
    @BindView(R.id.email_resturant)
    EditText emailResturant;
    @BindView(R.id.password_resturant)
    EditText passwordResturant;
    @BindView(R.id.confirm_password_resturant)
    EditText confirmPasswordResturant;
    @BindView(R.id.register1_resturant)
    Button register1Resturant;
    ApiSofraModel apiSofraModel;
    private int startRegionId = 0;
    Unbinder unbinder;

    public ResturantRegisterFirstkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rseturant_register_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        getCities();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                resturantSpinerCity.setAdapter(adapter);

                resturantSpinerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        resturantSpinerDestrict.setAdapter(adapter);

                        resturantSpinerDestrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    verbose("onCityItemSelected: " + idRegion.get(position));
                                    startRegionId = idRegion.get(position);
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

    @OnClick(R.id.register1_resturant)
    public void onViewClicked() {

        String res_Name =nameResturant.getText().toString();
        String res_Email= emailResturant.getText().toString();
        String res_Password=passwordResturant.getText().toString();
        String res_ConfirmPassword=confirmPasswordResturant.getText().toString();
        Bundle bundle =new Bundle();
        bundle.putString("res_Name",res_Name);
        bundle.putString("res_Email",res_Email);
        bundle.putString("res_Password",res_Password);
        bundle.putString("res_ConfirmPassword",res_ConfirmPassword);
        bundle.putInt("startRegionId",startRegionId);

        ResturantRegisterSecondFragment resturantRegisterSecondFragment =new ResturantRegisterSecondFragment();
        resturantRegisterSecondFragment.setArguments(bundle);
       HelperMethod.replaceFrag(resturantRegisterSecondFragment,getFragmentManager(),R.id.id_fram_Home_nvigation1);
        Toast.makeText(getContext(), "Tru", Toast.LENGTH_SHORT).show();



    }




}
