package com.example.asifkhan.sqlitesimpletodoapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.asifkhan.sqlitesimpletodoapp.R;

/**
 * Created by asifkhan on 1/19/18.
 */

public class SettingsHelper {

    //apply theme for toolbar
    public static void applyThemeToolbar(Toolbar toolbar, Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            toolbar.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }else if(themeName.equals("Red")){
            toolbar.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryRed));
        }else if(themeName.equals("Green")){
            toolbar.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryGreen));
        }else if(themeName.equals("Violete")){
            toolbar.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryViolete));
        }
    }

    //apply theme for text views
    public static void applyThemeTextView(TextView textView,Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }else if(themeName.equals("Red")){
            textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryRed));
        }else if(themeName.equals("Green")){
            textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryGreen));
        }else if(themeName.equals("Violete")){
            textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryViolete));
        }
    }

    //apply text color for text views
    public static void applyTextColor(TextView textView,Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else if(themeName.equals("Red")){
            textView.setTextColor(context.getResources().getColor(R.color.colorPrimaryRed));
        }else if(themeName.equals("Green")){
            textView.setTextColor(context.getResources().getColor(R.color.colorPrimaryGreen));
        }else if(themeName.equals("Violete")){
            textView.setTextColor(context.getResources().getColor(R.color.colorPrimaryViolete));
        }
    }

    //apply theme
    public static void applyTheme(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            context.setTheme(R.style.AppTheme);
        }else if(themeName.equals("Red")){
            context.setTheme(R.style.RedTheme);
        }else if(themeName.equals("Green")){
            context.setTheme(R.style.GreenTheme);
        }else if(themeName.equals("Violete")){
            context.setTheme(R.style.VioleteTheme);
        }
    }
}
