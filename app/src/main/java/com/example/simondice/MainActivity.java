package com.example.simondice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;

import java.util.Locale;

//primera actividad que se ejecuta
public class MainActivity extends AppCompatActivity
{
    BaseDeDatos bd= BaseDeDatos.getInstance(this);

    //se compruba el idioma, se define el usuario actual a "undefined"
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
        bd.setUsuarioActual("USER_UNDEFINED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    //se comprueba que el usuario y contraseñas son correctos
    public void onClickIniciarSesion(View v)
    {
        TextView textViewUsuario = findViewById(R.id.EditTextUsuario);
        String usuario = textViewUsuario.getText().toString();
        TextView textViewContraseña = findViewById(R.id.EditTextContraseña);
        String contraseña = textViewContraseña.getText().toString();
        if (bd.existeUsuario(usuario))
        {
            if (bd.usuarioCorrecto(usuario, contraseña))
            {
                bd.setUsuarioActual(usuario);

                Intent intentMenu= new Intent(this, MenuActivity.class);
                startActivity(intentMenu);
            }
            else
            {
                DialogFragment dialogoAlerta= new Dialogos(1);
                dialogoAlerta.show(getSupportFragmentManager(), "etiqueta");
            }
        }
        else
        {
            DialogFragment dialogoAlerta= new Dialogos(0);
            dialogoAlerta.show(getSupportFragmentManager(), "etiqueta");
        }
    }

    //se lanza RegistroActivity
    public void onClickRegistrarse(View v)
    {
        Intent intent= new Intent(MainActivity.this,RegistroActivity.class);
        startActivityForResult(intent, 666);
    }

    //resultado de lanzar RegistroActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 666 && resultCode == RESULT_OK)
        {
            lanzarNotificacionRegistro();
        }
    }

    //lanzar notificación que indica usuario correctamente registrado
    private void lanzarNotificacionRegistro()
    {
        NotificationManager elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(this, "IdCanal");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel elCanal = new NotificationChannel("IdCanal", "Registro",
                    NotificationManager.IMPORTANCE_DEFAULT);
            elCanal.setDescription("Canal01");
            elCanal.enableLights(true);
            elCanal.setLightColor(Color.RED);
            elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            elCanal.enableVibration(true);
            elManager.createNotificationChannel(elCanal);
        }
        elBuilder.setSmallIcon(android.R.drawable.star_big_on)
                .setContentTitle(getResources().getString(R.string.NotificacionRegistroTitulo))
                .setSubText(getResources().getString(R.string.NotificacionRegistroSubTexto))
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setAutoCancel(true)
        ;
        elManager.notify(1, elBuilder.build());
    }

}