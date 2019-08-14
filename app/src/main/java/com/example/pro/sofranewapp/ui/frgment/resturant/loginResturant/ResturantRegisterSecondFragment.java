package com.example.pro.sofranewapp.ui.frgment.resturant.loginResturant;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.categories.Categories;
import com.example.pro.sofranewapp.data.model.resturant.categories.CategoriesDatum;
import com.example.pro.sofranewapp.data.model.resturant.registerresturant.RegisterResturant;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.helper.MultiSelectionSpinner;
import com.example.pro.sofranewapp.helper.SharedPrefManager;
import com.example.pro.sofranewapp.ui.frgment.resturant.HomeSelFragment;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static com.example.pro.sofranewapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.pro.sofranewapp.helper.HelperMethod.convertStringToRequestBody;
import static com.example.pro.sofranewapp.helper.HelperMethod.openAlbum;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResturantRegisterSecondFragment extends Fragment {


    @BindView(R.id.rest_types_sp)
    MultiSelectionSpinner restTypesSp;
    @BindView(R.id.Layout2_types)
    RelativeLayout Layout2Types;
    @BindView(R.id.minimumorder_resturant)
    EditText minimumorderResturant;
    @BindView(R.id.delivryfee_resturant)
    EditText delivryfeeResturant;
    @BindView(R.id.phonen_umber_resturant)
    EditText phonenUmberResturant;
    @BindView(R.id.phone_whatsupo_registe)
    EditText phoneWhatsupoRegiste;
    @BindView(R.id.shop_photo)
    ImageView shopPhoto;
    @BindView(R.id.registersecond_resturantsecond)
    Button registersecondResturantsecond;
    Unbinder unbinder;
    ApiSofraModel apiSofraModel;
    private List<CategoriesDatum> categoryData;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private int region_id;
    private ArrayList<AlbumFile> albumFiles = new ArrayList<>();
    SharedPrefManager sharedPrefManager;


    public ResturantRegisterSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resturant_register_second, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        name = getArguments().getString("res_Name");
        email = getArguments().getString("res_Email");
        password = getArguments().getString("res_Password");
        confirmPassword = getArguments().getString("res_ConfirmPassword");
        region_id = getArguments().getInt("startRegionId", 0);
        getCatogries();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void addReturantRegisterRequest() {
        RequestBody reqPadyName = convertStringToRequestBody(name);
        RequestBody reqPadyEmail = convertStringToRequestBody(email);
        RequestBody reqPadyPassword = convertStringToRequestBody(password);
        RequestBody reqPadyConfirmPassword = convertStringToRequestBody(confirmPassword);
        RequestBody reqPadyRegionId = convertStringToRequestBody(String.valueOf(region_id));
        RequestBody reqPadyMinimuOrder = convertStringToRequestBody(minimumorderResturant.getText().toString());
        RequestBody reqPadyDelevaryFeed = convertStringToRequestBody(delivryfeeResturant.getText().toString());
        RequestBody reqPadyPhoneNumber = convertStringToRequestBody(phonenUmberResturant.getText().toString());
        RequestBody reqPadyPhoneWhatsup = convertStringToRequestBody(phoneWhatsupoRegiste.getText().toString());
        MultipartBody.Part reqPadyaddimagPart = convertFileToMultipart(albumFiles.get(0).getPath(), "photo");
        List<String> selectedStrings = restTypesSp.getSelectedStrings();
        List<String> listselected = new ArrayList<>();

        for (int i = 0; i < categoryData.size(); i++) {
            if (selectedStrings.contains(categoryData.get(i).getName())) {
                listselected.add(categoryData.get(i).getId().toString());
            }
        }
        List<RequestBody> requestBody_listCatogries = new ArrayList<>();
        for (int i = 0; i < listselected.size(); i++) {
            requestBody_listCatogries.add(convertStringToRequestBody(listselected.get(i)));

            Log.d(TAG, "list: " + requestBody_listCatogries.toArray().toString());
        }

        apiSofraModel.addRegisterResturant(reqPadyName, reqPadyEmail, reqPadyPassword
                , reqPadyConfirmPassword, reqPadyRegionId, reqPadyMinimuOrder
                , reqPadyDelevaryFeed, requestBody_listCatogries, reqPadyPhoneNumber
                , reqPadyPhoneWhatsup, reqPadyaddimagPart, convertStringToRequestBody("open"))
                .enqueue(new Callback<RegisterResturant>() {
                    @Override
                    public void onResponse(Call<RegisterResturant> call, Response<RegisterResturant> response) {
                           String msg = response.body().getMsg();
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getContext(), " "+msg, Toast.LENGTH_SHORT).show();

                            HomeSelFragment homeSelFragment = new HomeSelFragment();
                            HelperMethod.replaceFrag(homeSelFragment, getFragmentManager(), R.id.id_fram_Home_nvigation1);

                        } else {
                            Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResturant> call, Throwable t) {
                        Toast.makeText(getActivity(), "Register Erorr", Toast.LENGTH_SHORT).show();

                    }
                });


    }


    private void getCatogries() {
        apiSofraModel.getCaregoriesResturant()
                .enqueue(new Callback<Categories>() {
                    @Override
                    public void onResponse(Call<Categories> call, Response<Categories> response) {
                        long status = response.body().getStatus();
                        if (status == 1) {
                            Toast.makeText(getContext(), "Cetegory Tru" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            categoryData = response.body().getData();
                            List<String> nameList = new ArrayList<>();
                            final List<Integer> idList = new ArrayList<>();
                            nameList.add("التصنيفات");
                            for (int i = 0; i < categoryData.size(); i++) {
                                String name = categoryData.get(i).getName();
                                int idCato = categoryData.get(i).getId();
                                nameList.add(name);
                                idList.add(idCato);

                            }
                            restTypesSp.setItems(nameList);

                        } else {
                            Toast.makeText(getContext(), "now data", Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onFailure(Call<Categories> call, Throwable t) {
                        Toast.makeText(getContext(), "Error NewItemData", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @OnClick({R.id.shop_photo, R.id.registersecond_resturantsecond})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_photo:
                getAlbumFill();
                break;
            case R.id.registersecond_resturantsecond:
                addReturantRegisterRequest();

                break;
        }
    }

    private void getAlbumFill() {

        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                albumFiles.clear();
                albumFiles.addAll(result);


                Glide.with(getContext()).load(albumFiles.get(0).getPath()).into(shopPhoto);


            }
        };
        openAlbum(3, getActivity(), albumFiles, action);

    }
}