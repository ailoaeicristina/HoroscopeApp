package com.example.myhoroscope;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Contents {

    @SerializedName("quotes")
    @Expose
    private ArrayList<Quotes> quotes;

    public ArrayList<Quotes> getQuotes() {
        return quotes;
    }

    public Quotes getQuoteAtFirstIndex() {
        return quotes.get(0);
    }

}
