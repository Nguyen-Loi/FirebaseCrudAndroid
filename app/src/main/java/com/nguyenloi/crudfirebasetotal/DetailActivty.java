package com.nguyenloi.crudfirebasetotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivty extends AppCompatActivity {
    CircleImageView imgDetailImage;
    EditText edtDetailUsername, edtDetailEmail, edtDetailNumber;
    Button btnDetailUpdate;
    String key;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activty);
        setControl();

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        //Load data
        loadData();

        //update data
        btnDetailUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("userName", edtDetailUsername.getText().toString());
                map.put("email", edtDetailEmail.getText().toString());
                map.put("number", Integer.parseInt(edtDetailNumber.getText().toString()));
                reference.child("Students")
                        .child(key).updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(DetailActivty.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DetailActivty.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void loadData() {


        reference.child("Students").child(key).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String email = task.getResult().child("email").getValue().toString();
                String userName = task.getResult().child("userName").getValue().toString();
                String number = task.getResult().child("number").getValue().toString();
                String url = task.getResult().child("image").getValue().toString();

                edtDetailEmail.setText(email);
                edtDetailNumber.setText(number);
                edtDetailUsername.setText(userName);
                Picasso.get().load(url).into(imgDetailImage);
            }
        });
    }

    private void setControl() {
        imgDetailImage = findViewById(R.id.imgDetailImage);
        edtDetailEmail = findViewById(R.id.edtDetailEmail);
        edtDetailNumber = findViewById(R.id.edtDetailNumber);
        edtDetailUsername = findViewById(R.id.edtDetailUsername);
        btnDetailUpdate = findViewById(R.id.btnDetailUpdate);

    }
}