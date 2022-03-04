package com.example.simondice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simondice.Registro;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickIniciarSesion(View v)
    {

    }
    public void onClickRegistrarse(View v)
    {
        Intent intentRegistro= new Intent(MainActivity.this, Registro.class);
        startActivity(intentRegistro);
    }
}