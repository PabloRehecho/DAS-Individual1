package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        TextView textViewUsuarios= findViewById(R.id.TextViewUsuarios);
        BaseDeDatos bd= new BaseDeDatos(this, "NombreBD", null, 1);
        ArrayList<String> lista = bd.mostrarUsuarios();
        for (int i = 0; i < lista.size(); i+=2)
        {
            String actual = textViewUsuarios.getText().toString();
            actual = actual + "--" + lista.get(i) + lista.get(i+1) + "\n";
        }
    }
}