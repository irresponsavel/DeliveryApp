package com.example.uncdeliveryapp;

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


public class fragment_register extends Fragment {

    private Button btn_register;
    private EditText edt_nome;
    private EditText edt_email;
    private EditText edt_senha;
    private String url = "http://192.168.0.106:3000/users/create";

    public fragment_register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_register, container, false);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        edt_nome = (EditText) view.findViewById(R.id.edt_name);
        edt_email = (EditText) view.findViewById(R.id.edt_email);
        edt_senha = (EditText) view.findViewById(R.id.edt_password);

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
//        // criando e instanciando um novo objeto da classe JSON
//        JSONObject object = new JSONObject();
//        // montando o objeto com as informações dos campos da tela
//        try {
//            object.put("email",edt_email.getText());
//            object.put("senha", edt_senha.getText());
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//        // criando a estrutura para conectar a RST API
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.POST,
//                url,
//                object,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
//                        try {
//                            Log.d("JSON", String.valueOf(response));
//                            String error = response.getString("httpStatus");
//                            if (error.equals("") || error.equals(null)) {
//                                Log.d("", String.valueOf(error));
//                            } else if (error.equals("OK")) {
//                                JSONObject body = response.getJSONObject("body");
//                            } else {
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("Error","Error code: " + error.getMessage());
//                Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
        JSONObject object = new JSONObject();

        try
        {
            object.put("nome",edt_nome.getText());
            object.put("email",edt_email.getText());
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
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            Log.d("JSON", String.valueOf(response));
                            String error = response.getString("httpStatus");
                            if (error.equals("") || error.equals(null)) {
                                Log.d("", String.valueOf(error));

                            } else if (error.equals("OK")) {
                                JSONObject body = response.getJSONObject("body");
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        CallMain();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error","Error code: " + error.getMessage());
                Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest1);
    }

    public void CallMain(){
        Intent i = new Intent(getActivity(),TelaPrincipal.class);
        i.putExtra("usuario", edt_email.getText());
        startActivity(i);

    }
}