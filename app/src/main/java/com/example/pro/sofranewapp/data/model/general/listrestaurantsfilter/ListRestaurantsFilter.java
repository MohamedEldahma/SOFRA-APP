
package com.example.pro.sofranewapp.data.model.general.listrestaurantsfilter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRestaurantsFilter {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ListRestaurantsFilterData data;

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

    public ListRestaurantsFilterData getData() {
        return data;
    }

    public void setData(ListRestaurantsFilterData data) {
        this.data = data;
    }

}
