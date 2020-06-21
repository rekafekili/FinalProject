package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class CategorySelectActivity extends AppCompatActivity implements PermissionListener {
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        findViewById(R.id.select_region_textview).setOnClickListener(view -> {
            Intent intent = new Intent(this, AreaSelectActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.select_location_textview).setOnClickListener(view -> {
            TedPermission.with(this)
                    .setPermissionListener(this)
                    .setDeniedMessage("If you reject permission,you can not use this service\\n\\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .check();
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onPermissionGranted() {
        fusedLocationProviderClient.getLastLocation()
                .addOnFailureListener(this, error -> {
                    Toast.makeText(this, "현재 위치를 얻지 못했습니다.", Toast.LENGTH_SHORT).show();
                })
                .addOnSuccessListener(this, location -> {
                    if(location != null){
                        Intent intent = new Intent(this, EventListActivity.class);
                        intent.putExtra("SERVICE", "LOCATION");
                        intent.putExtra("MAP_X", location.getLongitude());
                        intent.putExtra("MAP_Y", location.getLatitude());
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "현재 위치를 얻지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onPermissionDenied(List<String> deniedPermissions) {
        Toast.makeText(this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
    }
}