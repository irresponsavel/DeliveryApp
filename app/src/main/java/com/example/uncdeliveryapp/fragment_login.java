package com.example.uncdeliveryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class fragment_login extends Fragment {

    // criando a variável que irá receber a URL para o 'endpoint'
    private String url = "http://192.168.0.106:3000/users/auth";
    // criando os objetos para os componentes da tela
    private EditText edtEmail, edtPass;
    private  Button btnLogin;
    // criando as variáveis que receberão os valores digitados
    public static String email, token;

    public fragment_login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        // vinculandos os objetos da classe aos componentes do front
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        edtEmail = (EditText) view.findViewById(R.id.edt_email);
        edtPass = (EditText) view.findViewById(R.id.edt_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chamando a função de login
                login();
            }
        });
        return view;
    }

    // criando a função para o login
    public void login() {
        // criando e instanciando um novo objeto da classe JSON
        JSONObject object = new JSONObject();
        // montando o objeto com as informações dos campos da tela
        try {
            object.put("email",edtEmail.getText());
            object.put("senha", edtPass.getText());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        // criando a estrutura para conectar a RST API
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("JSON", String.valueOf(response));
                            String error = response.getJSONObject("data").getString("email");
                            //int statusCode = response.getInt("statusCode");
                            //Toast.makeText(getActivity(), error + "", Toast.LENGTH_SHORT).show();
                            if (error.equals("") || error.equals(null)) {
                                Log.d("", String.valueOf(error));
                                CallMain();
                            } else if (error.equals("OK")) {
                                JSONObject body = response.getJSONObject("body");
                                CallMain();
                            } else {
                                CallMain();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error","Error code: " + error.getMessage());
                Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    public void CallMain(){
        Intent i = new Intent(getActivity(),TelaPrincipal.class);
        i.putExtra("usuario", edtEmail.getText());
        startActivity(i);

    }
}