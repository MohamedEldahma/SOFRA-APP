package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.CommentAdapter;
import com.example.pro.sofranewapp.data.ApiSofraModel;
import com.example.pro.sofranewapp.data.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.general.getcommentreview.GetCommentDatum;
import com.example.pro.sofranewapp.data.model.general.getcommentreview.GetCommentReview;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentEvaluationFragment extends Fragment {


    @BindView(R.id.reting_coment_recycler)
    RecyclerView retingComentRecycler;
    Unbinder unbinder;
    private int id_resturant;
    private String api_tokenResturant;
    private List<GetCommentDatum> getCommentDatumList = new ArrayList<>();
    CommentAdapter commentAdapter;
    ApiSofraModel apiSofraModel;
    public CommentEvaluationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment_evaluation, container, false);
        unbinder = ButterKnife.bind(this, view);
         apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        id_resturant = SharedPrefrancClass.LoadIntegerData(getActivity(),"id_Resturant");
//        api_tokenResturant =SharedPrefrancClass.LoadStringData(getActivity(),"api_tokenResturant");
       getComment();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public  void getComment (){
        apiSofraModel.getComent(id_resturant,1)
                .enqueue(new Callback<GetCommentReview>() {
                    @Override
                    public void onResponse(Call<GetCommentReview> call, Response<GetCommentReview> response) {
                        if (response.body().getStatus() == 1){
                            Toast.makeText(getActivity(), "tru", Toast.LENGTH_SHORT).show();
                            retingComentRecycler.setHasFixedSize(true);
                            retingComentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                            commentAdapter = new CommentAdapter(getCommentDatumList,getContext(),getActivity());
                            retingComentRecycler.setAdapter(commentAdapter);
                            List<GetCommentDatum> data =response.body().getData().getData();
                            getCommentDatumList.addAll(data);
                            commentAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getActivity(), "No Comment Show", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<GetCommentReview> call, Throwable t) {

                    }
                });




    }
}
