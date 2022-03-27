package com.example.simondice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

//fragment que sirve para realizar las preferencias

public class PreferenciasFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public PreferenciasFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        addPreferencesFromResource(R.xml.configuracion_preferencias);
    }

    //listener que escucha cuando se tiene que cambiar el idioma
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s)
    {
        switch (s)
        {
            case "idiomas":
                boolean pIdioma = sharedPreferences.getBoolean(s,true);
                Locale nuevaloc;
                if (!pIdioma)
                {
                    nuevaloc = new Locale("es");
                }
                else
                {
                    nuevaloc = new Locale("eng");
                }

                Locale.setDefault(nuevaloc);
                Configuration configuration = getActivity().getBaseContext().getResources().getConfiguration();
                configuration.setLocale(nuevaloc);
                Context context = getActivity().getBaseContext().createConfigurationContext(configuration);
                getActivity().getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

                getActivity().finish();
                Intent intentPreferencias= new Intent(getActivity(), MenuActivity.class);
                startActivity(intentPreferencias);
                break;
            case "daltonico":
                boolean daltonico = sharedPreferences.getBoolean(s,false);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}