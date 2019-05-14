
package com.example.pro.sofranewapp.data.model.resturant.restaurantlogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestLoginData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
