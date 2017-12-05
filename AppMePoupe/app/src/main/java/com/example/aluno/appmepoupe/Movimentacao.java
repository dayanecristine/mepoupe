package com.example.aluno.appmepoupe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Movimentacao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<String> descricoes;


    //VARIAVEIS E METODOS PARA MUDAR TELA
    int posicao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimentacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        inicializarVariaveis();
        configurarBotoes();

    }

    public void inicializarVariaveis(){
        descricoes = new ArrayList<String>();
        String descricao;
        int i=0;
        for(i=0;i<5;i++){
            descricao = ""+i;
            descricoes.add(descricao);
        }
        posicao = 0;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movimentacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void configurarBotoes(){
        Button anterior = (Button) findViewById(R.id.retroceder);
        Button proximo = (Button) findViewById(R.id.avançar);

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao--;
                atualizarLista();
            }
        });
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao++;
                atualizarLista();
            }
        });
    }

    public void atualizarLista(){
        TextView descricao = (TextView) findViewById(R.id.movimentacao_descricao);
        descricao.setText(descricoes.get(posicao));

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            telaHome();
        }
        else if (id == R.id.conta) {
            telaAlterarConta();
        }
        else if (id == R.id.grafico) {
            telaGrafico();
        }

        /*
        else if (id == R.id.menu_movimentacao) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void telaHome(){
        Intent intent = new Intent(this,MenuLateral.class);
        startActivity(intent);
    }

    public void telaAlterarConta() {
        Intent intent = new Intent(this, ContaUsuario.class);
        startActivity(intent);

    }

    public void telaGrafico() {
        Intent intent = new Intent(this, TelaGrafico.class);
        startActivity(intent);

    }
}
