package com.example.myhoroscope.Activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.myhoroscope.DatabaseHelper;
import com.example.myhoroscope.R;

import java.util.ArrayList;
import java.util.List;

public class ZodiacActivity extends AppCompatActivity {

    DatabaseHelper myDatabaseHelper;
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiac);

        myListView = findViewById(R.id.zodiacListView);
        myDatabaseHelper = new DatabaseHelper(this);
        
        populateListView();
    }

    private void populateListView() {

        Cursor data = myDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while(data.moveToNext()) {
            listData.add(data.getString(1));
            listData.add(data.getString(2));
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_zodiac, listData);
        myListView.setAdapter(adapter);

    }
}
