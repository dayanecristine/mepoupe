package com.example.aluno.appmepoupe;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class esquecerSenha extends AppCompatActivity {


    DBHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esquecer_senha);

        final EditText email = (EditText) findViewById(R.id.inputEmail);
        Button enviarsenha = (Button) findViewById(R.id.btnEnviarSenha);
        dbhelper = new DBHelper(this);
        db = dbhelper.getReadableDatabase();

        viewAll();

    }

    public void viewAll() {
        final DBHelper bd = new DBHelper(this);
        Button btnEnviarSenha = (Button)findViewById(R.id.btnEnviarSenha);
        btnEnviarSenha.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = bd.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Erro","Email não cadastrado");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Email :"+ res.getString(2)+"\n");
                            buffer.append("Senha :"+ res.getString(3)+"\n\n");
                        }

                        showMessage("Recuperação de senha",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
