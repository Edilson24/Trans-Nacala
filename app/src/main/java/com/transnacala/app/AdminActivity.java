package com.transnacala.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        MaterialButton btnGerirRotas = findViewById(R.id.btnGerirRotas);
        MaterialButton btnGerirParagens = findViewById(R.id.btnGerirParagens);

        btnGerirRotas.setOnClickListener(v ->
                startActivity(new Intent(AdminActivity.this, GerenciarRotasActivity.class))
        );

        btnGerirParagens.setOnClickListener(v ->
                startActivity(new Intent(AdminActivity.this, GerenciarParagensActivity.class))
        );
    }
}