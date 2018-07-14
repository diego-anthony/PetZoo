package com.petzoo.petzoo.constants;

import android.Manifest;

public final class PermissionConstants {
    public static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    public static final String[] CAMERA_PERMS={
            Manifest.permission.CAMERA
    };
    public static final String[] CONTACTS_PERMS={
            Manifest.permission.READ_CONTACTS
    };
    public static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    public static final int INITIAL_REQUEST=1337;

    public static final int CAMERA_REQUEST=INITIAL_REQUEST+1;
    public static final int CONTACTS_REQUEST=INITIAL_REQUEST+2;
    public static final int LOCATION_REQUEST=INITIAL_REQUEST+3;
}
