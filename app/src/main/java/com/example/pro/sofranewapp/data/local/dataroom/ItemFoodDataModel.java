package com.example.pro.sofranewapp.data.local.dataroom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ItemFoodDataModel {

    public ItemFoodDataModel(String notes ,  String photo_Url, String quantity, String price, String name, String restaurantId , int itemId) {

        this.photo_Url = photo_Url;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.restaurantId = restaurantId;
        this.notes = notes;
        this.itemId = itemId;

    }


    public ItemFoodDataModel(){

    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int itemId;

    @ColumnInfo(name = "photo_Url")
    private String photo_Url;

    @ColumnInfo(name = "notes")
    private String notes;

    @ColumnInfo(name = "quantity")
    private String quantity;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "RestaurantId")
    private String restaurantId;

//    @ColumnInfo(name = "total")
//    private int total;
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int total) {
//        this.total = total;
//    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto_Url() {
        return photo_Url;
    }

    public void setPhoto_Url(String photo_Url) {
        this.photo_Url = photo_Url;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}




