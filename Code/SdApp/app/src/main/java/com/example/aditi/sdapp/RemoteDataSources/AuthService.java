package com.example.aditi.sdapp.RemoteDataSources;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aditi.sdapp.Persistence.Entities.User;

import org.json.JSONObject;

/**
 * Created by aditi on 16/05/2018.
 */

public class AuthService {

    private static AuthService instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private final String baseUrl = "http://192.168.0.220:8080/auth";

    private final String loginUrl = baseUrl + "/login";
    private final String registerUrl = baseUrl + "/register";

    private AuthService(Context context){

        this.ctx = context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue(){

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;

    }

    public static synchronized AuthService getInstance(Context context){
        if(instance == null){
            instance = new AuthService(context);
        }
        return instance;
    }

    public void login(User user){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, loginUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

            // See where to place this information ?????????????

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // Error while receiving the request
                // Display error message

            }

        });

        getRequestQueue().add(jsonObjectRequest);

    }

    public void register(User user){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, registerUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                // If register is successful, go to the logged page

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // Error
                // Display error message

            }

        });

        getRequestQueue().add(jsonObjectRequest);

    }

}
