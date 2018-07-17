package com.petzoo.petzoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.constants.ExtrasConstants;
import com.petzoo.petzoo.models.AlertaDetalle;

import org.json.JSONObject;

import java.util.Objects;

public class MascotaDetalleActivity extends AppCompatActivity {

    TextView txt_descripcion, txt_clase_mascota,txt_persona_reporta,txt_estado_alerta;
    LinearLayout container;
    private int idAlert = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_detalle);
        txt_descripcion = findViewById(R.id.txt_descripcion);
        txt_clase_mascota = findViewById(R.id.txt_clase_mascota);
        txt_persona_reporta = findViewById(R.id.txt_persona_reporta);
        txt_estado_alerta = findViewById(R.id.txt_estado_alerta);
        container = findViewById(R.id.containerAlertDetail);


        Intent intent = getIntent();
        idAlert = intent.getIntExtra(ExtrasConstants.MakerToAlertDetailIdAlert,0);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        loadData();
    }

    private void loadData() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Alertas");
        progress.setMessage("Cargando datos...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, ApiServiceConstants.URL_BASE+"/api/get_alert_detail?id="+idAlert, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        AlertaDetalle alerta = (gson.fromJson(response.toString(), AlertaDetalle.class));
                        txt_descripcion.setText(alerta.getDescripcion());
                        txt_clase_mascota.setText(alerta.getClaseMascota());
                        txt_persona_reporta.setText(alerta.getNombrePersona());
                        txt_estado_alerta.setText(alerta.getEstadoAlerta());
                        container.setVisibility(View.VISIBLE);
                        progress.dismiss();

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
