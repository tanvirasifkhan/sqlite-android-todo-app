package com.example.asifkhan.sqlitesimpletodoapp.activities;


import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.helpers.SettingsHelper;

public class AppSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsHelper.applyTheme(this);
        setContentView(R.layout.activity_app_settings);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        SettingsHelper.applyThemeToolbar((Toolbar)findViewById(R.id.toolbar),this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.settings_title));
        getPrefFragment();
    }

    public static class AppPreference extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.app_preferences);
        }
    }

    //getting the setting fragment
    private void getPrefFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.prefContainer,new AppPreference()).commit();
    }
}
