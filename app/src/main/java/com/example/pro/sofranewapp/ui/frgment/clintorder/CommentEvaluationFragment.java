package com.example.pro.sofranewapp.ui.frgment.clintorder;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.adapter.CommentAdapter;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.reviewclintresturant.ReviewsClintResturant;
import com.example.pro.sofranewapp.data.model.general.getcommentreview.GetCommentDatum;
import com.example.pro.sofranewapp.data.model.general.getcommentreview.GetCommentReview;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentEvaluationFragment extends Fragment {


    @BindView(R.id.reting_coment_recycler)
    RecyclerView retingComentRecycler;
    Unbinder unbinder;
    private int id_resturant;
    private String api_tokent;
    private List<GetCommentDatum> getCommentDatumList = new ArrayList<>();
    CommentAdapter commentAdapter;
    ApiSofraModel apiSofraModel;
    int rating;
    String textComentReview;

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
        id_resturant = SharedPrefrancClass.LoadIntegerData(getActivity(), "id_Resturant");
//        api_tokent= SharedPrefManagerClient.getInstance(getContext()).getClientApiToken();
        api_tokent="HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB";
        getComment();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void getComment() {
        apiSofraModel.getComent(id_resturant, 1)
                .enqueue(new Callback<GetCommentReview>() {
                    @Override
                    public void onResponse(Call<GetCommentReview> call, Response<GetCommentReview> response) {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getActivity(), "tru", Toast.LENGTH_SHORT).show();
                            retingComentRecycler.setHasFixedSize(true);
                            retingComentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                            commentAdapter = new CommentAdapter(getCommentDatumList, getContext(), getActivity());
                            retingComentRecycler.setAdapter(commentAdapter);
                            List<GetCommentDatum> data = response.body().getData().getData();
                            getCommentDatumList.addAll(data);
                            commentAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "No Comment Show", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<GetCommentReview> call, Throwable t) {

                    }
                });


    }

    @OnClick(R.id.add_coment)
    public void onViewClicked() {
        addComentReview();


    }

    public void addComentReview() {


//        final AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(getContext());
//        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
//        final View view = layoutInflater.inflate(R.layout.review_comment, null);
//        dialogbuilder.setView(view);
//                dialogbuilder.show();





        final Dialog rateDialog = new Dialog(getContext());
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.review_comment, null);
        rateDialog.setContentView(view);

         final RatingBar ratingBar = view.findViewById(R.id.rting_coment);
         final EditText addComment = view.findViewById(R.id.add_comment);
         Button    bttAddComent = view.findViewById(R.id.btt_addcomment);
         Button    cncel = view.findViewById(R.id.cancel);
      bttAddComent.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              rating = (int) ratingBar.getRating();
              textComentReview=addComment.getText().toString();

              apiSofraModel.addComment(rating,textComentReview,id_resturant,api_tokent)
                      .enqueue(new Callback<ReviewsClintResturant>() {
                          @Override
                          public void onResponse(Call<ReviewsClintResturant> call, Response<ReviewsClintResturant> response) {
                              if (response.body().getStatus() == 1){
                                  Toast.makeText(getContext(), "Comment Edit", Toast.LENGTH_SHORT).show();

                              }else {
                                  Toast.makeText(getContext(), "No Comment Edit ", Toast.LENGTH_SHORT).show();
                              }
                          }

                          @Override
                          public void onFailure(Call<ReviewsClintResturant> call, Throwable t) {
                              Toast.makeText(getContext(), "Comment Erorrrrrrrrrrrrrrrrrrr ", Toast.LENGTH_SHORT).show();


                          }
                      });

          }
      });
      cncel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              rateDialog.dismiss();
          }
      });
        rateDialog.show();



    }
}
