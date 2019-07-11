
package com.example.pro.sofranewapp.data.model.clint.compleetneworderclint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompleetData {

    @SerializedName("order")
    @Expose
    private CompleetOrder order;

    public CompleetOrder getOrder() {
        return order;
    }

    public void setOrder(CompleetOrder order) {
        this.order = order;
    }

}
