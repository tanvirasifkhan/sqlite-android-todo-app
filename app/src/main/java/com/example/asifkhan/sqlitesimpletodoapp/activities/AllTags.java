package com.example.asifkhan.sqlitesimpletodoapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.adapters.TagAdapter;
import com.example.asifkhan.sqlitesimpletodoapp.helpers.TagDBHelper;
import com.example.asifkhan.sqlitesimpletodoapp.models.TagsModel;

import java.util.ArrayList;

public class AllTags extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView allTags;
    private ArrayList<TagsModel> tagsModels;
    private TagAdapter tagAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton fabAddTag;
    private TagDBHelper tagDBHelper;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_all_tags);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        setTitle(getString(R.string.all_tags_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        applyThemeToolbar((Toolbar)findViewById(R.id.toolbar));
        setStatusBarColor();
        loadTags();
    }

    //load all the tags
    private void loadTags(){
        allTags=(RecyclerView)findViewById(R.id.viewAllTags);
        linearLayout=(LinearLayout)findViewById(R.id.no_tags_available);
        tagDBHelper=new TagDBHelper(this);
        if(tagDBHelper.countTags()==0){
            linearLayout.setVisibility(View.VISIBLE);
            allTags.setVisibility(View.GONE);
        }else{
            allTags.setVisibility(View.VISIBLE);
            tagsModels=new ArrayList<>();
            tagsModels=tagDBHelper.fetchTags();
            tagAdapter=new TagAdapter(tagsModels,this);
            linearLayout.setVisibility(View.GONE);
        }
        linearLayoutManager=new LinearLayoutManager(this);
        allTags.setAdapter(tagAdapter);
        allTags.setLayoutManager(linearLayoutManager);
        fabAddTag=(FloatingActionButton)findViewById(R.id.fabAddTag);
        fabAddTag.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fabAddTag:
                showNewTagDialog();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vew_tag_option,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText=newText.toLowerCase();
                ArrayList<TagsModel> newTagsModels=new ArrayList<>();
                for(TagsModel tagsModel:tagsModels){
                    String tagTitle=tagsModel.getTagTitle().toLowerCase();
                    if(tagTitle.contains(newText)){
                        newTagsModels.add(tagsModel);
                    }
                }
                tagAdapter.filterTags(newTagsModels);
                tagAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                return true;
            case R.id.setting:
                startActivity(new Intent(this,AppSettings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //show add new tag dialog
    private void showNewTagDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view=layoutInflater.inflate(R.layout.add_new_tag_dialog,null);
        builder.setView(view);
        applyThemeTextView((TextView)view.findViewById(R.id.add_tag_dialog_title));
        final TextInputEditText tagTitle=(TextInputEditText)view.findViewById(R.id.tag_title);
        final TextView cancel=(TextView)view.findViewById(R.id.cancel);
        final TextView addNewtag=(TextView)view.findViewById(R.id.add_new_tag);
        applyTextColor(cancel);
        applyTextColor(addNewtag);

        addNewtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTagTitle=tagTitle.getText().toString();
                boolean isTagEmpty=tagTitle.getText().toString().isEmpty();
                boolean tagExists=tagDBHelper.tagExists(getTagTitle);

                if(isTagEmpty){
                    tagTitle.setError("Tag title required !");
                }else if(tagExists){
                    tagTitle.setError("Tag title already exists!");
                }else {
                    if(tagDBHelper.addNewTag(new TagsModel(getTagTitle))){
                        Toast.makeText(AllTags.this, R.string.tag_title_add_success_msg, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AllTags.this,AllTags.class));
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllTags.this,AllTags.class));
            }
        });
        builder.create().show();
    }

    //apply theme
    public void applyTheme(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
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

    //apply theme for toolbar
    public void applyThemeToolbar(Toolbar toolbar){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            setTheme(R.style.AppTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else if(themeName.equals("Red")){
            setTheme(R.style.RedTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryRed));
        }else if(themeName.equals("Green")){
            setTheme(R.style.GreenTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryGreen));
        }else if(themeName.equals("Violete")){
            setTheme(R.style.VioleteTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryViolete));
        }
    }

    //apply theme for text views
    public void applyThemeTextView(TextView textView){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            setTheme(R.style.AppTheme);
            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else if(themeName.equals("Red")){
            setTheme(R.style.RedTheme);
            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryRed));
        }else if(themeName.equals("Green")){
            setTheme(R.style.GreenTheme);
            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryGreen));
        }else if(themeName.equals("Violete")){
            setTheme(R.style.VioleteTheme);
            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryViolete));
        }
    }

    //apply text color for text views
    public void applyTextColor(TextView textView){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String themeName=sharedPreferences.getString("app_theme","Default");
        if(themeName.equals("Default")){
            setTheme(R.style.AppTheme);
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else if(themeName.equals("Red")){
            setTheme(R.style.RedTheme);
            textView.setTextColor(getResources().getColor(R.color.colorPrimaryRed));
        }else if(themeName.equals("Green")){
            setTheme(R.style.GreenTheme);
            textView.setTextColor(getResources().getColor(R.color.colorPrimaryGreen));
        }else if(themeName.equals("Violete")){
            setTheme(R.style.VioleteTheme);
            textView.setTextColor(getResources().getColor(R.color.colorPrimaryViolete));
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
                setTheme(R.style.AppTheme);
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
            }else if(themeName.equals("Red")){
                setTheme(R.style.RedTheme);
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDarkRed));
            }else if(themeName.equals("Green")){
                setTheme(R.style.GreenTheme);
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDarkGreen));
            }else if(themeName.equals("Violete")){
                setTheme(R.style.VioleteTheme);
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDarkViolete));
            }
        }

    }
}
