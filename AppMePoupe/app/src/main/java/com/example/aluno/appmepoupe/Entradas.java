package com.example.aluno.appmepoupe;

/**
 * Created by Dayane on 27/11/2017.
 */

public class Entradas {
    public int id;
    public String descricao;
    public String recebimento;
    public String categoria;
    public String data;
    public int quantidade;
    public float valor;
    public int fixa;

    public Entradas() {
    }

    public Entradas(String descricao, String recebimento, String categoria, String data, int quantidade, float valor, int fixa) {
        this.descricao = descricao;
        this.recebimento = recebimento;
        this.categoria = categoria;
        this.data = data;
        this.quantidade = quantidade;
        this.valor = valor;
        this.fixa = fixa;
    }
    public int getFixa() {
        return fixa;
    }

    public void setFixa(int fixa) {
        this.fixa = fixa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(String recebimento) {
        this.recebimento = recebimento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
