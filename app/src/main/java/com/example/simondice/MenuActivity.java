package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MenuActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onClickJugar(View v)
    {
        Intent intentRegistro= new Intent(this, JuegoActivity.class);
        startActivity(intentRegistro);
    }
    public void onClickMostrarInstrucciones(View v)
    {
        Intent intentRegistro= new Intent(this, InstruccionesActivity.class);
        startActivity(intentRegistro);

    }
    public void onClickPreferencias(View v)
    {
        Intent intentRegistro= new Intent(this, UsuariosActivity.class);
        startActivity(intentRegistro);
    }
}