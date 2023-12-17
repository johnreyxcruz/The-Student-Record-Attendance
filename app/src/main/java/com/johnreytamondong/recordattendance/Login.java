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

public class Login extends AppCompatActivity {
    EditText EMail,Password;
    Button btnLogin;
    TextView CreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EMail = findViewById(R.id.eMail);
        Password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        CreateBtn = findViewById(R.id.createText);

        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnLogin);
        CreateBtn = findViewById(R.id.createText);

        btnLogin.setOnClickListener(new View.OnClickListener() {
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

                //autheticate the user

                fAuth.signInWithEmailAndPassword(eMail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Error! Account Doesn't exist", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}