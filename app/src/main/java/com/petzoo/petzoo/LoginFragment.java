package com.petzoo.petzoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.petzoo.petzoo.constants.UserConstants;
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

        if(getActivity()!=null)
        {
            getActivity().setTitle("Login - Petzoo");

            _prefHelper = new PreferencesHelper(getContext());

            _txtPassword = view.findViewById(R.id.txtPassword_login);
            _txtUsername = view.findViewById(R.id.txtUsername_login);
            _chkRemenberMe = view.findViewById(R.id.chkRemenberMe_login);
            _btnRegister = view.findViewById(R.id.btnRegister_login);
            _btnLogin = view.findViewById(R.id.btnLogin_login);

            _btnRegister.setOnClickListener(this);
            _btnLogin.setOnClickListener(this);

            getBundle(this.getArguments());
        }
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
                LoginUser();
                break;
        }
    }

    void IntentMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    void getBundle(Bundle data)
    {
        if(data!=null)
        {
            _txtUsername.setText(data.getString(UserConstants.KEY_CORREO,""));
            _chkRemenberMe.setChecked(true);
            _txtPassword.requestFocus();
        }
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

    Usuario getUsuarioLogin()
    {
        return new Usuario(
                0,
                _txtUsername.getText().toString().trim(),
                _txtPassword.getText().toString()
        );
    }

    void LoginUser()
    {
        ClearErrors();

        boolean isValid = true;



        Usuario user = getUsuarioLogin();

        if (user.getPassword().equals("")) {
            isValid = false;
            _txtPassword.setError("La contraseña es requerida");
            _txtPassword.requestFocus();

        }

        if (user.getUsername().equals("")) {
            isValid = false;
            _txtUsername.setError("El usuario es requerido");
            _txtUsername.requestFocus();
        }


        try {
            if(isValid )
            {
                RequestQueue queue = Volley.newRequestQueue(getContext());

                final ProgressDialog pd = new ProgressDialog(getContext());
                pd.setTitle("Login");
                pd.setMessage("Validando usuario...");
                pd.setCancelable(false);
                pd.show();

                String url = ApiServiceConstants.URL_BASE+"/api/validate?username="+user.getUsername()+"&password="+user.getPassword();
                JsonRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Gson gson = new Gson();
                                Usuario usuario = gson.fromJson(response.toString(),Usuario.class);
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
                                pd.dismiss();
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Msg.Show(getContext(),"No se pudo completar la petición.");
                        pd.dismiss();
                    }
                });
                queue.add(jsonObjectRequest);
            }
        }
        catch (Exception e)
        {
            Log.e("Error",e.getMessage());
        }

    }


}
