package com.example.hansel_christopher.engagerpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        FloatingActionButton help =
                (FloatingActionButton) findViewById(R.id.floating_action_button);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:011-23386447"));
                startActivity(intent);
            }
        });
        final TextView myClickableUrl = (TextView) findViewById(R.id.open);
        myClickableUrl.setMovementMethod(LinkMovementMethod.getInstance());
        final TextView myClickableUrl1 = (TextView) findViewById(R.id.open1);
        myClickableUrl1.setMovementMethod(LinkMovementMethod.getInstance());
        final TextView myClickableUrl2 = (TextView) findViewById(R.id.open2);
        myClickableUrl2.setMovementMethod(LinkMovementMethod.getInstance());
        final TextView myClickableUrl3 = (TextView) findViewById(R.id.open3);
        myClickableUrl3.setMovementMethod(LinkMovementMethod.getInstance());
        final TextView myClickableUrl4 = (TextView) findViewById(R.id.open4);
        myClickableUrl4.setMovementMethod(LinkMovementMethod.getInstance());

    }

}
