
package com.example.pro.sofranewapp.data.model.clint.registerClient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("user")
    @Expose
    private RegisterUser user;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public RegisterUser getUser() {
        return user;
    }

    public void setUser(RegisterUser user) {
        this.user = user;
    }

}
