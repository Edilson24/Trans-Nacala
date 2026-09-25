package com.transnacala.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.transnacala.app.adapter.ParagensAdminAdapter;
import com.transnacala.app.database.ParagemDAO;
import com.transnacala.app.model.Paragem;

import java.util.List;

public class GerenciarParagensActivity extends AppCompatActivity {

    private ParagemDAO paragemDAO;
    private RecyclerView recyclerView;
    private ParagensAdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_paragens);

        paragemDAO = new ParagemDAO(this);
        recyclerView = findViewById(R.id.rvParagensAdmin);
        FloatingActionButton fabAdicionar = findViewById(R.id.fabAdicionarParagem);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carregarParagens();

        fabAdicionar.setOnClickListener(v -> abrirDialogFormulario(null));
    }

    private void carregarParagens() {
        List<Paragem> listaParagens = paragemDAO.listarTodas();
        adapter = new ParagensAdminAdapter(listaParagens, new ParagensAdminAdapter.OnParagemClickListener() {
            @Override
            public void onEditar(Paragem paragem) {
                abrirDialogFormulario(paragem);
            }

            @Override
            public void onEliminar(Paragem paragem) {
                confirmarEliminacao(paragem);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void abrirDialogFormulario(Paragem paragemExistente) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_formulario_paragem, null);

        EditText etNome = view.findViewById(R.id.etNomeParagem);
        EditText etLatitude = view.findViewById(R.id.etLatitudeParagem);
        EditText etLongitude = view.findViewById(R.id.etLongitudeParagem);

        boolean isEdicao = (paragemExistente != null);

        if (isEdicao) {
            etNome.setText(paragemExistente.getNome());
            etLatitude.setText(String.valueOf(paragemExistente.getLatitude()));
            etLongitude.setText(String.valueOf(paragemExistente.getLongitude()));
        }

        builder.setTitle(isEdicao ? "Editar Paragem" : "Nova Paragem")
                .setView(view)
                .setPositiveButton("Salvar", (dialog, which) -> {
                    String nome = etNome.getText().toString().trim();
                    String latStr = etLatitude.getText().toString().trim();
                    String longStr = etLongitude.getText().toString().trim();

                    if (nome.isEmpty() || latStr.isEmpty() || longStr.isEmpty()) {
                        Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double latitude = Double.parseDouble(latStr);
                    double longitude = Double.parseDouble(longStr);

                    if (isEdicao) {
                        paragemExistente.setNome(nome);
                        paragemExistente.setLatitude(latitude);
                        paragemExistente.setLongitude(longitude);
                        paragemDAO.atualizar(paragemExistente);
                        Toast.makeText(this, "Paragem atualizada!", Toast.LENGTH_SHORT).show();
                    } else {
                        Paragem novaParagem = new Paragem(nome, latitude, longitude);
                        paragemDAO.inserir(novaParagem);
                        Toast.makeText(this, "Paragem cadastrada!", Toast.LENGTH_SHORT).show();
                    }

                    carregarParagens();
                })
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }

    private void confirmarEliminacao(Paragem paragem) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Paragem")
                .setMessage("Deseja realmente eliminar a paragem " + paragem.getNome() + "?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    paragemDAO.eliminar(paragem.getId());
                    Toast.makeText(this, "Paragem eliminada!", Toast.LENGTH_SHORT).show();
                    carregarParagens();
                })
                .setNegativeButton("Não", null)
                .show();
    }
}