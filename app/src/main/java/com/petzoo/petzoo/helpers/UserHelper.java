package com.petzoo.petzoo.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

public class UserHelper {
    public static LatLng getCurrentLatLng(Activity activity){

        LocationManager locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        @SuppressLint("MissingPermission") Location location = locationManager
                .getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        LatLng cd = new LatLng(latitude,longitude);
        return cd;

    }


}
