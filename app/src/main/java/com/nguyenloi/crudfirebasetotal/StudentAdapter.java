package com.nguyenloi.crudfirebasetotal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class StudentAdapter extends FirebaseRecyclerAdapter<Student, StudentAdapter.StudentViewHolder> {
    Context context;

    public StudentAdapter(@NonNull FirebaseRecyclerOptions<Student> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Student model) {
        holder.tvItemNumber.setText(model.getNumber() + "");
        holder.tvItemEmail.setText(model.getEmail());
        holder.tvItemUsername.setText(model.getUserName());
        Picasso.get().load(model.getImage()).into(holder.imgItemImage);

        //Remove data;
        holder.imgItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Students")
                        .child(getRef(position).getKey()).removeValue();
            }
        });

        //Go to page detail
        holder.imgItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStudent = "";
                idStudent =FirebaseDatabase.getInstance().getReference().child("Students")
                       .child(getRef(position).getKey()).getKey();
                Intent intent = new Intent(context,DetailActivty.class);
                intent.putExtra("key",idStudent);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new StudentViewHolder(view);
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemUsername, tvItemEmail, tvItemNumber;
        private CircleImageView imgItemImage;
        private ImageView imgItemRemove;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemEmail = itemView.findViewById(R.id.tvItemEmail);
            tvItemNumber = itemView.findViewById(R.id.tvItemNumber);
            tvItemUsername = itemView.findViewById(R.id.tvItemUsername);
            imgItemImage = itemView.findViewById(R.id.imgItemImage);
            imgItemRemove = itemView.findViewById(R.id.imgItemRemove);
        }
    }
}
