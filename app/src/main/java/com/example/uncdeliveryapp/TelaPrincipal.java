package com.example.uncdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        // criando um objeto do tipo 'Bundle' para receber os extras
        Bundle bundle = getIntent().getExtras();
//        String nome = bundle.getString("usuario").toString();
//        Toast.makeText(getApplicationContext(),
//                "Nome: " + nome,
//                Toast.LENGTH_LONG).show();
    }
}