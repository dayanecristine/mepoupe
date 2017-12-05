package com.example.aluno.appmepoupe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        Button botao = (Button)findViewById(R.id.btnCad);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoControllerUsuario crud = new BancoControllerUsuario(getBaseContext());
                EditText nome = (EditText)findViewById(R.id.nomeCad);
                EditText email = (EditText)findViewById((R.id.emailCad));
                EditText senha = (EditText)findViewById(R.id.senhaCad);
                String nomeString = nome.getText().toString();
                String emailString = email.getText().toString();
                String senhaString = senha.getText().toString();
                String resultado;

                resultado = crud.insereDado(nomeString,emailString,senhaString);

                final AlertDialog.Builder builder = new AlertDialog.Builder(TelaCadastro.this);
                builder.setTitle("Uhuu!");
                builder.setMessage("Cadastrado com sucesso !");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        Intent intent = new Intent(TelaCadastro.this, Login.class);
                        startActivity(intent);

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }


}
