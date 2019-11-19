package com.example.black_jack;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected RequestQueue fRequestQueue;
    private VolleyS volley;

    Integer suma = 0;

    String url = "http://ramiro174.com/api/numero";
    String url2 = "http://ramiro174.com/api/enviar/numero";
    String url4 ="http://ramiro174.com/api/borrar/numero";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.uno:
        obtenerNumero();
            break;
            case  R.id.dos:
        enviarnumero();
        break;
            case  R.id.tres:
                SegundoActivity();
                break;
            case R.id.cuatro:
                BorrarNumero();
                break;
        }
    }

    private void SegundoActivity(){
        Intent st= new Intent(this, Resultados.class);
        startActivity(st);
    }

    private void obtenerNumero() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView text = findViewById(R.id.num);
                try {
                    Integer num = response.getInt("numero");
                    suma += num;
                    Toast.makeText(MainActivity.this, suma.toString(), Toast.LENGTH_SHORT).show();
                    if (suma > 21) {
                        Toast.makeText(MainActivity.this, "Perdiste!", Toast.LENGTH_SHORT).show();
                        text.setText("0");
                        suma = 0;
                        return;
                    }

                    text.setText(suma.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });
        fRequestQueue.add(request);

    }

    public void enviarnumero() {
         final JSONObject data = new JSONObject();
        try {
            data.put("nombre", "Marentes");
            data.put("numero", suma);
        } catch (JSONException e) {
            e.printStackTrace();
        }

         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url2, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Toast.makeText(MainActivity.this, "se envio datos", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse1: ", error.toString());
            }
        });
        fRequestQueue.add(jsonObjectRequest);
    }

    private void BorrarNumero(){
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url4, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this,"Se borro correctamente",Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse1: ", error.toString());
            }
        });
        fRequestQueue.add(jsonObjectRequest);
    }


}
