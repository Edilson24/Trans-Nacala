package com.transnacala.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.transnacala.app.model.Rota;

public class DatabaseSeeder {

    public static void inserirDadosIniciais(Context context) {

        RotaDAO rotaDAO = new RotaDAO(context);

        if (!rotaDAO.listarTodas().isEmpty()) {
            return;
        }

        rotaDAO.inserir(
                new Rota(
                        "Baixa → Alta",
                        "Baixa",
                        "Alta",
                        15,
                        "Percurso entre a Baixa e a Alta."
                )
        );

        rotaDAO.inserir(
                new Rota(
                        "Baixa → Matibane",
                        "Baixa",
                        "Matibane",
                        20,
                        "Percurso entre a Baixa e Matibane."
                )
        );

        rotaDAO.inserir(
                new Rota(
                        "Alta → Fernão Veloso",
                        "Alta",
                        "Fernão Veloso",
                        15,
                        "Percurso entre a Alta e Fernão Veloso."
                )
        );

        rotaDAO.inserir(
                new Rota(
                        "Fernão Veloso → Juma",
                        "Fernão Veloso",
                        "Juma",
                        20,
                        "Percurso entre Fernão Veloso e Juma."
                )
        );

        rotaDAO.inserir(
                new Rota(
                        "Juma → Nacala-a-Velha",
                        "Juma",
                        "Nacala-a-Velha",
                        25,
                        "Percurso entre Juma e Nacala-a-Velha."
                )
        );
    }
}