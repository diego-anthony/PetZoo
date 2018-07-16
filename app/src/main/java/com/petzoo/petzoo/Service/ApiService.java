package com.petzoo.petzoo.Service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.petzoo.petzoo.models.ResponseManager;

import org.json.JSONArray;

public class ApiService<T> implements Response.Listener,Response.ErrorListener {
    private Context context;
    private ResponseManager responseManager;

    public ApiService(Context context) {
        this.context = context;
    }

    public ResponseManager getList (String urlBase, String servicePrefix, String controller)
    {
        final ResponseManager responseManager = null;
        try{
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = urlBase+servicePrefix+controller;
            final JSONArray getRequestReturn = new JSONArray();
            JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            ResponseManager responseM = new ResponseManager(true,response);
                            getResponse(responseM);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ResponseManager response = new ResponseManager(false,error.getMessage());
                    getResponse(response);
                }
            });
            queue.add(stringRequest);
        }
        catch (Exception ex){

        }
        return responseManager;
    }
    public void getResponse(ResponseManager responseManager){

    }

    @Override
    public void onResponse(Object response) {
        responseManager = new ResponseManager(true,response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
