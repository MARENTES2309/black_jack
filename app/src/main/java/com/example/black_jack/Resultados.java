package com.example.black_jack;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    protected RequestQueue fRequestQueue;
    private VolleyS volley;

    RecyclerView recycleViewPersona;
    String url3 = "http://ramiro174.com/api/obtener/numero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_black_jack);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
        ObtenerDatos();

    }

    public void ObtenerDatos(){
        JsonObjectRequest datos = new JsonObjectRequest(Request.Method.GET,url3, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    ArrayList<Persona> listapersona;
                    Type talp = new TypeToken<ArrayList<Persona>>(){}.getType();
                    String p=response.getString("resultados");
                    listapersona=new Gson().fromJson(p,talp);
                    RecycleAdaptor ap = new RecycleAdaptor(listapersona);
                    RecyclerView milista = findViewById(R.id.recycleresultados);
                    milista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                    milista.setAdapter(ap);

                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse2: ", error.toString());
            }
        });
        fRequestQueue.add(datos);
    }

}
