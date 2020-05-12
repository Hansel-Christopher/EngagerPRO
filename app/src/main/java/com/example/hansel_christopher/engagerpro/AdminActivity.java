package com.example.hansel_christopher.engagerpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    Button mButton;
    SqliteHelper sqliteHelper;
    MyRecyclerViewAdapter adapter;
    private List<Complaint> movieList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        sqliteHelper = new SqliteHelper(this);
        mButton = findViewById(R.id.containedButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AdminActivity.this, MapsActivity.class);
                startActivity(myIntent);
            }
        });
        Log.d("Title", "()");
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new MyRecyclerViewAdapter(movieList);

        recyclerView.setAdapter(adapter);
        prepareMovieData();
    }

    private void prepareMovieData() {
        List<Complaint> allComplaints = sqliteHelper.getAllComplaints();
        for (Complaint ca : allComplaints) {
            String title = ca.getTitle().toString();
            Log.d("Title", ca.getTitle());
            String dept = ca.getDept();
            Complaint cx = new Complaint(title, dept);
            movieList.add(cx);
        }
//
//        adapter.notifyDataSetChanged();
    }
}
