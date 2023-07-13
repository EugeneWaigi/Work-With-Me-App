package com.example.workwithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workwithme.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostJobActivity extends AppCompatActivity {
    private FloatingActionButton fabBtn;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private DatabaseReference JobPostDatabase;
    private FirebaseRecyclerAdapter<Data, MyViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        fabBtn = findViewById(R.id.floatbutton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uID = mUser.getUid();

        JobPostDatabase = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uID);

        recyclerView = findViewById(R.id.id_recycler_postjobs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InsertJobPostActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Data> options = new FirebaseRecyclerOptions.Builder<Data>()
                .setQuery(JobPostDatabase, Data.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Data, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position, @NonNull Data model) {
                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobDate(model.getDate());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setJobSkills(model.getSkills());
                viewHolder.setJobSalary(model.getSalary());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_post_item, parent, false);
                return new MyViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View myview;

        public MyViewHolder(View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setJobTitle(String title) {
            TextView mTitle = myview.findViewById(R.id.id_ya_jobtitle);
            mTitle.setText(title);
        }

        public void setJobDate(String date) {
            TextView mDate = myview.findViewById(R.id.job_date);
            mDate.setText(date);
        }

        public void setJobDescription(String description) {
            TextView mDescription = myview.findViewById(R.id.id_ya_jobdescription);
            mDescription.setText(description);
        }

        public void setJobSkills(String skills) {
            TextView mSkills = myview.findViewById(R.id.id_ya_jobskills);
            mSkills.setText(skills);
        }

        public void setJobSalary(String salary) {
            TextView mSalary = myview.findViewById(R.id.id_ya_salary);
            mSalary.setText(salary);
        }
    }
}
