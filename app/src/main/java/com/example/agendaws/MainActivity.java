package com.example.agendaws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCSession;
    TextView txtIdUser;
    FirebaseAuth mAuth;
    String emailUserLog, uidUserLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailUserLog = getIntent().getStringExtra("email");
        uidUserLog = getIntent().getStringExtra("uid");
        this.setTitle("Bienvenido " + emailUserLog);
        initXML();
    }
    public void initXML(){
        mAuth = FirebaseAuth.getInstance();
        btnCSession = findViewById(R.id.btnCloseSession);
        btnCSession.setOnClickListener(this);

        txtIdUser = findViewById(R.id.lbl_idUser);
        txtIdUser.setText(uidUserLog);
    }

    @Override
    public void onClick(View v) {
        if(v == btnCSession){
            mAuth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}