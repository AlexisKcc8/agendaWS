package com.example.agendaws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendaws.models.Model_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnGetInto;
    TextView txtRegister;
    EditText txtEmail, txtPassword;
    FirebaseAuth mAuth;
    Model_user user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        XML();
    }

    public void XML(){
        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.l_userEmail);
        txtPassword = findViewById(R.id.l_userPassword);

        txtRegister = findViewById(R.id.l_lblRegsiter);
        txtRegister.setOnClickListener(this);

        btnGetInto = findViewById(R.id.l_btnGetInto);
        btnGetInto.setOnClickListener(this);
    }

    public void loginUser(Model_user user){
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser userLog = mAuth.getCurrentUser();
                            if (userLog != null) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //currentUser.reload();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    public void onClick(View v) {
        if(v == btnGetInto){
            user = new Model_user();
            String emailUser = txtEmail.getText().toString().trim();
            String passUser = txtPassword.getText().toString().trim();

            if(emailUser.isEmpty() && passUser.isEmpty()){
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show();
            }else{
                user.setEmail(emailUser);
                user.setPassword(passUser);
                loginUser(user);
            }
        }
        if(v == txtRegister){
            Intent i = new Intent(LoginActivity.this, RegisterUserActivity.class);
            startActivity(i);
        }
    }
}