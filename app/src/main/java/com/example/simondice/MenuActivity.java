package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MenuActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onClickJugar(View v)
    {
        Intent intentJugar= new Intent(this, JuegoActivity.class);
        startActivity(intentJugar);
    }
    public void onClickMostrarInstrucciones(View v)
    {
        Intent intentInstrucciones= new Intent(this, InstruccionesActivity.class);
        startActivity(intentInstrucciones);
    }
    public void onClickPreferencias(View v)
    {
        Intent intentPreferencias= new Intent(this, PreferenciasActivity.class);
        startActivity(intentPreferencias);
    }
    public void onClickMostrarUsuarios(View v)
    {
        Intent intentUsuarios= new Intent(this, RankingActivity.class);
        startActivity(intentUsuarios);
    }

}