
package com.example.pro.sofranewapp.data.model.resturant.addnewofferResturant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewOfferResturant {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AddNewOfferData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AddNewOfferData getData() {
        return data;
    }

    public void setData(AddNewOfferData data) {
        this.data = data;
    }

}
