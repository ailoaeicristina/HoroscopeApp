package com.example.myhoroscope;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WResponse {

    @SerializedName("contents")
    @Expose
    private Contents contents;

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

}
