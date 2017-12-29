package com.example.asifkhan.sqlitesimpletodoapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        setContentView(R.layout.activity_all_tags);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        setTitle(R.string.all_tags_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.statusBarColor));
        }
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
        getMenuInflater().inflate(R.menu.add_new_tag_option,menu);
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
        final TextInputEditText tagTitle=(TextInputEditText)view.findViewById(R.id.tag_title);
        final TextView cancel=(TextView)view.findViewById(R.id.cancel);
        final TextView addNewtag=(TextView)view.findViewById(R.id.add_new_tag);

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
}
