package com.transnacala.app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.transnacala.app.model.Rota;

public class DetalhesRotaActivity extends AppCompatActivity {

    private TextView txtNomeRota;
    private TextView txtOrigem;
    private TextView txtDestino;
    private TextView txtTarifa;
    private TextView txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_rota);

        txtNomeRota = findViewById(R.id.txtNomeRota);
        txtOrigem = findViewById(R.id.txtOrigem);
        txtDestino = findViewById(R.id.txtDestino);
        txtTarifa = findViewById(R.id.txtTarifa);
        txtDescricao = findViewById(R.id.txtDescricao);

        Rota rota =
                (Rota) getIntent().getSerializableExtra("rota");

        if (rota != null) {

            txtNomeRota.setText(rota.getNome());

            txtOrigem.setText(rota.getOrigem());

            txtDestino.setText(rota.getDestino());

            txtTarifa.setText(
                    String.format("%.0f MT", rota.getTarifa())
            );

            txtDescricao.setText(rota.getDescricao());
        }
    }
}