package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class JuegoActivity extends AppCompatActivity
{
    private ArrayList<Integer> listaSecuencia= new ArrayList<Integer>();
    private ImageButton botonRojo = findViewById(R.id.BotonRojo);
    private ImageButton botonAzul = findViewById(R.id.BotonAzul);
    private ImageButton botonVerde = findViewById(R.id.BotonVerde);
    private ImageButton botonAmarillo = findViewById(R.id.BotonAmarillo);
    private ImageView imagen= findViewById(R.id.imageView);
    private int posicionActual=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        aumentarSecuencia();
        aumentarSecuencia();
        aumentarSecuencia();
        repetirSecuencia();
    }
    private void repetirSecuencia()
    {
        int pos=0;
        while (pos<listaSecuencia.size()) {
            switch (listaSecuencia.get(pos)) {
                case 0:
                    imagen.setImageResource(R.drawable.cuadrado_rojo);
                    break;
                case 1:
                    imagen.setImageResource(R.drawable.cuadrado_azul);
                    break;
                case 2:
                    imagen.setImageResource(R.drawable.cuadrado_verde);
                    break;
                case 3:
                    imagen.setImageResource(R.drawable.cuadrado_amarillo);
                    break;
                default:
                    imagen.setImageResource(R.drawable.ic_launcher_background);
                    break;
            }
            try {Thread.sleep(1000);}
            catch (InterruptedException e) {e.printStackTrace(); }
        }
        imagen.setImageResource(R.drawable.ic_launcher_background);

    }
    private void comprobarSecuencia(int posicionActual, int i)
    {
        //se falla al repetir la secuencia
        if (listaSecuencia.get(posicionActual)!=i)
        {
            Intent intentInstrucciones= new Intent(this, MenuActivity.class);
            startActivity(intentInstrucciones);
        }

        //es el último elemento de la secuencia
        if (posicionActual>=listaSecuencia.size()-1)
        {
            aumentarSecuencia();
            repetirSecuencia();
            posicionActual=0;
        }

        //no es el último elemento y se sigue con los siguientes elemennos
        else
        {
            posicionActual++;
        }
    }
    private void aumentarSecuencia()
    {
        Random r = new Random();
        int nuevo = r.nextInt(3);
        listaSecuencia.add(nuevo);
    }
    public void onClickCuadradoRojo(View v)
    {
        comprobarSecuencia(posicionActual,0);
    }
    public void onClickCuadradoAzul(View v)
    {
        comprobarSecuencia(posicionActual,1);
    }
    public void onClickCuadradoVerde(View v)
    {
        comprobarSecuencia(posicionActual,2);
    }
    public void onClickCuadradoAmarillo(View v)
    {
        comprobarSecuencia(posicionActual,3);
    }
}