package com.example.hansel_christopher.engagerpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        mButton = (Button) findViewById(R.id.b1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SelectionActivity.this, AdminActivity.class);
                myIntent.putExtra("DEPT",  mButton.getText().toString());
                startActivity(myIntent);

            }
        });
    }
}
