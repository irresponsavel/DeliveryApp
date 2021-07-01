package com.example.uncdeliveryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class activity_cadastro_transportadora extends AppCompatActivity {
    Button buttonRegister;
    EditText edtTransp;
    EditText edtCidade;
    EditText edtEstado;
    EditText edtCnpj;
    EditText edtRegiao;

    String url = "http://unc-delivery.herokuapp.com/transportadoras/create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_transportadora);


        buttonRegister = (Button)findViewById(R.id.btn_register);
        edtTransp = (EditText)findViewById(R.id.edt_nome);
        edtRegiao = (EditText)findViewById(R.id.edt_login);
        edtCidade = (EditText)findViewById(R.id.edt_Cidade);
        edtEstado = (EditText)findViewById(R.id.edt_senha);
        edtCnpj = (EditText)findViewById(R.id.edt_conf_senha);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();

                try{
                    obj.put("nome", edtTransp.getText());
                    obj.put("regiao", edtRegiao.getText());
                    obj.put("estado", edtEstado.getText());
                    obj.put("cidade", edtCidade.getText());
                    obj.put("cnpj", edtCnpj.getText());
                } catch (JSONException e){
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String str = response.getString("message");
                                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                                } catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

     }
}
