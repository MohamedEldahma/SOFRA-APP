
package com.example.pro.sofranewapp.data.model.clint.completcrderclint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompletData {

    @SerializedName("order")
    @Expose
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
