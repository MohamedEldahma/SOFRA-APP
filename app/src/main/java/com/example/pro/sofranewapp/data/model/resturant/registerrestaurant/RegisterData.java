
package com.example.pro.sofranewapp.data.model.resturant.registerrestaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("data")
    @Expose
    private RegisterDatum data;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public RegisterDatum getData() {
        return data;
    }

    public void setData(RegisterDatum data) {
        this.data = data;
    }

}
