package com.petzoo.petzoo.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

import static com.petzoo.petzoo.constants.UbicationConstants.DEFAULT_LATITUDE;
import static com.petzoo.petzoo.constants.UbicationConstants.DEFAULT_LONGITUDE;

public class UserHelper {
    public static LatLng getCurrentLatLng(Activity activity){

        LocationManager locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        @SuppressLint("MissingPermission") Location location = locationManager
                .getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));

        double longitude = DEFAULT_LATITUDE;
        double latitude = DEFAULT_LONGITUDE;
        if(location != null)
        {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        LatLng cd = new LatLng(latitude,longitude);
        return cd;

    }


}
