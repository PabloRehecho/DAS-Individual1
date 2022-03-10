package com.example.simondice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper
{
    public BaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        crearUsuario("admin", "admin");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE Usuarios ('NombreUsuario' VARCHAR(255) PRIMARY KEY NOT NULL, 'Contraseña' VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){}

    public void crearUsuario(String nombreUsuario, String contraseña)
    {
        SQLiteDatabase bd = getWritableDatabase();
        String sentencia = String.format("INSERT INTO Usuario VALUES ('%s' ,'%s')", nombreUsuario, contraseña);
        bd.execSQL(sentencia);
        bd.close();
    }

    public boolean existeUsuario(String nombreNuevo)
    {
        SQLiteDatabase bd = getWritableDatabase();
        boolean encontrado=false;
        Cursor cu = bd.rawQuery("SELECT NombreUsuario FROM Usuarios", null);
        while (cu.moveToNext() && !encontrado)
        {
            String nombre = cu.getString(0);
            if (nombre.equals(nombreNuevo)) {encontrado=true;}
        }
        cu.close();
        bd.close();
        return encontrado;

    }

    public ArrayList<String> mostrarUsuarios()
    {
        ArrayList<String> listaUsuarios = new ArrayList<>();
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cu = bd.rawQuery("SELECT NombreUsuario, Contraseña FROM Usuarios", null);
        while (cu.moveToNext())
        {
            String nombre = cu.getString(0);
            String contraseña = cu.getString(1);
            listaUsuarios.add(nombre);
            listaUsuarios.add(contraseña);
        }
        cu.close();
        bd.close();
        return listaUsuarios;
    }
}
