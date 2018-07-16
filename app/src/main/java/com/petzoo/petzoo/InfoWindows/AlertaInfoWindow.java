package com.petzoo.petzoo.InfoWindows;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.petzoo.petzoo.R;

public class AlertaInfoWindow implements GoogleMap.InfoWindowAdapter {
    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;

    public AlertaInfoWindow(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        //Carga layout personalizado.
        View v = inflater.inflate(R.layout.alerta_info_window, null);
        ((TextView)v.findViewById(R.id.info_window_nombre)).setText(marker.getSnippet());
        ((TextView)v.findViewById(R.id.info_window_placas)).setText(marker.getTitle());
        ((TextView)v.findViewById(R.id.info_window_estado)).setText("21/05/2013");
        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
