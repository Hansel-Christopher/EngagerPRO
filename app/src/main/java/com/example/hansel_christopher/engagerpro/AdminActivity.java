package com.example.hansel_christopher.engagerpro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdminActivity extends AppCompatActivity {
    Button mButton;
    SqliteHelper sqliteHelper;
    MyRecyclerViewAdapter adapter;

    private String m_Text = "";

    private List<Complaint> movieList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        sqliteHelper = new SqliteHelper(this);
        String dept = getIntent().getStringExtra("DEPT");

        final TextView mTextView = (TextView) findViewById(R.id.welcome);
        mTextView.setText(dept);
//        mButton = findViewById(R.id.containedButton);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(AdminActivity.this, MapsActivity.class);
//                startActivity(myIntent);
//            }
//        });
        Log.d("Title", "()");
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new MyRecyclerViewAdapter(movieList);

        recyclerView.setAdapter(adapter);
        prepareMovieData();
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AdminActivity.this);
                alert.setTitle("Complaint ID completed: ");
                final EditText input = new EditText(AdminActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alert.setView(input);


                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        Complaint c = sqliteHelper.getTitle(m_Text);
                        Boolean done = sqliteHelper.deleteComplaint(m_Text);
                        if(done){
                            Log.d("Complaint:", c.title.toString());
                            String title =c.title.toString();
//                            String name = c.uid.toString();
//                            NotificationCompat.Builder mBuilder =
//                                    new NotificationCompat.Builder(AdminActivity.this)
//                                            .setSmallIcon(R.drawable.help)
//                                            .setContentTitle("My notification")
//                                            .setContentText("Hello World!");
//                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                            notificationManager.notify(1, mBuilder.build());
//                            addNotification(title);
//                            Intent intent = new Intent(Intent.ACTION_SEND);
//                            intent.setData(Uri.parse("smsto:"+num)); // This ensures only SMS apps respond
//                            intent.putExtra("sms_body", title);
//
                            dialog.cancel();
                            recreate();
                        }else{
                            dialog.cancel();
                        }
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

    }
    @Override
    public void recreate()
    {
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            super.recreate();
        }
        else
        {
            startActivity(getIntent());
            finish();
        }
    }

    private void prepareMovieData() {
        List<Complaint> allComplaints = sqliteHelper.getAllComplaints();
        for (Complaint ca : allComplaints) {
            String title = "ID: " + ca.getId() + " " + ca.getTitle().toString();
            String dept = ca.getDesc() + " @ " + ca.getLoc().toString();
            Complaint cx = new Complaint(title, dept);
            movieList.add(cx);
        }
//
//        adapter.notifyDataSetChanged();
    }
    private void addNotification(String title) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.help) //set icon for notification
                        .setContentTitle("Complaint resolved") //set title of notification
                        .setContentText("Your complaint - " + title + " has been resolved")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification


        Intent notificationIntent = new Intent(AdminActivity.this, Notification.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
