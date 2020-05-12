package com.example.hansel_christopher.engagerpro;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity  {
    TextView userName;
    MyRecyclerViewAdapter adapter;
    SqliteHelper sqliteHelper;
    private LinearLayoutManager LayoutManager;
    private List<Complaint> movieList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        SharedPreferences sp1=this.getSharedPreferences("Login", MODE_PRIVATE);
        String unm=sp1.getString("name", null);
        String id = sp1.getString("id", null);
        Log.v("Name", unm);
        Log.v("id", id);
        sqliteHelper = new SqliteHelper(this);

        userName = (TextView) findViewById(R.id.welcome);
        userName.setText("Welcome back, "+ unm);
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ListingActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        ArrayList<String> animalNames = new ArrayList<>();


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
