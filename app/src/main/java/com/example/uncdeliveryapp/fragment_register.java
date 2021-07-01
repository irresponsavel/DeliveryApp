package com.example.uncdeliveryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class fragment_register extends Fragment {

    private Button btn_register;
    private EditText edt_nome;
    private EditText edt_login;
    private EditText edt_senha;
    private String url = "http://unc-delivery.herokuapp.com/usuarios/create";

    public fragment_register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_register, container, false);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        edt_nome = (EditText) view.findViewById(R.id.edt_nome);
        edt_login = (EditText) view.findViewById(R.id.edt_login);
        edt_senha = (EditText) view.findViewById(R.id.edt_senha);

        btn_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        return view;
    }

    public void register(){

        JSONObject object = new JSONObject();

        try
        {
            object.put("nome",edt_nome.getText());
            object.put("login",edt_login.getText());
            object.put("senha",edt_senha.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(
                Request.Method.POST,
                url,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String message = response.getString("message");
                            if (message.equals("ok")) {
                                CallMain();
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error","Error code: " + error.getLocalizedMessage());
                Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsonObjectRequest1);
    }

    public void CallMain(){
        Intent i = new Intent(getActivity(),TelaPrincipal.class);
        i.putExtra("usuario", edt_login.getText());
        startActivity(i);

    }
}