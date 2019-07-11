package com.example.pro.sofranewapp.ui.frgment.general;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.general.contactus.ContactUs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactManagmentFragment extends Fragment {


    @BindView(R.id.my_name)
    EditText myName;
    @BindView(R.id.my_mail)
    EditText myMail;
    @BindView(R.id.my_phone)
    EditText myPhone;
    @BindView(R.id.my_content)
    EditText myContent;
    @BindView(R.id.send_id)
    Button sendId;
    @BindView(R.id.cont_facbook)
    CircleImageView contFacbook;
    @BindView(R.id.cont_twitter)
    CircleImageView contTwitter;
    @BindView(R.id.cont_instigram)
    CircleImageView contInstigram;
    Unbinder unbinder;
    ApiSofraModel apiSofraModel;
    @BindView(R.id.complain_id)
    RadioButton complainId;
    @BindView(R.id.suggest_id)
    RadioButton suggestId;
    @BindView(R.id.select_id)
    RadioButton selectId;
    private String typContact;
    public static final String COMPLAINT = "complaint";
    public static final String SUGGESTION = "suggestion";
    public static final String INQUIRY = "inquiry";
    public ContactManagmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_managment, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void addContact() {
        String name = myName.getText().toString();
        String email = myMail.getText().toString();
        String phone = myPhone.getText().toString();
        String content = myContent.getText().toString();

                apiSofraModel.addcontactUs(name, email, phone,typContact, content)
                        .enqueue(new Callback<ContactUs>() {
                            @Override
                            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                                if (response.body().getStatus() == 1) {
                                    
                                    Toast.makeText(getActivity(), "SEND TRU", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(getActivity(), "Send False", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ContactUs> call, Throwable t) {
                                Toast.makeText(getActivity(), "Erorr", Toast.LENGTH_SHORT).show();
                            }
                        });


    }


    @OnClick({R.id.complain_id, R.id.suggest_id, R.id.select_id, R.id.send_id, R.id.cont_facbook, R.id.cont_twitter, R.id.cont_instigram})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complain_id:
                typContact = COMPLAINT;
                break;
            case R.id.suggest_id:
                typContact = SUGGESTION;
                break;
            case R.id.select_id:
                typContact = INQUIRY;
                break;
            case R.id.send_id:
                addContact();
                break;case R.id.cont_facbook:

                break;
            case R.id.cont_twitter:
                break;
            case R.id.cont_instigram:
                break;
        }
    }
}
