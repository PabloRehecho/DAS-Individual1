package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registro extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void onClickTerminarRegistro(View v)
    {
        Intent intentTerminarRegistro= new Intent(Registro.this, MainActivity.class);
        startActivity(intentTerminarRegistro);
    }
}