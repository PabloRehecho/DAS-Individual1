package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

//método que muestra las puntuaciones
public class RankingActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        generarRanking();

        /*TextView textViewUsuarios= findViewById(R.id.TextViewUsuarios);
        BaseDeDatos bd= BaseDeDatos.getInstance(this);
        ArrayList<String> lista = bd.mostrarUsuarios();
        String todos="";
        for (int i = 0; i < lista.size(); i+=2)
        {
            todos = todos + "--" + lista.get(i) + " " + lista.get(i+1) + "\n";
        }
        textViewUsuarios.setText(todos);*/
    }

    protected void generarRanking()
    {
        BaseDeDatos bd= BaseDeDatos.getInstance(this);
        ArrayList<String> lista = bd.mostrarPuntuaciones();

        String[] nombres = new String[lista.size()/3];
        String[] puntuaciones = new String[lista.size()/3];;
        String[] fechas = new String[lista.size()/3];;


        for (int j = 0; j<lista.size(); j++)
        {
            Log.i("ranking", lista.get(j));
        }
        for (int i = 0; i < lista.size(); i+=3)
        {
            nombres[i/3]="Usuario: " + lista.get(i);
            puntuaciones[(i/3)]="Puntuación: " + lista.get(i+1);
            fechas[(i/3)]="Fecha: " + lista.get(i+2);
        }

        ListView ranking= (ListView) findViewById(R.id.ListViewRanking);
        AdaptadorListView eladap= new AdaptadorListView(getApplicationContext(),nombres,puntuaciones,fechas);
        ranking.setAdapter(eladap);
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