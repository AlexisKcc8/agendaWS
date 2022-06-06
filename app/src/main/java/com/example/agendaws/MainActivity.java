package com.example.agendaws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnNewEvent, btnCSession;
    TextView txtIdUser;
    FirebaseAuth mAuth;
    String emailUserLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        getUser();
        this.setTitle("Bienvenido " + emailUserLog);
        initXML();
    }
    public void initXML(){
        btnCSession = findViewById(R.id.btnCloseSession);
        btnCSession.setOnClickListener(this);
        btnNewEvent = findViewById(R.id.btnNewEvent);
        btnNewEvent.setOnClickListener(this);

    }
    public void getUser(){
        FirebaseUser userLog = mAuth.getCurrentUser();
        if (userLog != null) {
            for (UserInfo profile : userLog.getProviderData()) {
                emailUserLog = profile.getEmail();
            };
        }
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
            //Intent i = new Intent(MainActivity.this, MainActivity.class);
            //startActivity(i);
            //finish();
        }
    }
    @Override
    public void onClick(View v) {
        if(v == btnCSession){
            mAuth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        if(v == btnNewEvent ){
            Intent i = new Intent(MainActivity.this, NewEventActivity.class);
            i.putExtra("idUser", emailUserLog);
            startActivity(i);
        }
    }
}