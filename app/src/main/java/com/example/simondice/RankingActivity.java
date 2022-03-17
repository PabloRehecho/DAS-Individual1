package com.example.simondice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        TextView textViewUsuarios= findViewById(R.id.TextViewUsuarios);
        BaseDeDatos bd= BaseDeDatos.getInstance(this);
        ArrayList<String> lista = bd.mostrarUsuarios();
        String todos="";
        for (int i = 0; i < lista.size(); i+=2)
        {
            todos = todos + "--" + lista.get(i) + " " + lista.get(i+1) + "\n";
        }
        textViewUsuarios.setText(todos);
    }
}