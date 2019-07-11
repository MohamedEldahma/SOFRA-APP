
package com.example.pro.sofranewapp.data.model.clint.compleetneworderclint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompleetNeworderClint {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private CompleetData data;

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

    public CompleetData getData() {
        return data;
    }

    public void setData(CompleetData data) {
        this.data = data;
    }

}
