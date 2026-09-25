package com.transnacala.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.transnacala.app.database.DatabaseSeeder;

public class MainActivity extends AppCompatActivity {

    private Button btnRotas;
    private Button btnAdmin;

    // Palavra-passe definida para acesso do administrador
    private static final String ADMIN_PIN = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Garante a semeadura condicional de dados na primeira execução
        DatabaseSeeder.inserirDadosIniciais(this);

        btnRotas = findViewById(R.id.btnRotas);
        btnAdmin = findViewById(R.id.btnAdmin);

        // Fluxo para passageiro/utilizador comum
        btnRotas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RotasActivity.class);
            startActivity(intent);
        });

        // Fluxo para acesso do administrador
        btnAdmin.setOnClickListener(v -> solicitarAcessoAdmin());
    }

    private void solicitarAcessoAdmin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acesso Restrito");
        builder.setMessage("Digite a palavra-passe do Administrador:");

        // Campo para digitar o PIN
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("Entrar", (dialog, which) -> {
            String pinDigitado = input.getText().toString().trim();
            if (ADMIN_PIN.equals(pinDigitado)) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Palavra-passe incorreta!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}