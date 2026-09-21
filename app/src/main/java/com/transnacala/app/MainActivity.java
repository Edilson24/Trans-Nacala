package com.transnacala.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.transnacala.app.database.DatabaseSeeder;

public class MainActivity extends AppCompatActivity {

    private Button btnRotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DatabaseSeeder.inserirDadosIniciais(this);

        btnRotas = findViewById(R.id.btnRotas);

        btnRotas.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MainActivity.this, RotasActivity.class);

            startActivity(intent);
        });
    }
}