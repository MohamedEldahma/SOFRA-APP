package com.example.pro.sofranewapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.model.general.getcommentreview.GetCommentDatum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<GetCommentDatum> commentData;
    private Context context;
    private Activity activity;

    public CommentAdapter(List<GetCommentDatum> commentData, Context context, Activity activity) {
        this.commentData = commentData;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.review_item_resturant, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        GetCommentDatum getCommentDatum = commentData.get(i);
        viewHolder.restNam.setText(getCommentDatum.getClient().getName());
        viewHolder.rtingComent.setNumStars(Integer.parseInt(getCommentDatum.getRate()));
        viewHolder.textDate.setText(getCommentDatum.getUpdatedAt());
        viewHolder.textComent.setText(getCommentDatum.getComment());
    }


    @Override
    public int getItemCount() {
        return commentData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rest_nam)
        TextView restNam;
        @BindView(R.id.rting_coment)
        RatingBar rtingComent;
        @BindView(R.id.text_date)
        TextView textDate;
        @BindView(R.id.text_coment)
        TextView textComent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}