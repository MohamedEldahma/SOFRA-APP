
package com.example.pro.sofranewapp.data.model.resturant.restaurantlogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestLoginCategory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pivot")
    @Expose
    private RestLoginPivot pivot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestLoginPivot getPivot() {
        return pivot;
    }

    public void setPivot(RestLoginPivot pivot) {
        this.pivot = pivot;
    }

}