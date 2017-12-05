package com.example.aluno.appmepoupe;

import java.text.SimpleDateFormat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TelaSaida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_saida);

        Spinner spinnerRecebimento = (Spinner) findViewById(R.id.spinnerPagamentoSaida);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.formaDe, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRecebimento.setAdapter(adapter);

        Spinner spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoriaSaida);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        final String dateString = "4/12/2017";
    }/*
    public void salvarSaida(){
        Toast.makeText(TelaSaida.this, "Saída salva com sucesso!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TelaSaida.this,MenuLateral.class);
        startActivity(intent);
    }*/
}
