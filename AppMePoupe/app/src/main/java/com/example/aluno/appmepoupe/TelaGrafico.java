package com.example.aluno.appmepoupe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class TelaGrafico extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_grafico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PieChart pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setUsePercentValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.setDrawEntryLabels(true);
        pieChart.setDrawHoleEnabled(false);
        Description des = pieChart.getDescription();
        des.setEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);


        addDataSet(pieChart);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void addDataSet(PieChart pieChart) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(10.0f, "Vestuário"));
        entries.add(new PieEntry(50.0f, "Alimentação"));
        entries.add(new PieEntry(20.0f, "Transporte"));
        entries.add(new PieEntry(15.0f, "Lazer"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(175,238,238));
        colors.add(Color.rgb(176,224,230));
        colors.add(Color.rgb(230,230,250));
        colors.add(Color.rgb(255,228,225));



        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextSize(20);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setData(pieData);
        pieChart.invalidate();

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
        getMenuInflater().inflate(R.menu.tela_grafico, menu);
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

        if (id == R.id.menu_movimentacao) {
            telaMovimentacao();
        } else if (id == R.id.conta) {
            telaAlterarConta();
        }
        else if (id == R.id.home) {
            // Handle the camera action
            telaHome();
        }
        /*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        }

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

    public void telaMovimentacao() {
        Intent intent = new Intent(this, Movimentacao.class);
        startActivity(intent);
    }

    public void telaAlterarConta() {
        Intent intent = new Intent(this, ContaUsuario.class);
        startActivity(intent);

    }
    public void telaHome(){
        Intent intent = new Intent(this,MenuLateral.class);
        startActivity(intent);
    }
}
