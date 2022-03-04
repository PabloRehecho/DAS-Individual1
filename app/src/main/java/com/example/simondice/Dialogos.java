package com.example.simondice;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class Dialogos extends DialogFragment {
    private int código;

    public Dialogos(int tipo)
    {
        super();
        código=tipo;
    }

    //@NonNull
    //@Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState, int tipo)
    {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch(tipo)
        {
            case 10 :
                crearDialogo11(builder);
                break;
            case 11 :
                crearDialogo12(builder);
                break;
            default :
                crearDialogoIndefinido(builder);
                break;
        }
        return builder.create();
    }

    private AlertDialog.Builder crearDialogoIndefinido(AlertDialog.Builder builder)
    {
        builder.setTitle("ERROR/ DIALOGO INDEFINIDO");
        return builder;
    }

    private AlertDialog.Builder crearDialogo10(AlertDialog.Builder builder)
    {
        builder.setTitle("Usuario ya escogido, introduzca otro");
        return builder;
    }
    private AlertDialog.Builder crearDialogo11(AlertDialog.Builder builder)
    {
        builder.setTitle("Ambas contraseñas tienen que coincidir");
        return builder;
    }
    private AlertDialog.Builder crearDialogo12(AlertDialog.Builder builder)
    {
        builder.setTitle("Usuario introducido correctamente");
        return builder;
    }
}