package com.example.myhoroscope.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhoroscope.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth auth;
    FirebaseUser currentUser;

    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        myToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.open, R.string.close);
        myDrawerLayout.addDrawerListener(myToggle);
        myToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

      //  updateHeader();
        setNavigationViewListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(myToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation2);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.dailyquoutedb) openQuoteActivity();
        if(id == R.id.zodiacdb) openZodiacActivity();

        return true;
    }

    public void openQuoteActivity() {
        Intent intent = new Intent(this, QuoteActivity.class);
        startActivity(intent);
    }

    public void openZodiacActivity() {
        Intent intent = new Intent(this, ZodiacActivity.class);
        startActivity(intent);
    }

   /* public void updateHeader() {

        NavigationView navigationView = findViewById(R.id.navigation);
        View headerView  = navigationView.getHeaderView(0);

        TextView navName = headerView.findViewById(R.id.header_name);
        TextView navMail = headerView.findViewById(R.id.header_mail);
        ImageView navImg = headerView.findViewById(R.id.header_picture);

        navName.setText(currentUser.getDisplayName());
        navMail.setText(currentUser.getEmail());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navImg);

    }*/
}
