package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class JuegoActivity extends AppCompatActivity
{
    private ArrayList<Integer> listaSecuencia= new ArrayList<Integer>();
    private ImageView imagenSecuencia;
    private int posicionActual=0;
    private boolean poderAcertar=false;
    private boolean primeraVez=true;
    private boolean esperar=true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("juego","crear");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        imagenSecuencia = (ImageView) findViewById(R.id.ImageViewSecuencia);
    }
    private void repetirSecuencia()
    {
        int posicionSecuencia=0;
        while (posicionSecuencia<listaSecuencia.size())
        {
            Log.i("juego","entra");
            esperar = true;
            boolean primera = true;
            switch (listaSecuencia.get(posicionSecuencia))
            {
                case 0:
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_rojo);
                    break;
                case 1:
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_azul);
                    break;
                case 2:
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_verde);
                    break;
                case 3:
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_amarillo);
                    break;
                default:
                    imagenSecuencia.setImageResource(R.drawable.ic_launcher_background);
                    break;
            }
            posicionSecuencia++;

            while(esperar)
            {
                if (primera)
                {
                    Log.i("juego","primera");
                    primera = false;
                    CountDownTimer a = new CountDownTimer(3000, 1000)
                    {
                        public void onTick(long millisUntilFinished)
                        {
                            Log.i("juego","tick");
                        }

                        public void onFinish()
                        {
                            Log.i("juego","terminar");
                            esperar=false;
                        }
                    };//.start();
                    a.start();
                }
            }
            //try {Thread.sleep(1000);}
            //catch (InterruptedException e) {e.printStackTrace(); }

        }
        poderAcertar=true;
        imagenSecuencia.setImageResource(R.drawable.ic_launcher_background);

    }

    private void esperar1Segundo()
    {
        Log.i("juego","esperar1");
        new CountDownTimer(3000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                Log.i("juego","tick");
            }

            public void onFinish()
            {
                Log.i("juego","terminar");
                esperar=false;
            }
        }.start();
    }

    private void comprobarSecuencia(int i)
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
            poderAcertar=false;
        }

        //no es el último elemento y se sigue con los siguientes elemennos
        else
        {
            posicionActual++;
        }
    }
    private void aumentarSecuencia()
    {
        Log.i("juego","aumentar");
        Random r = new Random();
        int nuevo = r.nextInt(3);
        listaSecuencia.add(nuevo);
    }
    public void onClickCuadradoRojo(View v)
    {
        if (poderAcertar)
        {
            comprobarSecuencia(0);
        }

    }
    public void onClickCuadradoAzul(View v)
    {
        if (poderAcertar)
        {
            comprobarSecuencia(1);
        }
    }
    public void onClickCuadradoVerde(View v)
    {
        if (poderAcertar)
        {
            comprobarSecuencia(2);
        }
    }
    public void onClickCuadradoAmarillo(View v)
    {
        if (poderAcertar)
        {
            comprobarSecuencia(3);
        }
    }
    public void onClickEmpezar(View v)
    {
        if (primeraVez)
        {
            aumentarSecuencia();
            aumentarSecuencia();
            aumentarSecuencia();
            repetirSecuencia();
            primeraVez=false;
        }

    }
}