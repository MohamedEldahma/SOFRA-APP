package com.example.pro.sofranewapp.ui.frgment.resturant.item;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.resturant.additem.AddItem;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment {


    @BindView(R.id.descrip_item)
    EditText descripItem;
    @BindView(R.id.pric_item)
    EditText pricItem;
    @BindView(R.id.time_item)
    EditText timeItem;
    @BindView(R.id.photo_item)
    ImageView photoItem;
    @BindView(R.id.btt_ittem_add)
    Button bttIttemAdd;
    Unbinder unbinder;
    @BindView(R.id.name_item)
    EditText nameItem;
    private ApiSofraModel apiSofraModel;
    private String api_tokenResturant;
    private ArrayList<AlbumFile> addImgeFill = new ArrayList<>();


    public AddItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        api_tokenResturant = "7jiWQQbN9afm8LTiO4VrmMObYz2lFig117PPCa1vxcK6VsXWy0pGWeq8MA4j";
//        api_tokenResturant = SharedPrefrancClass.LoadStringData(getActivity(),"api_tokenResturant");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.photo_item, R.id.btt_ittem_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.photo_item:
                addtPhoto();
                break;
            case R.id.btt_ittem_add:
                edditMyItem();
                break;
        }
    }

    private void edditMyItem() {
        RequestBody itemName = convertStringToRequestBody(nameItem.getText().toString());
        RequestBody itemDecrip = convertStringToRequestBody(descripItem.getText().toString());
        RequestBody itemPric = convertStringToRequestBody(pricItem.getText().toString());
        RequestBody itemTime = convertStringToRequestBody(timeItem.getText().toString());
        RequestBody apiTokenRest=convertStringToRequestBody(api_tokenResturant);
        MultipartBody.Part imagPart =convertFileToMultipart(addImgeFill.get(0).getPath(),"photo");
        apiSofraModel.addMyItemFood(itemDecrip,itemPric,itemTime,itemName,apiTokenRest,imagPart)
                   .enqueue(new Callback<AddItem>() {
                       @Override
                       public void onResponse(Call<AddItem> call, Response<AddItem> response) {
                           if (response.body().getStatus() == 1){
                               Toast.makeText(getContext(), "MyOrderItem Add tru", Toast.LENGTH_SHORT).show();
                               MyItemFragment myItemFragment = new MyItemFragment();
                               HelperMethod.replaceFrag(myItemFragment,getFragmentManager(),R.id.id_fram_Home_nvigation1);

                           }else {
                               Toast.makeText(getContext(), "MyOrderItem Not Add", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<AddItem> call, Throwable t) {
                           Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                       }
                   });


    }
    private void addtPhoto() {


        Action<ArrayList<AlbumFile>> action = new Action<ArrayList<AlbumFile>>() {
            @Override
            public void onAction(@NonNull ArrayList<AlbumFile> result) {

                addImgeFill.clear();
                addImgeFill.addAll(result);

                Glide.with(getContext()).load(addImgeFill.get(0).getPath()).into(photoItem);


            }
        };

        openAlbum(3, getActivity(), addImgeFill, action);


    }
}
