package com.example.aluno.appmepoupe;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email, senha;
    Button login;
    DBHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email = (EditText) findViewById(R.id.loginEmail);
        final EditText senha = (EditText) findViewById(R.id.loginSenha);
        Button login = (Button) findViewById(R.id.btnEntrarLogin);

        dbhelper = new DBHelper(this);
        db = dbhelper.getReadableDatabase();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailusuario = email.getText().toString();
                String senhausuario = senha.getText().toString();

                cursor = db.rawQuery("SELECT *FROM "+dbhelper.TABELA+" WHERE "+dbhelper.COLUMN_EMAIL+"=? AND "+dbhelper.COLUMN_SENHA+"=?",new String[] {emailusuario,senhausuario});
                if (cursor != null) {
                    if(cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        Toast.makeText(Login.this, "Bem vindo (a)!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,MenuLateral.class);
                        startActivity(intent);
                        finish();
                    }
                    else {

                        //I am showing Alert Dialog Box here for alerting user about wrong credentials
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setTitle("Alert");
                        builder.setMessage("Email ou senha errados");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //-------Alert Dialog Code Snippet End Here
                    }
                }

            }
        });
    }

    public void esqueciSenha(View view)
    {
        Intent intent = new Intent(Login.this, esquecerSenha.class);
        startActivity(intent);
    }
}
