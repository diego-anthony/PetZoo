package com.petzoo.petzoo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.constants.MapContants;
import com.petzoo.petzoo.constants.PermissionConstants;
import com.petzoo.petzoo.constants.UbicationConstants;
import com.petzoo.petzoo.helpers.UserHelper;
import com.petzoo.petzoo.models.Alerta;
import com.petzoo.petzoo.models.Mascota;
import com.petzoo.petzoo.models.ResponseManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MapView mMapView;
    private GoogleMap googleMap;

    private OnFragmentInteractionListener mListener;

    public MapsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        mMapView = rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                configureMap(mMap);
            }
        });

        return rootView;
    }

    private void configureMap(GoogleMap mMap){
        googleMap = mMap;
        // For showing a move to my location button
        LatLng latLng = getCurrentLocationUser();
        // For dropping a marker at a point on the Map
        loadData(googleMap);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(MapContants.ZOOM_LEVEL).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void loadData(final GoogleMap googleMap) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Mascotas");
        progress.setMessage("Cargando datos...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, ApiServiceConstants.URL_BASE+"/api/Alerta", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            Gson gson = new Gson();
                            List<Alerta> alerta = Arrays.asList(gson.fromJson(response.toString(), Alerta[].class));
                            loadMarkers(alerta);
                        }
                        progress.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ResponseManager response = new ResponseManager(false,error.getMessage());
                progress.dismiss();
            }
        });
        queue.add(stringRequest);
    }

    private void loadMarkers(List<Alerta> alertas) {
        googleMap.clear();
        for(Alerta alerta: alertas){
            LatLng position = new LatLng(alerta.getLatitud(), alerta.getLongitud());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(position)
                    .title(alerta.getDescripcion());
            //Marker s = new Marker();
            googleMap.addMarker(markerOptions);
        }
    }


    private LatLng getCurrentLocationUser(){
        LatLng result;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            double latitude = UbicationConstants.DEFAULT_LATITUDE;
            double longitude = UbicationConstants.DEFAULT_LONGITUDE;
            requestPermissions(PermissionConstants.LOCATION_PERMS,PermissionConstants.LOCATION_REQUEST);
            result = new LatLng(latitude,longitude);
        }
        else
        {
            googleMap.setMyLocationEnabled(true);
            result = UserHelper.getCurrentLatLng(getActivity());
        }
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PermissionConstants.LOCATION_REQUEST:
                if (canAccessLocation()){
                    googleMap.clear();
                    configureMap(googleMap);
                }
        }
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==ActivityCompat.checkSelfPermission(getContext(),perm));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
