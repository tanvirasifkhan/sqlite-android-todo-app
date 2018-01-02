package com.example.asifkhan.sqlitesimpletodoapp.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.example.asifkhan.sqlitesimpletodoapp.R;

public class AppSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_app_settings);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        applyThemeToolbar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.settings_title));
        setStatusBarColor();
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

    //apply theme
    public void applyTheme(){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            setTheme(R.style.AppTheme);
        }else if(themeName.equals("Red")){
            setTheme(R.style.RedTheme);
        }else if(themeName.equals("Green")){
            setTheme(R.style.GreenTheme);
        }else if(themeName.equals("Violete")){
            setTheme(R.style.VioleteTheme);
        }
    }

    //apply theme
    public void applyThemeToolbar(Toolbar toolbar){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else if(themeName.equals("Red")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryRed));
        }else if(themeName.equals("Green")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryGreen));
        }else if(themeName.equals("Violete")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryViolete));
        }
    }

    //change the status bar color according to the theme
    public void setStatusBarColor(){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            if(themeName.equals("Default")){
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
            }else if(themeName.equals("Red")){
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDarkRed));
            }else if(themeName.equals("Green")){
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDarkGreen));
            }else if(themeName.equals("Violete")){
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDarkViolete));
            }
        }

    }
}
