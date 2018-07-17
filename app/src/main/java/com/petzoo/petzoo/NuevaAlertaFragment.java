package com.petzoo.petzoo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.petzoo.petzoo.Adapters.ClaseMascotaAdapter;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.helpers.ImageHelper;
import com.petzoo.petzoo.models.AlertaDetalle;
import com.petzoo.petzoo.models.ClaseMascota;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class NuevaAlertaFragment extends Fragment {

    private Button btnAddPhotos;
    private SliderLayout imageAlert;
    private Spinner spnMascotCategory;
    private ScrollView newAlertScrollContainer;
    private ClaseMascota currentClaseMascota;


    public NuevaAlertaFragment() {
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddPhotos = view.findViewById(R.id.btnAddPhotos);
        imageAlert = view.findViewById(R.id.imageAlert);
        spnMascotCategory = view.findViewById(R.id.spnMascotCategory);
        newAlertScrollContainer = view.findViewById(R.id.newAlertScrollContainer);
        btnAddPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        loadMascotCategory();
    }
    private void loadMascotCategory() {
        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Alertas");
        progress.setMessage("Cargando datos...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        JsonArrayRequest jsonObjectRequest= new JsonArrayRequest(Request.Method.GET, ApiServiceConstants.URL_BASE+"/api/ClaseMascota", null,
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
                Toast.makeText(getContext(), currentClaseMascota.getDescripcion(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onStop() {
        imageAlert.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            TextSliderView textSliderView = new TextSliderView(getContext());
            try {
                textSliderView
                        .image(ImageHelper.bitMapToFile(Objects.requireNonNull(getContext()),imageBitmap));
            } catch (IOException e) {
                Toast.makeText(getContext(), "No se ha podido cargar la imagen",
                        Toast.LENGTH_SHORT).show();
            }

            imageAlert.addSlider(textSliderView);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nueva_alerta, container, false);
    }

}
