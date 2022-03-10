package com.example.simondice;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class Dialogos extends DialogFragment {
    private int code = -1;

    public Dialogos(int tipo)
    {
        super();
        code = tipo;
    }

    //@NonNull
    //@Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch(code)
        {
            case 10 :
                crearDialogo10(builder);
                break;
            case 11 :
                crearDialogo11(builder);
                break;
            case 12 :
                crearDialogo12(builder);
                break;
            case 13 :
                crearDialogo13(builder);
                break;
            default :
                crearDialogoIndefinido(builder);
                break;
        }
        builder.setPositiveButton("Entendido", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
         });
        return builder.create();
    }

    private AlertDialog.Builder crearDialogoIndefinido(AlertDialog.Builder builder)
    {
        builder.setMessage("ERROR/ DIALOGO INDEFINIDO");
        return builder;
    }
    private AlertDialog.Builder crearDialogo10(AlertDialog.Builder builder)
    {
        builder.setTitle(" Usuario incorrecto");
        builder.setMessage("Nombre de usuario ya escogido, introduzca otro");
        return builder;
    }
    private AlertDialog.Builder crearDialogo11(AlertDialog.Builder builder)
    {
        builder.setTitle("Contraseñas erroneas");
        builder.setMessage("Ambas contraseñas tienen que coincidir");
        return builder;
    }
    private AlertDialog.Builder crearDialogo12(AlertDialog.Builder builder)
    {
        builder.setTitle("Contraseña corta");
        builder.setMessage("La contraseña tiene que tener al menos 4 caracteres");
        return builder;
    }
    private AlertDialog.Builder crearDialogo13(AlertDialog.Builder builder)
    {
        builder.setTitle("Éxito");
        builder.setMessage("Usuario creado correctamente");
        return builder;
    }
}