package com.example.aluno.appmepoupe;

/**
 * Created by julli on 18/11/2017.
 */

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(){}

    public Usuario(String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;

    }

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }


}
