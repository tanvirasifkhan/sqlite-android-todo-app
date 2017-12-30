package com.example.asifkhan.sqlitesimpletodoapp.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by asifkhan on 12/30/17.
 */

public class IntentExtras {
    public static final String GITHUB_URL="http://github.com/asifkhantanvir";
    public static final String FACEBOOK_URL="https://www.facebook.com/tanvirasifkhan";
    public static final String RATING_ON_PLAY_STORE_URL="https://play.google.com/store/apps/details?id=com.todo.asifkhan.sqlitesimpletodoapp";
    public static final String SHARE_APP_URL="https://play.google.com/store/apps/details?id=com.todo.asifkhan.sqlitesimpletodoapp";
    public static final String MORE_APPS_URL="https://play.google.com/store/apps/developer?id=Tanvir+Asif+Khan";

    //find on github
    public static void findOnGithub(Context context){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_URL));
        context.startActivity(intent);
    }

    //find on facebook
    public static void findOnFacebook(Context context){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL));
        context.startActivity(intent);
    }

    //rate us on play store
    public static void rateOnPlayStore(Context context){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(RATING_ON_PLAY_STORE_URL));
        context.startActivity(intent);
    }

    //find more apps
    public static void findMoreApps(Context context){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(MORE_APPS_URL));
        context.startActivity(intent);
    }

    //share this app
    public static void shareThisApp(Context context){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,SHARE_APP_URL);
        context.startActivity(Intent.createChooser(intent,"Share This App"));
    }
}
