package com.petzoo.petzoo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.constants.UserConstants;
import com.petzoo.petzoo.helpers.Msg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class UserRegisterFragment extends Fragment implements View.OnClickListener  {


    String _codTipoPersona = UserConstants.COD_INDIVIDUAL;

    Button _btnSoyCasaProtectora, _btnSoyIndividual,_btnRegistrarUsuario;

    EditText _txtNombre, _txtApellidoPaterno, _txtApellidoMaterno,
            _txtCelular, _txtTelefono, _txtWeb, _txtDescripcion,
            _txtCorreo, _txtContrasena, _txtRepetirContrasena;

    LinearLayout _divApellidos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_register, container, false);

        if(getActivity()!=null)
        {
            getActivity().setTitle("Registro de usuario");

            _divApellidos = view.findViewById(R.id.divApellidos);

            _txtNombre = view.findViewById(R.id.txtNombre_register);
            _txtApellidoPaterno = view.findViewById(R.id.txtApellidoPaterno_register);
            _txtApellidoMaterno = view.findViewById(R.id.txtApellidoMaterno_register);
            _txtCelular = view.findViewById(R.id.txtCelular_register);
            _txtTelefono = view.findViewById(R.id.txtTelefono_register);
            _txtWeb = view.findViewById(R.id.txtWeb_register);
            _txtDescripcion = view.findViewById(R.id.txtDescripcion_register);
            _txtCorreo = view.findViewById(R.id.txtCorreo_register);
            _txtContrasena = view.findViewById(R.id.txtContrasena_register);
            _txtRepetirContrasena = view.findViewById(R.id.txtRepetirContrasena_register);

            _btnSoyIndividual = view.findViewById(R.id.btnSoyIndividual);
            _btnSoyCasaProtectora = view.findViewById(R.id.btnSoyCasaProtectora);
            _btnRegistrarUsuario = view.findViewById(R.id.btnRegistrarUsuario);

            _btnSoyIndividual.setOnClickListener(this);
            _btnSoyCasaProtectora.setOnClickListener(this);
            _btnRegistrarUsuario.setOnClickListener(this);

        }

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnSoyCasaProtectora:
                    CambiarForm(UserConstants.COD_CASA_PROTECTORA);
                break;
            case R.id.btnSoyIndividual:
                CambiarForm(UserConstants.COD_INDIVIDUAL);
                break;
            case R.id.btnRegistrarUsuario:
                RegistrarUsuario();
                break;
        }

    }

    private void RegistrarUsuario() {
        try {

            JSONObject persona = new JSONObject();
            JSONObject usuario = new JSONObject();
            JSONArray arrayUsuario = new JSONArray();

            persona.put("Nombre", _txtNombre.getText().toString());
            persona.put("ApellidoPaterno", _txtApellidoPaterno.getText().toString());
            persona.put("ApellidoMaterno", _txtApellidoMaterno.getText().toString());
            persona.put("Celular", _txtCelular.getText().toString());
            persona.put("Telefono", _txtTelefono.getText().toString());
            persona.put("CorreoElectronico", _txtCorreo.getText().toString());
            persona.put("Web", _txtWeb.getText().toString());
            persona.put("TipoPersona", _codTipoPersona);
            persona.put("Presentacion", _txtDescripcion.getText().toString());
            persona.put("IdDistrito", UserConstants.ID_DISTRITO_DEFAULT);
            persona.put("Direccion", "");
            persona.put("FechaNacimiento",null);

           // usuario.put("Username",_txtCorreo.getText().toString());
            //usuario.put("Password",_txtContrasena.getText().toString());

            //arrayUsuario.put(usuario);

           // persona.put("Usuario",arrayUsuario);

            postData(ApiServiceConstants.URL_BASE+"/api/Persona",persona);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void postData(String url,JSONObject data){
        RequestQueue requstQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Msg.Show(getContext(),"success");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Msg.Show(getContext(),"error "+error.getMessage());
                    }
                }
        );
        requstQueue.add(jsonobj);

    }

    void CambiarForm(String codTipoPersona)
    {
        _codTipoPersona = codTipoPersona;

        if(_codTipoPersona == UserConstants.COD_CASA_PROTECTORA)
        {
            ToggleEstiloBoton(R.id.btnSoyCasaProtectora);
            _divApellidos.setVisibility(View.GONE);
        }
        else if(_codTipoPersona == UserConstants.COD_INDIVIDUAL)
        {
            ToggleEstiloBoton(R.id.btnSoyIndividual);
            _txtApellidoPaterno.setText("");
            _txtApellidoMaterno.setText("");
            _divApellidos.setVisibility(View.VISIBLE);
        }
    }

    void ToggleEstiloBoton(int idBtn) {

        if(R.id.btnSoyIndividual == idBtn)
        {
            _btnSoyCasaProtectora.setTextColor(Color.BLACK);
            _btnSoyCasaProtectora.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.smoke));

            _btnSoyIndividual.setTextColor(Color.WHITE);
            _btnSoyIndividual.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.accent));
        }else if(R.id.btnSoyCasaProtectora == idBtn)
        {
            _btnSoyIndividual.setTextColor(Color.BLACK);
            _btnSoyIndividual.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.smoke));

            _btnSoyCasaProtectora.setTextColor(Color.WHITE);
            _btnSoyCasaProtectora.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.accent));

        }
    }


}
