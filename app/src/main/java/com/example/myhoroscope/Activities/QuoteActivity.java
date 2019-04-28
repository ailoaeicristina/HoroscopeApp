package com.example.myhoroscope.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myhoroscope.Models.JsonPlaceHolderApi;
import com.example.myhoroscope.Models.NetworkClient;
import com.example.myhoroscope.Models.WResponse;
import com.example.myhoroscope.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuoteActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewText;
    private TextView textViewAuthor;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        textViewTitle = findViewById(R.id.title);
        textViewText = findViewById(R.id.quote);
        textViewAuthor = findViewById(R.id.author);
        textViewDate = findViewById(R.id.date);

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

                    String content;
                    content = wResponse.getContents().getQuoteAtFirstIndex().getTitle();
                    textViewTitle.setText(content);

                    content = wResponse.getContents().getQuoteAtFirstIndex().getQuote();
                    textViewText.setText(content);

                    content = wResponse.getContents().getQuoteAtFirstIndex().getAuthor();
                    textViewAuthor.setText(content);

                    content = wResponse.getContents().getQuoteAtFirstIndex().getDate();
                    textViewDate.setText(content);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                textViewTitle.setText(t.getMessage());
                textViewText.setText(t.getMessage());
                textViewAuthor.setText(t.getMessage());
                textViewDate.setText(t.getMessage());
            }
        });

    }
}
