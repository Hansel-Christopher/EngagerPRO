package com.example.hansel_christopher.engagerpro;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    Button mButton;
    TextInputLayout til, num, desc, mLocation;
    EditText mEdit;
    TextView addressField, latField;
    String dept;
    Spinner spinner, spinner1;
    SqliteHelper sqliteHelper;
    SharedPreferences sp1;
    String uuid,city;
    CheckBox ch;

    private LocationManager locationManager;
    private LocationListener locationListener = null;
    private double longitude;
    private double latitude;
    private Boolean flag = false;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        mButton = (Button) findViewById(R.id.containedButton);
        mLocation = (TextInputLayout) findViewById(R.id.loc);
        num = (TextInputLayout) findViewById(R.id.number);
        desc = (TextInputLayout) findViewById(R.id.desc);
//        latField = (TextView) findViewById(R.id.latlong);
        ch = (CheckBox) findViewById(R.id.ch);
        dept = spinner.getSelectedItem().toString();
        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);
        uuid = sp1.getString("name", null);
//        locationManager = (LocationManager)
//                getSystemService(Context.LOCATION_SERVICE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.sanitation_array, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = spinner1.getSelectedItem().toString();
                String descr = desc.getEditText().getText().toString();
                String deprt = spinner.getSelectedItem().toString();
                String numb = num.getEditText().getText().toString();
                String loc = mLocation.getEditText().getText().toString();
                Log.v("Loc", loc);
                int number = Integer.parseInt(numb);
                sqliteHelper.addComplaint(new Complaint(null, title, deprt, descr, number, uuid, loc));
                if (ch.isChecked()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "admin@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "A new complaint received - " + title);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, descr);
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } else {
                    Log.v("Dont send", title);
                    Intent myIntent = new Intent(MainActivity.this, ListingActivity.class);
                    startActivity(myIntent);
                }
            }
        });
    }
//        addressField = (TextView) findViewById(R.id.editTextLocation); //Make sure you add this to activity_main
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, false);
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    Activity#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for Activity#requestPermissions for more details.
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        onLocationChanged(location);
//        loc_func(location);

//    }

//
//    @Override
//    public void onLocationChanged(Location location) {
//        //You had this as int. It is advised to have Lat/Loing as double.
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//        latField.setText("Latitude: "+latitude+", Longitude: "+longitude);
//
//    }
//
//    @Override
//    public void onStatusChanged(String s, int i, Bundle bundle){
//
//    }
//
//    @Override
//    public void onProviderEnabled(String s){
//
//    }
//
//    @Override
//    public void onProviderDisabled(String s){
//
//    }
//
//    private void loc_func(Location location){
//        try {
//            Geocoder geocoder = new Geocoder(this);
//            List<Address> address = null;
//            address = geocoder.getFromLocation(latitude, longitude, 1);
//            city = address.get(0).getLocality();
//            addressField.setText("City: "+city);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//    }
}