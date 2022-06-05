package com.example.agendaws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendaws.models.Model_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    TextView id, userName, userEmail, userPassword;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    Model_user user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        initXML();
    }
    
    public void initXML(){
        id = findViewById(R.id.r_userId);
        userName = findViewById(R.id.r_userName);
        userEmail = findViewById(R.id.r_userEmail);
        userPassword = findViewById(R.id.r_userPassword);
        btnRegister = findViewById(R.id.r_btnUser);
        btnRegister.setOnClickListener(this);
    }
    public void addUser(){
        user = new Model_user();
        String nameUser =userName.getText().toString().trim();
        String emailUser = userEmail.getText().toString().trim();
        String passUser = userPassword.getText().toString().trim();

        if(nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()){
            Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show();
        }else{

            user.setName(nameUser);
            user.setEmail(emailUser);
            user.setPassword(passUser);
            registerUser(user);
        }
    }
    public void registerUser(Model_user user){
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Map<String, Object> map = new HashMap<>();
                String userId = mAuth.getCurrentUser().getUid();
                user.setId(userId);
                map.put("id", userId);
                map.put("name", user.getName());
                map.put("email", user.getEmail());
                map.put("password", user.getPassword());

                mFirestore.collection("users").document(userId).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(RegisterUserActivity.this, MainActivity.class));
                        Toast.makeText(RegisterUserActivity.this, "Usuario Guardado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterUserActivity.this, "error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterUserActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v == btnRegister){
            addUser();
        }
    }
}