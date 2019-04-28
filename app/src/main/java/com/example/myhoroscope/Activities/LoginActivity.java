package com.example.myhoroscope.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myhoroscope.R;
import com.google.android.gms.flags.impl.SharedPreferencesFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private EditText userMail, userPassword;
    private Button loginBtn;
    private CheckBox checkBox;
    private ProgressBar loginProgress;
    private Intent homeActivity;
    private FirebaseAuth auth;
    private ImageView imgUserPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.loginMail);
        userPassword = findViewById(R.id.loginPassword);
        checkBox = findViewById(R.id.loginCheckBox);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor =  preferences.edit();
        loginBtn = findViewById(R.id.loginButton);
        loginProgress = findViewById(R.id.loginProgressBar);
        auth = FirebaseAuth.getInstance();
        homeActivity = new Intent(this, HomeActivity.class);
        imgUserPhoto = findViewById(R.id.loginUserPhoto);

        checkSharedPreferences();

        imgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerActivity);
                finish();

            }
        });

        loginProgress.setVisibility(View.INVISIBLE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginBtn.setVisibility(View.INVISIBLE);
                loginProgress.setVisibility(View.VISIBLE);

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if(checkBox.isChecked()) {

                    editor.putString(getString(R.string.checkbox), "True");
                    editor.commit();

                    editor.putString(getString(R.string.mail), mail);
                    editor.commit();

                    editor.putString(getString(R.string.password), password);
                    editor.commit();
                }
                else {

                    editor.putString(getString(R.string.checkbox), "False");
                    editor.commit();

                    editor.putString(getString(R.string.mail), "");
                    editor.commit();

                    editor.putString(getString(R.string.password), "");
                    editor.commit();

                }

                if(mail.isEmpty() || password.isEmpty()) {

                    showMessage("Please verify the fields");
                    loginBtn.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else {
                    signIn(mail, password);
                }

            }
        });

    }

    private void checkSharedPreferences() {

        String checkbox = preferences.getString(getString(R.string.checkbox), "False");
        String mail = preferences.getString(getString(R.string.mail), "");
        String password =  preferences.getString(getString(R.string.password), "");

        userMail.setText(mail);
        userPassword.setText(password);

        if(checkbox.equals("True"))
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);
    }

    private void signIn(String mail, String password) {

        auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    loginProgress.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else {

                    loginBtn.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                    showMessage(task.getException().getMessage());
                }

            }
        });


    }

    private void updateUI() {

        startActivity(homeActivity);
        finish();
    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
/*
    @Override
    protected void onStart() {

        super.onStart();
        FirebaseUser user = auth.getCurrentUser();

        if(user != null) {

            updateUI();
        }
    }
    */
}

