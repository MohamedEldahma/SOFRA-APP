package com.example.pro.sofranewapp.data.local.dataroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface RoomItemDao {

    @Query("SELECT * fROM ItemFoodDataModel")
    List<ItemFoodDataModel> getAllData();

    @Insert
    void   insertAll(ItemFoodDataModel... cartModels);

    @Query("delete from ItemFoodDataModel")
    void deletAll();

    @Delete
    void deleteItem(ItemFoodDataModel ... cartModels);
    @Update
    void update(ItemFoodDataModel ... cartModels);

//    @Insert
//    void addItemToCar(ItemFoodDataModel...insertFoodItem);
//
//    @Update
//    void updateItemToCar(ItemFoodDataModel... updateFoodItem);
//
//    @Delete
//    void deleteItemToCar(ItemFoodDataModel... updateFoodItem);
//
//    @Query("Delete from ItemFoodDataModel")
//    void deleteAllItemToCar();
//
//    @Query("Select * from ItemFoodDataModel")
//    List<ItemFoodDataModel> getAllItem();
}
