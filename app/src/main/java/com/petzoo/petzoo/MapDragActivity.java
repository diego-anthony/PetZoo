package com.petzoo.petzoo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.petzoo.petzoo.constants.MapContants;
import com.petzoo.petzoo.helpers.UserHelper;

public class MapDragActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnAddLocationDrag,btnAddMyLocation;
    private double lat=0,longitude=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_drag);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnAddLocationDrag = findViewById(R.id.btnAddLocationDrag);
        btnAddLocationDrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("latitude",lat);
                intent.putExtra("longitude",longitude);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        btnAddMyLocation = findViewById(R.id.btnAddMyLocation);
        btnAddMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                LatLng latLng = UserHelper.getCurrentLatLng(MapDragActivity.this);

                intent.putExtra("latitude",latLng.latitude);
                intent.putExtra("longitude",latLng.longitude);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker arg0) {
                Log.d("System out", "onMarkerDragStart..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void onMarkerDragEnd(Marker arg0) {
                Log.d("System out", "onMarkerDragEnd..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);
                lat = arg0.getPosition().latitude;
                longitude = arg0.getPosition().longitude;
                mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));
            }

            @Override
            public void onMarkerDrag(Marker arg0) {
                Log.i("System out", "onMarkerDrag...");
            }
        });
        LatLng latLng = UserHelper.getCurrentLatLng(this);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker()).draggable(true);
        mMap.addMarker(markerOptions);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(MapContants.ZOOM_LEVEL).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        lat = latLng.latitude;

        longitude = latLng.longitude;
    }
}
