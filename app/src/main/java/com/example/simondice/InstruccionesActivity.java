package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

//se muestran por pantalla las instrucciones del juego en el idioma correcto
public class InstruccionesActivity extends AppCompatActivity
{
    String texto;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        String codigoPais = Locale.getDefault().toString();
        InputStream fich;
        Log.i("idioma", codigoPais);
        if (codigoPais.equals("es"))
        {
            fich = getResources().openRawResource(R.raw.intrucciones_espannol);
        }
        else if (codigoPais.equals("eng"))
        {
            fich = getResources().openRawResource(R.raw.instrucciones_ingles);
        }
        else
        {
            fich=null;
        }

        BufferedReader buff = new BufferedReader(new InputStreamReader(fich));
        try
        {
            String lineaActual;
            texto="";
            while ((lineaActual = buff.readLine()) != null)
            {
                texto = texto + lineaActual;
            }
            buff.close();
            TextView textViewInstrucciones = findViewById(R.id.TextViewInstrucciones);
            textViewInstrucciones.setText(texto);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //botón para volver al menú
    public void onClickVolver (View v)
    {
        Intent intentMenu= new Intent(this, MenuActivity.class);
        startActivity(intentMenu);
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