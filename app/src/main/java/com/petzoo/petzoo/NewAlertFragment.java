package com.petzoo.petzoo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.petzoo.petzoo.Adapters.ClaseMascotaAdapter;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.helpers.PreferencesHelper;
import com.petzoo.petzoo.models.Alerta;
import com.petzoo.petzoo.models.ClaseMascota;

import org.json.JSONArray;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class NewAlertFragment extends Fragment {

    private Button btnRegisterAlert,selectLocation;
    private Spinner spnMascotCategory;
    private ScrollView newAlertScrollContainer;
    private ClaseMascota currentClaseMascota;
    private EditText alertDescription,txtLatitude,txtLongitude;
    private double latitude,longitude;

    public NewAlertFragment() {
    }

    static final int MAP_DRAG_REQUEST = 1484;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spnMascotCategory = view.findViewById(R.id.spnMascotCategory);
        alertDescription = view.findViewById(R.id.txt_description_alert);
        newAlertScrollContainer = view.findViewById(R.id.newAlertScrollContainer);
        btnRegisterAlert = view.findViewById(R.id.btnRegisterAlert);
        selectLocation = view.findViewById(R.id.selectLocation);
        txtLatitude = view.findViewById(R.id.txtLatitud);
        txtLongitude = view.findViewById(R.id.txtLongitud);
        selectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(getContext(),MapDragActivity.class);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, MAP_DRAG_REQUEST);
                }
            }
        });
        btnRegisterAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alerta alerta = getObject();
                if (isValid(alerta)){
                    saveAlert(alerta);
                }
            }
        });
        loadMascotCategory();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == MAP_DRAG_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                latitude = data.getDoubleExtra("latitude",0);
                txtLatitude.setText(latitude+"");
                longitude = data.getDoubleExtra("longitude",0);
                txtLongitude.setText(longitude+"");
            }
        }
    }

    private boolean isValid(Alerta alerta){
        boolean response = false;
        String message= "";
        if (alerta.getDescripcion().equals("")){
            message = "Debe agregar una descripción";
            alertDescription.requestFocus();
        }
        else if (alerta.getLatitud()==0 || alerta.getLongitud() == 0){
            message = "Debe agregar una ubicación en el mapa";
        }
        else{
            response = true;
        }
        if (!response)
        {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
        return response;
    }

    private void saveAlert(final Alerta alerta) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            String url = ApiServiceConstants.URL_BASE+"/api/Alerta";


            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main,new MapsFragment());
                    fragmentTransaction.commit();
                    Toast.makeText(getContext(), "Alerta registrada correctamente", Toast.LENGTH_LONG).show();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ){
                @Override
                public byte[] getBody(){
                    String body = new Gson().toJson(alerta);

                    byte[] response;
                    try {
                        response = body.getBytes();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error al serializar información", Toast.LENGTH_SHORT).show();
                        Log.i("Error",e.getMessage());
                        throw null;
                    }
                    return response;
                };


                @Override
                public String getBodyContentType() {
                    return ApiServiceConstants.JSON_CONTENT_TYPE;
                }
            };


            requestQueue.add(stringRequest);
        }
        catch (Exception e) {
            Toast.makeText(getContext(), "No sa ha podido registrar la alerta", Toast.LENGTH_SHORT).show();
        }
    }

    private Alerta getObject() {
        Alerta alerta = new Alerta();
        alerta.setDescripcion(alertDescription.getText().toString());
        int idPersona = new PreferencesHelper(getContext()).getIdPersona();
        alerta.setIdPersona(idPersona);
        alerta.setIdClaseMascota(currentClaseMascota.getIdClaseMascota());
        alerta.setLatitud(latitude);
        alerta.setLongitud(longitude);
        return alerta;
    }

    private void loadMascotCategory() {
        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Alertas");
        progress.setMessage("Cargando datos...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        String url = ApiServiceConstants.URL_BASE+"/api/ClaseMascota";
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        JsonArrayRequest jsonObjectRequest= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        ClaseMascota[] claseMascotas = (gson.fromJson(response.toString(), ClaseMascota[].class));
                        setClaseMastocaAdapter(claseMascotas);
                        newAlertScrollContainer.setVisibility(View.VISIBLE);
                        progress.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se ha podido completar la petición", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void setClaseMastocaAdapter(ClaseMascota[] claseMascotas){
        final ArrayAdapter<ClaseMascota> adapter = new ClaseMascotaAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                claseMascotas);
        spnMascotCategory.setAdapter(adapter);
        spnMascotCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentClaseMascota = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nueva_alerta, container, false);
    }

}
