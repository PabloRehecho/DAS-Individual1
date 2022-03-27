package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.Locale;

//método que lanza el menú de preferencias
public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        //boolean idioma = preferencias.getBoolean("idiomas",false);
        //boolean daltonico = preferencias.getBoolean("daltonico", false);
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