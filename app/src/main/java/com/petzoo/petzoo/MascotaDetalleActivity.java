package com.petzoo.petzoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.petzoo.petzoo.Service.ApiService;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.constants.ExtrasConstants;
import com.petzoo.petzoo.models.Alerta;
import com.petzoo.petzoo.models.ResponseManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MascotaDetalleActivity extends AppCompatActivity {

    TextView txtName;
    private int idAlert = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_detalle);
        txtName = findViewById(R.id.txtNombre);

        Intent intent = getIntent();
        idAlert = intent.getIntExtra(ExtrasConstants.MakerToAlertDetailIdAlert,0);
        Toast.makeText(this, idAlert+"", Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadData();
    }

    private void loadData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Mascotas");
        progress.setMessage("Cargando datos...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();



        JsonRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, ApiServiceConstants.URL_BASE+"/api/Alerta/"+idAlert, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Alerta alerta = (gson.fromJson(response.toString(), Alerta.class));
                        progress.dismiss();
                        Toast.makeText(MascotaDetalleActivity.this, alerta.getDescripcion().toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MascotaDetalleActivity.this, "No se ha podido completar la petición", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
        queue.add(jsonObjectRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
