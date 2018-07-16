package com.petzoo.petzoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.petzoo.petzoo.constants.ExtrasConstants;

public class MascotaDetalleActivity extends AppCompatActivity {

    TextView txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_detalle);
        txtName = findViewById(R.id.txtNameMascota);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ExtrasConstants.MakerToAlertDetailIdAlert);
        txtName.setText(name);
    }
}
