package com.example.simondice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

//clase que se encarga de todos lo relaciona con la base de datos
public class BaseDeDatos extends SQLiteOpenHelper
{
    private static BaseDeDatos miBase;
    private static String usuarioActual;

    //se realiza un patrón Singleton para que no se pueda crear la base de datos 2 veces.
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

    //Se crea una tabla que guarda los suaurios y otra que crea las puntuaciones
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE Usuarios ('NombreUsuario' VARCHAR(255) PRIMARY KEY NOT NULL, 'Contraseña' VARCHAR(255))");
        db.execSQL("CREATE TABLE Puntuaciones ('NombreUsuario' VARCHAR(255) , 'Puntuacion' INT, 'Fecha' DATE PRIMARY KEY NOT NULL)");
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

    //al ser un objeto Singleton puedo guardar aquí el nombre del suaurio actual
    //en vez de tener que llevarlo a traves de todas las activities
    public String getUsuarioActual()
    {
        return usuarioActual;
    }

    public void setUsuarioActual(String user)
    {
        usuarioActual = user;
    }

    //este método crear un usuario en la tabla Usuarios
    public void crearUsuario(String nombreUsuario, String contraseña)
    {
        Log.i("bd","crearUsuario");
        SQLiteDatabase bd = getWritableDatabase();
        String sentencia = String.format("INSERT INTO Usuarios VALUES ('%s' ,'%s')", nombreUsuario, contraseña);
        bd.execSQL(sentencia);
        bd.close();
    }

    //este método devuelve True si existe un usuario con ese nombre en la tabla Usuarios
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

    //este método comprueba si en la tabla Usuarios existe una fila con ese nombre y esa contraseña
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

    //este método devuelve un ArrayList con la información de los usuaurios, ya no se usa
    public ArrayList<String> mostrarUsuarios()
    {
        Log.i("bd","mostrarUsuarios");
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

    //este método devuelve la información de la tabla Puntuaciones
    public ArrayList<String> mostrarPuntuaciones()
    {
        Log.i("bd","mostrarUsuarios");
        ArrayList<String> listaUsuarios = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cu = bd.rawQuery("SELECT NombreUsuario, Puntuacion, Fecha FROM Puntuaciones ORDER BY Puntuacion DESC;", null);
        while (cu.moveToNext())
        {
            String nombre = cu.getString(0);
            String puntuacion = String.valueOf(cu.getInt(1));
            String fecha = cu.getString(2);
            listaUsuarios.add(nombre);
            listaUsuarios.add(puntuacion);
            listaUsuarios.add(fecha);
        }
        cu.close();
        bd.close();
        return listaUsuarios;
    }

    //crea una fila en la tabla Puntuaciones
    public void crearPuntuacion(int puntuacion)
    {
        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fecha = formatter.format(date);

        Log.i("bd","crearPuntuacion");
        SQLiteDatabase bd = getWritableDatabase();
        String sentencia = String.format("INSERT INTO Puntuaciones VALUES ('%s' ,'%d', '%s')", getUsuarioActual(), puntuacion, fecha);
        bd.execSQL(sentencia);
        bd.close();
    }


}
