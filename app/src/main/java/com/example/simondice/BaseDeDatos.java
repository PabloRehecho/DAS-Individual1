package com.example.simondice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper
{
    private static BaseDeDatos miBase;
    private static String usuarioActual;

    public static synchronized BaseDeDatos getInstance(Context context)
    {
        if (miBase == null)
        {
            miBase = new BaseDeDatos(context);
        }
        return miBase;
    }

    private BaseDeDatos(@Nullable Context context)
    {
        super(context, "BaseDatos", null, 1);
        //crearUsuario("admin", "admin");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE Usuarios ('NombreUsuario' VARCHAR(255) PRIMARY KEY NOT NULL, 'Contraseña' VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        if (i != i1) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + "Usuarios");
            onCreate(db);
        }
    }

    public void crearUsuario(String nombreUsuario, String contraseña)
    {
        Log.i("bd","crear");
        SQLiteDatabase bd = getWritableDatabase();
        String sentencia = String.format("INSERT INTO Usuarios VALUES ('%s' ,'%s')", nombreUsuario, contraseña);
        bd.execSQL(sentencia);
        bd.close();
    }

    public boolean existeUsuario(String nombreNuevo)
    {
        SQLiteDatabase bd = getReadableDatabase();
        boolean encontrado=false;
        Cursor cu = bd.rawQuery("SELECT NombreUsuario FROM Usuarios", null);
        while (cu.moveToNext() && !encontrado)
        {
            String nombre = cu.getString(0);
            if (nombre.equals(nombreNuevo))
            {
                encontrado=true;
            }
        }
        cu.close();
        bd.close();
        return encontrado;
    }

    public boolean usuarioCorrecto(String pUsuario, String pContraseña)
    {
        Log.i("bd","usuarioCorrecto");
        SQLiteDatabase bd = getReadableDatabase();
        boolean encontrado = false;
        boolean coinciden = false;
        Cursor cu = bd.rawQuery("SELECT NombreUsuario, Contraseña FROM Usuarios", null);
        while (cu.moveToNext() && !encontrado)
        {
            String nombre = cu.getString(0);
            String contraseña = cu.getString(1);
            if (nombre.equals(pUsuario))
            {
                encontrado=true;
                if (contraseña.equals(pContraseña))
                {
                    coinciden = true;
                }

            }
        }
        cu.close();
        bd.close();
        return coinciden;
    }

    public ArrayList<String> mostrarUsuarios()
    {
        Log.i("bd","mostrar");
        ArrayList<String> listaUsuarios = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
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

    public String getUsuarioActual()
    {
        return usuarioActual;
    }

    public void setUsuarioActual(String user)
    {
        usuarioActual = user;
    }
}
