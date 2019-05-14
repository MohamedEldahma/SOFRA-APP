
package com.example.pro.sofranewapp.data.model.resturant.additem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddItem {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AddItemData data;

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

    public AddItemData getData() {
        return data;
    }

    public void setData(AddItemData data) {
        this.data = data;
    }

}
