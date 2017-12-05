package com.example.aluno.appmepoupe;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ContaUsuario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public EditText nome;
    public EditText email;
    public EditText senha;
    public EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        id = (EditText) findViewById(R.id.idAtualizarConta);
        nome = (EditText) findViewById(R.id.atualizarNome);
        email = (EditText) findViewById(R.id.atualizarEmail);
        senha = (EditText) findViewById(R.id.atualizarSenha);

        Usuario u = new BancoControllerUsuario(getApplicationContext()).getUsuario();

        id.setText(String.valueOf(u.getId()));
        nome.setText(u.getNome());
        email.setText(u.getEmail());
        senha.setText(u.getSenha());


        AddData();
        viewAll();
        UpdateData();
        DeleteData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void DeleteData() {
        Button btnDelete= (Button)findViewById(R.id.btnDeletarConta);
        final DBHelper bd = new DBHelper(this);
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Integer deletedRows = bd.deletarUsuario();
                        if(deletedRows > 0)
                            Toast.makeText(ContaUsuario.this,"Conta deletada!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ContaUsuario.this,"Conta deletada!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        final DBHelper bd = new DBHelper(this);
        Button btnAlterar= (Button)findViewById(R.id.btnAtualizarDados);
        btnAlterar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = bd.updateData(id.getText().toString(),
                                nome.getText().toString(),
                                email.getText().toString(),senha.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(ContaUsuario.this,"Conta atualizada!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ContaUsuario.this,"Conta não atualizada, tenta novamente!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void AddData() {
        final DBHelper bd = new DBHelper(this);
        Button btnAddConta = (Button)findViewById(R.id.btnAddConta);
        btnAddConta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = bd.insertData(id.getText().toString(),nome.getText().toString(),
                                email.getText().toString(),
                                senha.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(ContaUsuario.this,"Conta adicionada!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ContaUsuario.this,"Conta não adicionada, tente novamente!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        final DBHelper bd = new DBHelper(this);
        Button btnvVerContas = (Button)findViewById(R.id.btnVerContas);
        btnvVerContas.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = bd.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Erro","Nenhuma contra encontrada");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("Nome :"+ res.getString(1)+"\n");
                            buffer.append("Email :"+ res.getString(2)+"\n");
                            buffer.append("Senha :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Contas",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
        getMenuInflater().inflate(R.menu.conta_usuario, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            telaHome();
        }
        else if (id == R.id.menu_movimentacao) {
            telaMovimentacao();
        }
        else if (id == R.id.grafico) {
            telaGrafico();
        }
/*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

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

    public void telaMovimentacao() {
        Intent intent = new Intent(this, Movimentacao.class);
        startActivity(intent);
    }
    public void telaGrafico() {
        Intent intent = new Intent(this, TelaGrafico.class);
        startActivity(intent);
    }
}
