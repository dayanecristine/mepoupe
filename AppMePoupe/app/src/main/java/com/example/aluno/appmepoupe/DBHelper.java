package com.example.aluno.appmepoupe;

import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DBHelper  extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "MePoupe.db";
    public static final String TABELA = "usuarios";
    public static final String COLUMN_ID =  "id";
    public static final String COLUMN_NOME =  "nome";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_SENHA = "senha";
    public static final String TABELA_ENTRADAS = "entradas";
    public static final String COLUMN_ID_ENTRADA = "identrada";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_RECEBIMENTO = "recebimento";
    public static final String COLUMN_CATEGORIA = "categoria";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_QTDE = "quantidade";
    public static final String COLUMN_VALOR = "valor";
    public static final String COLUMN_FIXA = "fixa";
    private static final int DATABASE_VERSION = 2; // indicate database update

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ENTRADAS);
        onCreate(db);
    }
    /*public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2)
            db.execSQL("ALTER TABLE "+ TABELA +" ADD "+ COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT");
    }
    */
    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTabelaUsuarios =  "CREATE TABLE " + TABELA + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, "+
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_SENHA + " TEXT" + ");";



        String sqlCreateTabelaEntrada =  "CREATE TABLE " + TABELA_ENTRADAS + " (" +
                COLUMN_ID_ENTRADA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATA+ " TEXT, "+
                COLUMN_DESCRICAO+ " TEXT, " +
                COLUMN_VALOR + " TEXT" +
                COLUMN_QTDE+ " TEXT, "+
                COLUMN_RECEBIMENTO + " TEXT, " +
                COLUMN_FIXA+ " TEXT" +");";

        db.execSQL(sqlCreateTabelaUsuarios);
        db.execSQL(sqlCreateTabelaEntrada);

    }
/*
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropTabelaUsuarios = "DROP TABLE usuarios";
        db.execSQL(sqlDropTabelaUsuarios);

        String sqlDropTabelaEntradas = "DROP TABLE entradas";
        db.execSQL(sqlDropTabelaEntradas);

        onCreate(db);
    }

*/
    public boolean insertData(String id, String nome,String email,String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_NOME,nome);
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_SENHA,senha);

        long result = db.insert(TABELA,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public boolean insertEntrada(String id, String data,String descricao,String valor, String qtde, String recebimento, String categoria, String fixa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID_ENTRADA,id);
        contentValues.put(COLUMN_DATA,data);
        contentValues.put(COLUMN_DESCRICAO,descricao);
        contentValues.put(COLUMN_VALOR,valor);
        contentValues.put(COLUMN_QTDE,qtde);
        contentValues.put(COLUMN_RECEBIMENTO,recebimento);
        contentValues.put(COLUMN_CATEGORIA,categoria);
        contentValues.put(COLUMN_FIXA,fixa);


        long result = db.insert(TABELA,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Integer deletarUsuario () {
        SQLiteDatabase db = this.getWritableDatabase();
        Usuario u = new Usuario();
        return db.delete(DBHelper.TABELA, DBHelper.COLUMN_NOME+"="+u.getNome(),null);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABELA,null);
        return res;
    }

    public boolean updateData(String id,String nome,String email,String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_NOME,nome);
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_SENHA,senha);
        db.update(TABELA, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public void insertEntrada(Entradas entradas){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("descricao", entradas.getDescricao());
        cv.put("valor", entradas.getValor());
        cv.put("quantidade", entradas.getQuantidade());
        cv.put("recebimento", entradas.getRecebimento());
        cv.put("categoria", entradas.getCategoria());
        cv.put("fixa", entradas.getFixa());
        cv.put("data", entradas.getData());


        db.insert("entradas", null, cv);

        db.close();
    }
}
