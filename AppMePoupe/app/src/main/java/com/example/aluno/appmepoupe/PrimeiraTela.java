package com.example.aluno.appmepoupe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PrimeiraTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);
    }

    public void login(View view)
    {
        Intent intent = new Intent(PrimeiraTela.this, Login.class);
        startActivity(intent);
    }
    public void cadastro(View view)
    {
        Intent intent = new Intent(PrimeiraTela.this, TelaCadastro.class);
        startActivity(intent);
    }

}
