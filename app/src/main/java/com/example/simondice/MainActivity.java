package com.example.simondice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity
{
    BaseDeDatos bd= BaseDeDatos.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        bd.setUsuarioActual("USER_UNDEFINED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
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
    public void onClickRegistrarse(View v)
    {
        Intent intentRegistro= new Intent(this, RegistroActivity.class);
        startActivity(intentRegistro);
    }
}