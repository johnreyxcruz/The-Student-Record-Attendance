package com.johnreytamondong.recordattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText FullName,EMail,Password,Phone;
    Button btnRegister;
    TextView LoginButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FullName = findViewById(R.id.fullName);
        EMail = findViewById(R.id.eMail);
        Password = findViewById(R.id.password);
        Phone = findViewById(R.id.phone);
        btnRegister = findViewById(R.id.btnRegister);
        LoginButton = findViewById(R.id.loginText);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMail = EMail.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(eMail)){
                    EMail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Password.setError("Password is Required.");
                    return;
                }
                if (password.length() <6){
                    Password.setError("Password Must be >=6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);


                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(eMail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}