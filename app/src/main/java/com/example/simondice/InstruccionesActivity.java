package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InstruccionesActivity extends AppCompatActivity
{
    String texto;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        InputStream fich = getResources().openRawResource(R.raw.intrucciones_espannol);
        //InputStream fich = getResources().openRawResource(R.raw.intrucciones_ingles);
        BufferedReader buff = new BufferedReader(new InputStreamReader(fich));
        try
        {
            String lineaActual = buff.readLine();
            while (lineaActual != null)
            {
                texto = texto + lineaActual;
            }
            TextView textViewInstrucciones = findViewById(R.id.TextViewInstrucciones);
            textViewInstrucciones.setText(texto);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}