package com.example.aluno.appmepoupe;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by julli on 19/11/2017.
 */

public class BancoControllerUsuario {
    private SQLiteDatabase db;
    private DBHelper banco;

    public BancoControllerUsuario(Context context){
        banco = new DBHelper(context);
    }

    public String insereDado(String nome, String email, String senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBHelper.COLUMN_NOME, nome);
        valores.put(DBHelper.COLUMN_EMAIL, email);
        valores.put(DBHelper.COLUMN_SENHA, senha);

        resultado = db.insert(DBHelper.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Cadastrado com sucesso";
    }



    public Usuario getUsuario(){
        Usuario u = new Usuario();
        Cursor cursor = banco.getWritableDatabase().rawQuery("SELECT *FROM "+banco.TABELA+" ORDER BY ID DESC",null);

        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String emailusuario = cursor.getString(cursor.getColumnIndex("email"));
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
            cursor.close();
            return new Usuario(id, nome, emailusuario, senha);
        }

        return null;
    }


    public String insereEntrada(String descricao, Float valor, int qtde, String formaDe, String cat, int fixa, String data){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBHelper.COLUMN_DESCRICAO, descricao);
        valores.put(DBHelper.COLUMN_VALOR, valor);
        valores.put(DBHelper.COLUMN_QTDE, qtde);
        valores.put(DBHelper.COLUMN_RECEBIMENTO, formaDe);
        valores.put(DBHelper.COLUMN_CATEGORIA, cat);
        valores.put(DBHelper.COLUMN_FIXA, fixa);
        valores.put(DBHelper.COLUMN_DATA, data);

        resultado = db.insert(DBHelper.TABELA_ENTRADAS, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Sua entrada está salva!";
    }

}
