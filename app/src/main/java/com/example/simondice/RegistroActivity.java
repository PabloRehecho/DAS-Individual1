package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void onClickTerminarRegistro(View v)
    {
        if (comprobarRequisitos())
        {
            Intent intentTerminarRegistro= new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(intentTerminarRegistro);
        }
    }

    private boolean comprobarRequisitos()
    {
        boolean aceptar = false;
        TextView textViewUsuarioNuevo= findViewById(R.id.EditTextUsuarioNuevo);
        TextView textViewContraseña1= findViewById(R.id.EditTextContraseñaNueva);
        TextView textViewContraseña2= findViewById(R.id.EditTextRepetirContraseña);
        String usuarioNuevo = textViewUsuarioNuevo.getText().toString();
        String contraseña1 = textViewContraseña1.getText().toString();
        String contraseña2 = textViewContraseña2.getText().toString();
        if (usuarioNuevo!="escogido")
        {
            if (contraseña1!=contraseña2)
            {
                aceptar = true;
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
}