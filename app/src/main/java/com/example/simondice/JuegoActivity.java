package com.example.simondice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//clase que se encarga del juego en sí
public class JuegoActivity extends AppCompatActivity
{
    private ArrayList<Integer> listaSecuencia= new ArrayList<Integer>();
    private ImageView imagenSecuencia;
    private int posicionActual=0;
    private boolean poderAcertar=false;
    private boolean primeraVez=true;
    @Override
    //se comprueba si hay savedInstanceState para ersaturar el estado del juego
    //si está activado el modo daltónico se cambian las imagenes
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
        Log.i("juego","crear");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        imagenSecuencia = (ImageView) findViewById(R.id.ImageViewSecuencia);

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        boolean daltonico = preferencias.getBoolean("daltonico",false);
        if (daltonico)
        {
            ImageButton cuadradoRojo = findViewById(R.id.BotonRojo);
            ImageButton cuadradoAzul = findViewById(R.id.BotonAzul);
            ImageButton cuadradoVerde = findViewById(R.id.BotonVerde);
            ImageButton cuadradoAmarillo = findViewById(R.id.BotonAmarillo);
            cuadradoRojo.setImageResource(R.drawable.figura_cuadrado);
            cuadradoAzul.setImageResource(R.drawable.figura_circulo);
            cuadradoVerde.setImageResource(R.drawable.figura_equis);
            cuadradoAmarillo.setImageResource(R.drawable.figura_triangulo);
            imagenSecuencia.setImageResource(R.drawable.ic_launcher_foreground);
        }

        if (savedInstanceState!= null)
        {
            posicionActual = savedInstanceState.getInt("posicionActual");
            poderAcertar = savedInstanceState.getBoolean("poderAcertar");
            primeraVez = savedInstanceState.getBoolean("primeraVez");
            listaSecuencia = savedInstanceState.getIntegerArrayList("listaSecuencia");
            if (!primeraVez) {mostrarUltimo();}
        }
    }


    //método que guarda el estado actual del juego por si se cierra
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("posicionActual", posicionActual);
        outState.putBoolean("poderAcertar", poderAcertar);
        outState.putBoolean("primeraVez", primeraVez);
        outState.putIntegerArrayList("listaSecuencia", listaSecuencia);
    }
    //método original que no funciona
    /*
    private void repetirSecuencia()
    {
        int posicionSecuencia=0;
        while (posicionSecuencia<listaSecuencia.size())
        {
            String drawable=imagenSecuencia.getDrawable().toString();
            Log.i("juego","entra " + listaSecuencia.get(posicionSecuencia));
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

            String drawable2;
            switch (listaSecuencia.get(posicionSecuencia))
            {
                case 0:
                    drawable2= getResources().getDrawable(R.drawable.cuadrado_rojo).toString();
                    break;
                case 1:
                    drawable2= getResources().getDrawable(R.drawable.cuadrado_azul).toString();
                    break;
                case 2:
                    drawable2= getResources().getDrawable(R.drawable.cuadrado_verde).toString();
                    break;
                case 3:
                    drawable2= getResources().getDrawable(R.drawable.cuadrado_amarillo).toString();
                    break;
                default:
                    drawable2= getResources().getDrawable(R.drawable.ic_launcher_background).toString();
                    break;
            }

            while(!drawable2.equals(imagenSecuencia.getDrawable().toString()))
            {

            }

        }
        poderAcertar=true;
        imagenSecuencia.setImageResource(R.drawable.ic_launcher_background);

    }
    */

    //muestra el útlimo elemtno de la secuencia en la útlima imagen
    private void mostrarUltimo()
    {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        boolean daltonico = preferencias.getBoolean("daltonico",false);
        if (!daltonico)
        {
            switch (listaSecuencia.get(listaSecuencia.size() - 1))
            {
                case 0:
                    Log.i("juego", "mostrar 0");
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_rojo);
                    break;
                case 1:
                    Log.i("juego", "mostrar 1");
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_azul);
                    break;
                case 2:
                    Log.i("juego", "mostrar 2");
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_verde);
                    break;
                case 3:
                    Log.i("juego", "mostrar 3");
                    imagenSecuencia.setImageResource(R.drawable.cuadrado_amarillo);
                    break;
                default:
                    Log.i("juego", "mostrar error");
                    imagenSecuencia.setImageResource(R.drawable.ic_launcher_background);
                    break;
            }
        }
        else
        {
            switch (listaSecuencia.get(listaSecuencia.size() - 1))
            {
                case 0:
                    Log.i("juego", "mostrar 0");
                    imagenSecuencia.setImageResource(R.drawable.figura_cuadrado);
                    break;
                case 1:
                    Log.i("juego", "mostrar 1");
                    imagenSecuencia.setImageResource(R.drawable.figura_circulo);
                    break;
                case 2:
                    Log.i("juego", "mostrar 2");
                    imagenSecuencia.setImageResource(R.drawable.figura_equis);
                    break;
                case 3:
                    Log.i("juego", "mostrar 3");
                    imagenSecuencia.setImageResource(R.drawable.figura_triangulo);
                    break;
                default:
                    Log.i("juego", "mostrar error");
                    imagenSecuencia.setImageResource(R.drawable.ic_launcher_background);
                    break;
            }
        }
        poderAcertar=true;
    }


    //cuando intentas adivinar la secuencia se llama a este método,
    //si fallas --> FinalActivity
    //si aciertas y es último elemento --> se aumenta la Secuencia y se reinicia la posicionActual
    //si aciertas y no es útlimo elemtno --> se pasa a acertar el siguiente
    private void comprobarSecuencia(int i)
    {
        Log.i("juego","posicion = " + posicionActual);
        //se falla al repetir la secuencia
        if (listaSecuencia.get(posicionActual)!=i)
        {
            Intent intentFinal= new Intent(this, FinalActivity.class);
            intentFinal.putIntegerArrayListExtra("listaSecuencia", listaSecuencia);
            startActivity(intentFinal);
        }

        //es el último elemento de la secuencia
        if (posicionActual>=listaSecuencia.size()-1)
        {
            Log.i("juego","ultimo");
            posicionActual=0;
            poderAcertar=false;
            aumentarSecuencia();
            //repetirSecuencia();
            mostrarUltimo();


        }

        //no es el último elemento y se sigue con los siguientes elemennos
        else
        {
            Log.i("juego","mas");
            posicionActual++;
        }
    }

    //aumentar secuencia por 1
    private void aumentarSecuencia()
    {
        Log.i("juego","aumentar Secuencia");
        Random r = new Random();
        int nuevo = r.nextInt(4);
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
            primeraVez=false;
            aumentarSecuencia();
            //aumentarSecuencia();
            //aumentarSecuencia();
            mostrarUltimo();

        }
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