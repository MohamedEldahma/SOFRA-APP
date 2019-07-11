
package com.example.pro.sofranewapp.data.model.clint.ecitprofilCliint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfileData {

    @SerializedName("client")
    @Expose
    private EditClient client;

    public EditClient getClient() {
        return client;
    }

    public void setClient(EditClient client) {
        this.client = client;
    }

}
