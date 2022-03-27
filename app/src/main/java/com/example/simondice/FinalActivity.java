package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

//esta actividad se utiliza tras perder una partida
public class FinalActivity extends AppCompatActivity
{
    BaseDeDatos bd= BaseDeDatos.getInstance(this);

    //se recoge la secuencia que se ha usado en el juego
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
        setTheme(android.R.style.Theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        lanzarNotificacionGameOver();

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            Log.i("juego","extras");
            ArrayList<Integer> listaSecuencia= extras.getIntegerArrayList("listaSecuencia");
            Log.i("juego",listaSecuencia.toString());
            bd.crearPuntuacion(listaSecuencia.size());
            SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
            boolean daltonico = preferencias.getBoolean("daltonico",false);
            imprimirResultado(listaSecuencia, daltonico);
        }
    }

    // se muestra ka secuencia generada en el juego
    private void imprimirResultado(ArrayList<Integer> listaSecuencia, boolean daltonico)
    {
        int i = 0;
        TextView textViewFinal = findViewById(R.id.TextViewSecuenciaFinal);
        textViewFinal.setText("");
        while (i<listaSecuencia.size())
        {
            String texto = textViewFinal.getText().toString();
            texto = texto + getResources().getString(R.string.AyudaFinal) + " " + (i+1) + " : ";
            if (!daltonico)
            {
                switch (listaSecuencia.get(i))
                {
                    case 0:
                        texto = texto + getResources().getString(R.string.Rojo);
                        break;
                    case 1:
                        texto = texto + getResources().getString(R.string.Azul);
                        break;
                    case 2:
                        texto = texto + getResources().getString(R.string.Verde);
                        break;
                    case 3:
                        texto = texto + getResources().getString(R.string.Amarillo);
                        break;
                }
            }
            else
            {
                switch (listaSecuencia.get(i))
                {
                    case 0:
                        texto = texto + getResources().getString(R.string.Cuadrado);
                        break;
                    case 1:
                        texto = texto + getResources().getString(R.string.Circulo);
                        break;
                    case 2:
                        texto = texto + getResources().getString(R.string.Equis);
                        break;
                    case 3:
                        texto = texto + getResources().getString(R.string.Triangulo);
                        break;
                }
            }
            texto = texto + " - ";
            textViewFinal.setText(texto);
            i++;
        }
    }

    //se lanza una notifación sobre cómo se ha perdido la partida
    public void lanzarNotificacionGameOver()
    {
        NotificationManager elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(this, "IdCanal");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel elCanal = new NotificationChannel("IdCanal", "GameOver",
                    NotificationManager.IMPORTANCE_DEFAULT);
            elCanal.setDescription("Canal01");
            elCanal.enableLights(true);
            elCanal.setLightColor(Color.RED);
            elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            elCanal.enableVibration(true);
            elManager.createNotificationChannel(elCanal);
        }
        elBuilder.setSmallIcon(android.R.drawable.ic_delete)
                .setContentTitle(getResources().getString(R.string.NotificacionGameOverTitulo))
                .setContentText(":^(")
                .setSubText(getResources().getString(R.string.NotificacionGameOverSubTexto))

                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setAutoCancel(true)
        ;
        elManager.notify(2, elBuilder.build());
    }

    // al pulsar el botón "Mail" se abre una aplicación de envío de correo
    public void onClickMail(View v)
    {
        sendMail();
    }

    public void sendMail()
    {
        Log.i("juego", "enviar correo");
        String[] TO = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setData(Uri.parse("mailto:"));
        //emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Simon Dice");
        emailIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.TextoMail));

        try
        {
            startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.TituloMail)));
            finish();
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(this, getResources().getString(R.string.TextoMail), Toast.LENGTH_SHORT).show();
        }
    }

    //método para volver al menú
    public void onClickVolver(View v)
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