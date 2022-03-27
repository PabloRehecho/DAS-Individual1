package com.example.simondice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

//Esta clase crea el ListView personalizado que contiene un array de Strings nombre,
//uno de ints puntuaciones y uno de Strings fechas
public class AdaptadorListView extends BaseAdapter
{
    private Context contexto;
    private LayoutInflater inflater;
    private String[] nombres;
    private String[] puntuaciones;
    private String[] fechas;

    @Override
    public int getCount() {
        return nombres.length;
    }

    @Override
    public Object getItem(int i)
    {
        return nombres[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view=inflater.inflate(R.layout.list_view_personalizado,null);
        TextView nombre = (TextView) view.findViewById(R.id.TextViewRanking1);
        TextView puntuacion = (TextView) view.findViewById(R.id.TextViewRanking2);
        TextView fecha = (TextView) view.findViewById(R.id.TextViewRanking3);
        nombre.setText(nombres[i]);
        puntuacion.setText(puntuaciones[i]);
        fecha.setText(fechas[i]);
        return view;
    }

    public AdaptadorListView(Context pcontext, String[] pNombres, String[] pPuntuaciones, String[] pFechas)
    {
        contexto = pcontext;
        nombres = pNombres;
        puntuaciones = pPuntuaciones;
        fechas = pFechas;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
