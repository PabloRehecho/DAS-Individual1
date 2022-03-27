package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;


public class MenuActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
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
        Intent intent= new Intent(this,PreferenciasActivity.class);
        startActivityForResult(intent, 666);

    }
    public void onClickMostrarUsuarios(View v)
    {
        Intent intentUsuarios= new Intent(this, RankingActivity.class);
        startActivity(intentUsuarios);
    }

    //cambia el idioma al correcto
    private void comprobarIdioma()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean pIdioma = sharedPreferences.getBoolean("idiomas",true);
        Locale nuevaloc;
        if (!pIdioma)
        {
            nuevaloc = new Locale("es");
        }
        else
        {
            nuevaloc = new Locale("eng");
        }

        Locale.setDefault(nuevaloc);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(nuevaloc);
        Context context = getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

}