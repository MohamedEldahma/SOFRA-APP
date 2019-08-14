package com.example.pro.sofranewapp.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.model.clint.listnotificationclint.ListNotificationDatum;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitListNotifcationAdapterClint extends RecyclerView.Adapter<GitListNotifcationAdapterClint.ViewHolder> {
    List<ListNotificationDatum> datumListNotifications;
    private Context context;
    private Activity activity;

    public GitListNotifcationAdapterClint(List<ListNotificationDatum> datumListNotifications, Context context, Activity activity) {
        this.datumListNotifications = datumListNotifications;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_getlist_notification_adapterclint, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ListNotificationDatum listNotification = datumListNotifications.get(i);

        String createdAt = listNotification.getCreatedAt();
        String title = listNotification.getTitle();
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        Date date = null;
        try {
            date = formatDate.parse(createdAt);
            String date1 = formatDate.format(date);
            viewHolder.notificationAlrtDate.setText(date1);

        } catch (ParseException e) {
            e.printStackTrace();
        }



        DateFormat formatTime = new SimpleDateFormat("HH:mm:ss a",Locale.CANADA);
        Date time = null;
        try {
            time = formatTime.parse(createdAt);
            String time1 = formatTime.format(time);
            viewHolder.notificationAlertTime.setText(time1);
        } catch (ParseException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        viewHolder.notificationAlertAddress.setText(title);
//
        viewHolder.notificationAlertAddress.setText(title);
        viewHolder.notificationAlrtDate.setText(createdAt);
    }


    @Override
    public int getItemCount() {
        return  datumListNotifications.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notification_img_alert)
        ImageView notificationImgAlert;
        @BindView(R.id.notification_alert_address)
        TextView notificationAlertAddress;
        @BindView(R.id.notification_alrt_date)
        TextView notificationAlrtDate;
        @BindView(R.id.notification_alert_time)
        TextView notificationAlertTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
