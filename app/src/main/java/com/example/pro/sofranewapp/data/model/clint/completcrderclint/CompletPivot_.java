
package com.example.pro.sofranewapp.data.model.clint.completcrderclint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompletPivot_ {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}
