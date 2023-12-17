package com.johnreytamondong.recordattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText Name,Course,Section,Email,turl;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Name = (EditText) findViewById(R.id.txtName);
        Course = (EditText) findViewById(R.id.txtCourse);
        Section = (EditText) findViewById(R.id.txtSection);
        Email = (EditText) findViewById(R.id.txtEmail);
        turl = (EditText) findViewById(R.id.txtImgurl);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                finish();
            }
        });

    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("Name",Name.getText().toString());
        map.put("Course",Course.getText().toString());
        map.put("Section",Section.getText().toString());
        map.put("Email",Email.getText().toString());
        map.put("turl",turl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Added Successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity.this,"Error while Inserting Data.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll(){
        Name.setText("");
        Course.setText("");
        Section.setText("");
        Email.setText("");
        turl.setText("");
    }
}