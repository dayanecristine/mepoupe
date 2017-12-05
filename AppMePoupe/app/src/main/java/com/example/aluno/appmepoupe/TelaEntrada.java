package com.example.aluno.appmepoupe;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

public class TelaEntrada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_entrada);

        final Spinner spinnerRecebimento = (Spinner) findViewById(R.id.spinnerRecebimento);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.formaDe, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRecebimento.setAdapter(adapter);

        final Spinner spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        /*
        TextView dataatual = (TextView) findViewById(R.id.dataSaida);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        final String dateString = sdf.format(date);
        dataatual.setText(dateString);
        */
        final String dateString = "4/12/2017";
        Button botao = (Button)findViewById(R.id.btnSalvarEntrada);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoControllerUsuario crud = new BancoControllerUsuario(getBaseContext());
                EditText descricao = (EditText)findViewById(R.id.descEntrada);
                EditText valor = (EditText)findViewById((R.id.valorEntrada));
                EditText qtde = (EditText)findViewById(R.id.parcelasEntrada);
                Switch fixa = (Switch) findViewById(R.id.receitaEntrada);

                String descricaoString = descricao.getText().toString();
                float valorString = Float.valueOf(valor.getText().toString());
                int qtdeString = Integer.valueOf(qtde.getText().toString());
                String formaDeRecString;
                switch(spinnerRecebimento.getSelectedItemPosition()){
                    case 1: formaDeRecString = "Dinheiro";
                        break;
                    case 2: formaDeRecString = "Cartão de Crédito";
                        break;
                    case 3: formaDeRecString = "Cartão de débito";
                        break;
                    case 4: formaDeRecString = "Cheque";
                        break;
                    case 5: formaDeRecString = "Boleto";
                        break;
                    default: formaDeRecString = "Null";
                }
                String categoriaString;
                switch(spinnerCategoria.getSelectedItemPosition()){
                    case 1: categoriaString = "Alimentação";
                        break;
                    case 2: categoriaString = "Vestuário";
                        break;
                    case 3: categoriaString = "Itens pessoais";
                        break;
                    case 4: categoriaString = "Transporte";
                        break;
                    case 5: categoriaString = "Lazer";
                        break;
                    case 6: categoriaString = "Outros";
                        break;
                    default: categoriaString = "Null";
                }
                int fixaString;
                if(fixa.isChecked()){fixaString=1;}
                else{fixaString=0;}
                String resultado;
                if(categoriaString == "Null"){
                    Toast.makeText(TelaEntrada.this, "Você esqueceu de dizer a categoria!", Toast.LENGTH_SHORT).show();
                }
                if(formaDeRecString == "Null"){
                    Toast.makeText(TelaEntrada.this, "Você esqueceu de dizer como recebeu o dinheiro!", Toast.LENGTH_SHORT).show();
                }
                resultado = crud.insereEntrada(descricaoString, valorString, qtdeString, formaDeRecString, categoriaString, fixaString,dateString);
                Entradas e = new Entradas(descricaoString, formaDeRecString, categoriaString, dateString, qtdeString, valorString, fixaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            }
        });


    }/*
    public void salvarEntrada() {
        Toast.makeText(TelaEntrada.this, "Entrada salva com sucesso!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TelaEntrada.this, MenuLateral.class);
        startActivity(intent);
    }*/
}