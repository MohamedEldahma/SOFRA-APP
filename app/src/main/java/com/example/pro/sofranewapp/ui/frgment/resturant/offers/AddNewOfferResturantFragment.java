package com.example.pro.sofranewapp.ui.frgment.resturant.offers;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.addnewofferResturant.AddNewOfferResturant;
import com.example.pro.sofranewapp.helper.CalendrHelper;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pro.sofranewapp.helper.HelperMethod.convertFileToMultipart;
import static com.example.pro.sofranewapp.helper.HelperMethod.convertStringToRequestBody;
import static com.example.pro.sofranewapp.helper.HelperMethod.openAlbum;
import static com.example.pro.sofranewapp.helper.HelperMethod.replaceFrag;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewOfferResturantFragment extends Fragment {


    @BindView(R.id.add_offer_name)
    EditText addOfferName;
    @BindView(R.id.add_offer_decrip)
    EditText addOfferDecrip;
    @BindView(R.id.add_offer_price)
    EditText addOfferPrice;
    @BindView(R.id.add_offer_too_at)
    TextView addOfferTooAt;
    @BindView(R.id.add_offer_frome_at)
    TextView addOfferFromeAt;
    @BindView(R.id.add_offers_layout_from_to)
    LinearLayout addOffersLayoutFromTo;
    @BindView(R.id.add_offer_img)
    ImageView addOfferImg;
    @BindView(R.id.add_product_text)
    TextView addProductText;
    @BindView(R.id.add_offer_btn_Add)
    Button addOfferBtnAdd;
    Unbinder unbinder;



    private String  api_tokenResturant;
    private ApiSofraModel apiSofraModel;
    private ArrayList<AlbumFile> albumImagFiles = new ArrayList<>();
    private Calendar myCalendarDate;

    public AddNewOfferResturantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_offer_resturant, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        api_tokenResturant = "OGqjF8iGLccLQqdOJ11gHDTzdwG6980twebZRnN66mOFWh2P0Qwb3UCFHboc";
        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public  void  addNewOffer(){
        RequestBody  name = convertStringToRequestBody(addOfferName.getText().toString());
        RequestBody  description = convertStringToRequestBody(addOfferDecrip.getText().toString());
        RequestBody  price = convertStringToRequestBody(addOfferPrice.getText().toString());
        RequestBody  api_token = convertStringToRequestBody(api_tokenResturant);


        RequestBody from = convertStringToRequestBody(addOfferFromeAt.getText().toString());
        RequestBody  too = convertStringToRequestBody(addOfferTooAt.getText().toString());
        MultipartBody.Part addPhotoPart = convertFileToMultipart(albumImagFiles.get(0).getPath(),"photo");

        apiSofraModel.addNewOfferRest(description,price,from,name,addPhotoPart,too,api_token)
                    .enqueue(new Callback<AddNewOfferResturant>() {
                        @Override
                        public void onResponse(Call<AddNewOfferResturant> call, Response<AddNewOfferResturant> response) {
                            if (response.body().getStatus() == 1){
                                Toast.makeText(getActivity(), "Add Offer Tru", Toast.LENGTH_SHORT).show();

                                replaceFrag(new MyOffer(),getActivity().getSupportFragmentManager(),R.id.id_fram_Home_nvigation1);

                            }else {
                                Toast.makeText(getActivity(), "Add Offer Falese", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddNewOfferResturant> call, Throwable t) {
                            Toast.makeText(getActivity(), "Add Offer Erorr", Toast.LENGTH_SHORT).show();

                        }
                    });








    }


    @OnClick({R.id.add_offer_too_at, R.id.add_offer_frome_at, R.id.add_offer_img, R.id.add_offer_btn_Add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_offer_too_at:
                CalendrHelper calendrHelper =new CalendrHelper(getContext());
                calendrHelper.showCalendr(addOfferTooAt);
                break;

            case R.id.add_offer_frome_at:
                CalendrHelper calendrHelper1 =new CalendrHelper(getContext());
                calendrHelper1.showCalendr(addOfferFromeAt);

                break;
            case R.id.add_offer_img:
                sellectAddPhoto();
                break;
            case R.id.add_offer_btn_Add:
                addNewOffer();
                break;
        }
    }

//    private void startingDate() {
//
//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                myCalendarDate.set(YEAR, year);
//                myCalendarDate.set(Calendar.MONTH, month);
//                myCalendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                String myFormat = "yyyy-MM-dd"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//                addOfferFromeAt.setText(sdf.format(myCalendarDate.getTime()));
//
//
//            }
//        };
//
//
//        new DatePickerDialog(getActivity(), date, myCalendarDate.get(Calendar.YEAR),
//                myCalendarDate.get(Calendar.MONTH), myCalendarDate.get(Calendar.DAY_OF_MONTH)).show();
//
//    }

//    private void endingAtDate() {
//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                myCalendarDate.set(YEAR, year);
//                myCalendarDate.set(Calendar.MONTH, month);
//                myCalendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                String myFormat = "yyyy-MM-dd"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//                addOfferTooAt.setText(sdf.format(myCalendarDate.getTime()));
//
//
//            }
//        };
//
//
//        new DatePickerDialog(getActivity(), date, myCalendarDate.get(Calendar.YEAR),
//                myCalendarDate.get(Calendar.MONTH), myCalendarDate.get(Calendar.DAY_OF_MONTH)).show();
//
//    }


    private void sellectAddPhoto() {


        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {

                albumImagFiles.clear();
                albumImagFiles.addAll(result);

                Glide.with(getContext()).load(albumImagFiles.get(0).getPath()).into(addOfferImg);


            }
        };

        openAlbum(3, getActivity(), albumImagFiles, action);


    }
}
