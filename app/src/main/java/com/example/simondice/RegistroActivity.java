package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

//actividad que realiza los registros
public class RegistroActivity extends AppCompatActivity
{
    BaseDeDatos bd= BaseDeDatos.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        comprobarIdioma();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    //se compruba si se cumplen los requisitos y se cumplen
    //se crea un nuevo usaurio en la base de datos
    public void onClickTerminarRegistro(View v)
    {
        if (comprobarRequisitos())
        {
            TextView textViewUsuarioNuevo= findViewById(R.id.EditTextUsuarioNuevo);
            TextView textViewContraseña1= findViewById(R.id.EditTextContraseñaNueva);
            String usuarioNuevo = textViewUsuarioNuevo.getText().toString();
            String contraseña1 = textViewContraseña1.getText().toString();
            bd.crearUsuario(usuarioNuevo, contraseña1);


            Intent intent=new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    //método que comprueba varios factores y genera Dialogs para informar
    private boolean comprobarRequisitos()
    {
        boolean aceptar = false;
        TextView textViewUsuarioNuevo= findViewById(R.id.EditTextUsuarioNuevo);
        TextView textViewContraseña1= findViewById(R.id.EditTextContraseñaNueva);
        TextView textViewContraseña2= findViewById(R.id.EditTextRepetirContraseña);
        String usuarioNuevo = textViewUsuarioNuevo.getText().toString();
        String contraseña1 = textViewContraseña1.getText().toString();
        String contraseña2 = textViewContraseña2.getText().toString();
        if (!bd.existeUsuario(usuarioNuevo))
        {
            if (contraseña1.equals(contraseña2))
            {
                if (contraseña1.length()>=4)
                {
                    bd.crearUsuario(usuarioNuevo,contraseña1);
                    aceptar = true;
                }
                else
                {
                    DialogFragment dialogoAlerta= new Dialogos(12);
                    dialogoAlerta.show(getSupportFragmentManager(), "etiqueta");
                }
            }
            else
            {
                DialogFragment dialogoAlerta= new Dialogos(11);
                dialogoAlerta.show(getSupportFragmentManager(), "etiqueta");
            }
        }
        else
        {
            DialogFragment dialogoAlerta= new Dialogos(10);
            dialogoAlerta.show(getSupportFragmentManager(), "etiqueta");
        }
        return aceptar;
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