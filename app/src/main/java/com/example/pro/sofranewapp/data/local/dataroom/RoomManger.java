package com.example.pro.sofranewapp.data.local.dataroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;


@Database(entities = {ItemFoodDataModel.class}, version = 2)
@TypeConverters({DataTypeConverter.class})
public abstract class RoomManger extends RoomDatabase {



    private static RoomManger roomManger;

    public abstract RoomItemDao roomDao();

    public static synchronized RoomManger getInstance(Context context) {
        if (roomManger == null) {
            roomManger = Room.databaseBuilder(context.getApplicationContext(), RoomManger.class,
                    "sofra_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomManger;
    }

}
