package com.nguyenloi.crudfirebasetotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AddStudentActivity extends AppCompatActivity {
    EditText edtAddUsername, edtAddEmail, edtAddNumber, edtAddUrl;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        setControl();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", edtAddUsername.getText().toString());
        map.put("email", edtAddEmail.getText().toString());
        map.put("image", edtAddUrl.getText().toString());
        map.put("number", Integer.parseInt(edtAddNumber.getText().toString()));
        FirebaseDatabase.getInstance().getReference().child("Students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(AddStudentActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(AddStudentActivity.this, "Add Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddStudentActivity.this, "Add Failed. You can try again", Toast.LENGTH_SHORT).show();
            }
        }) ;
    }

    private void setControl() {
        edtAddEmail = findViewById(R.id.edtAddEmail);
        edtAddNumber = findViewById(R.id.edtAddNumber);
        edtAddUrl = findViewById(R.id.edtAddUrl);
        edtAddUsername = findViewById(R.id.edtAddUsername);
        btnAdd = findViewById(R.id.btnAdd);
    }


}