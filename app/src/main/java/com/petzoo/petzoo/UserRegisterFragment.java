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
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.constants.UserConstants;
import com.petzoo.petzoo.helpers.Msg;
import com.petzoo.petzoo.helpers.Validator;
import com.petzoo.petzoo.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    Usuario ValidarUsuario()
    {
        LimpiarError();

        Boolean esValido = true;
        Usuario usuario = getUsuario();
        EditText txt = null;

        if(usuario.getPassword().equals(""))
        {
            esValido = false;
            _txtContrasena.setError("La contraseña es requerida");
            txt = _txtContrasena;
        }
        else if(!usuario.getPassword().equals(_txtRepetirContrasena.getText().toString()))
        {
            esValido = false;
            _txtRepetirContrasena.setError("Las contraseñas no coinciden");
            txt = _txtRepetirContrasena;
        }

        if(usuario.getCorreoElectronico().equals(""))
        {
            esValido = false;
            _txtCorreo.setError("El nombre es requerido");
            txt = _txtCorreo;
        }
        else if(!Validator.isValidEmail(usuario.getCorreoElectronico()))
        {
            esValido = false;
            _txtCorreo.setError("El correo no es válido");
            txt = _txtCorreo;
        }

        if(!usuario.getWeb().equals("") && !Validator.isValidURL(usuario.getWeb()))
        {
            esValido = false;
            _txtWeb.setError("La web no es válida");
            txt = _txtWeb;
        }

        if(usuario.getCelular().equals(""))
        {
            esValido = false;
            _txtCelular.setError("El número de celular es requerido");
            txt = _txtCelular;
        }

        if(usuario.getNombre().equals(""))
        {
            esValido = false;
            _txtNombre.setError("El nombre es requerido");
            txt = _txtNombre;
        }

        if(!esValido)
        {
            usuario = null;
            txt.requestFocus();
        }

        return usuario;
    }

    void LimpiarError()
    {
        _txtNombre.setError(null);
        _txtCorreo.setError(null);
        _txtContrasena.setError(null);
        _txtWeb.setError(null);
        _txtCelular.setError(null);
    }


    void LimpiarTxts()
    {
        _txtNombre.setText("");
        _txtApellidoPaterno.setText("");
        _txtApellidoMaterno.setText("");
        _txtCelular.setText("");
        _txtTelefono.setText("");
        _txtWeb.setText("");
        _txtDescripcion.setText("");
        _txtCorreo.setText("");
        _txtContrasena.setText("");
        _txtRepetirContrasena.setText("");
    }
    Usuario getUsuario()
    {
        Usuario usuario = new Usuario();

        usuario.setUsername(_txtCorreo.getText().toString().trim());
        usuario.setPassword(_txtContrasena.getText().toString());
        usuario.setEstado(true);

        usuario.setNombre(_txtNombre.getText().toString().trim());
        usuario.setApellidoPaterno(_txtApellidoPaterno.getText().toString().trim());
        usuario.setApellidoMaterno(_txtApellidoMaterno.getText().toString().trim());
        usuario.setCelular(_txtCelular.getText().toString().trim());
        usuario.setTelefono( _txtTelefono.getText().toString().trim());
        usuario.setDireccion("");
        usuario.setFechaNacimiento(null);
        usuario.setCorreoElectronico(_txtCorreo.getText().toString().trim());
        usuario.setWeb(_txtWeb.getText().toString().trim());
        usuario.setPresentacion(_txtDescripcion.getText().toString().trim());
        usuario.setTipoPersona(_codTipoPersona);
        usuario.setIdDistrito(UserConstants.ID_DISTRITO_DEFAULT);
        usuario.setDireccion("");
        usuario.setFechaNacimiento(null);
        return usuario;
    }

    private void RegistrarUsuario() {
        try {

            Usuario usuarioEvaluado = ValidarUsuario();

            if(usuarioEvaluado!=null)
            {
                JSONObject persona = new JSONObject();
                JSONObject usuario = new JSONObject();
                JSONArray arrayUsuario = new JSONArray();

                usuario.put("Username",usuarioEvaluado.getCorreoElectronico());
                usuario.put("Password",usuarioEvaluado.getPassword());
                usuario.put("Activo",usuarioEvaluado.getEstado());

                arrayUsuario.put(usuario);

                persona.put("Usuario",arrayUsuario);
                persona.put("Nombre", usuarioEvaluado.getNombre());
                persona.put("ApellidoPaterno",usuarioEvaluado.getApellidoPaterno());
                persona.put("ApellidoMaterno", usuarioEvaluado.getApellidoMaterno());
                persona.put("Celular", usuarioEvaluado.getCelular());
                persona.put("Telefono",usuarioEvaluado.getTelefono());
                persona.put("CorreoElectronico", usuarioEvaluado.getCorreoElectronico());
                persona.put("Web", usuarioEvaluado.getWeb());
                persona.put("TipoPersona", usuarioEvaluado.getTipoPersona());
                persona.put("Presentacion", usuarioEvaluado.getPresentacion());
                persona.put("IdDistrito", usuarioEvaluado.getIdDistrito());
                persona.put("Direccion", usuarioEvaluado.getDireccion());
                persona.put("FechaNacimiento",usuarioEvaluado.getFechaNacimiento());

                postData(ApiServiceConstants.URL_BASE+"/api/Persona",persona);
            }

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
        LimpiarError();
        LimpiarTxts();

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
