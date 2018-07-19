package com.petzoo.petzoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.helpers.Msg;
import com.petzoo.petzoo.helpers.PreferencesHelper;
import com.petzoo.petzoo.models.Usuario;

import org.json.JSONObject;


public class LoginFragment extends Fragment implements View.OnClickListener{

    PreferencesHelper _prefHelper;
    Button _btnRegister;
    Button _btnLogin;
    EditText _txtUsername, _txtPassword;
    CheckBox _chkRemenberMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        _prefHelper = new PreferencesHelper(getContext());

        _txtPassword = view.findViewById(R.id.txtPassword_login);
        _txtUsername = view.findViewById(R.id.txtUsername_login);
        _chkRemenberMe = view.findViewById(R.id.chkRemenberMe_login);
        _btnRegister = view.findViewById(R.id.btnRegister_login);
        _btnLogin = view.findViewById(R.id.btnLogin_login);

        _btnRegister.setOnClickListener(this);
        _btnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnRegister_login:
                AttachFragment(new UserRegisterFragment());
                break;
            case R.id.btnLogin_login:
                ValidUser();
                break;
        }
    }

    void IntentMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    void ClearErrors() {
        _txtUsername.setError(null);
        _txtPassword.setError(null);
    }

    void AttachFragment(Fragment fragment) {
        if(getActivity()!=null)
        {
            FragmentTransaction tns = getActivity().getSupportFragmentManager()
                    .beginTransaction();
            tns.replace(R.id.div_login,fragment);
            tns.addToBackStack(null);
            tns.commit();
        }
    }

    void ValidUser()
    {
        ClearErrors();

        boolean isValid = true;

        Usuario user = new Usuario(
                0,
                _txtUsername.getText().toString().trim(),
                _txtPassword.getText().toString()
        );

        if (user.getUsername().equals("")) {
            isValid = false;
            _txtUsername.setError("El usuario es requerido");
            _txtUsername.requestFocus();
        }

        if (user.getPassword().equals("")) {
            isValid = false;
            _txtPassword.setError("La contraseña es requerida");
            _txtPassword.requestFocus();

        }

        if(isValid )
        {
            RequestQueue queue = Volley.newRequestQueue(getContext());
            String url = ApiServiceConstants.URL_BASE+"/api/validate?username="+user.getUsername()+"&password="+user.getPassword();
            JsonRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Gson gson = new Gson();
                            Usuario usuario = gson.fromJson(response.toString(),Usuario.class);
                            //showProgress(false);
                            if(usuario!=null && usuario.getIdUsuario() > 0)
                            {
                                if(_chkRemenberMe.isChecked())
                                {
                                    _prefHelper.putIsLogin(true);
                                }
                                _prefHelper.putName(usuario.getNombre());
                                _prefHelper.putEmail(usuario.getCorreoElectronico());
                                _prefHelper.putIdPersona(usuario.getIdPersona());
                                IntentMain();
                            }
                            else {
                                _txtUsername.setError("Usuario o contraseña es incorrecta");
                                _txtUsername.requestFocus();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    //showProgress(false);
                    Msg.Show(getContext(),"No se pudo completar la petición.");
                    // progress.dismiss();
                }
            });
            queue.add(jsonObjectRequest);
        }
    }


}
