package com.example.myhoroscope.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myhoroscope.JsonPlaceHolderApi;
import com.example.myhoroscope.NetworkClient;
import com.example.myhoroscope.WResponse;
import com.example.myhoroscope.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuoteActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        textViewResult = findViewById(R.id.quote_result);

        getDailyQuote();
    }

    private void getDailyQuote() {

        Retrofit retrofit = NetworkClient.getRetrofitClient();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call call = jsonPlaceHolderApi.getDailyQuote();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                if(response.body() != null) {
                    WResponse wResponse = (WResponse) response.body();

                    String content = "";
                    content += "Title: " + wResponse.getContents().getQuoteAtFirstIndex().getTitle() + "\n";
                    content += "Quote: " + wResponse.getContents().getQuoteAtFirstIndex().getQuote() + "\n" ;
                    content += "Author: " + wResponse.getContents().getQuoteAtFirstIndex().getAuthor() + "\n" ;
                    content += "Date: " + wResponse.getContents().getQuoteAtFirstIndex().getDate() + "\n" ;

                    textViewResult.setText(content);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                textViewResult.setText(t.getMessage());

            }
        });

    }
}
