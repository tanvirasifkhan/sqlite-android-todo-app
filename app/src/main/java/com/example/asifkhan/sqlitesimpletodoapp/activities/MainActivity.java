package com.example.asifkhan.sqlitesimpletodoapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.adapters.PendingTodoAdapter;
import com.example.asifkhan.sqlitesimpletodoapp.models.PendingTodoModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{
    private RecyclerView pendingTodos;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<PendingTodoModel> pendingTodoModels;
    private PendingTodoAdapter pendingTodoAdapter;
    private FloatingActionButton addNewTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setTitle(R.string.app_title);
        changeStatusBarColor();
        showDrawerLayout();
        navigationMenuInit();
        loadPendingTodos();
    }

    //loading all the pending todos
    private void loadPendingTodos(){
        pendingTodos=(RecyclerView)findViewById(R.id.pending_todos_view);
        linearLayoutManager=new LinearLayoutManager(this);
        pendingTodoModels=new ArrayList<>();
        pendingTodoAdapter=new PendingTodoAdapter(pendingTodoModels,this);
        pendingTodos.setAdapter(pendingTodoAdapter);
        pendingTodos.setLayoutManager(linearLayoutManager);
        addNewTodo=(FloatingActionButton)findViewById(R.id.fabAddTodo);
        addNewTodo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fabAddTodo:
                startActivity(new Intent(this,AddNewTodo.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //change status bar color API>=21
    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.statusBarColor));
        }
    }


    //add the drawer layout
    private void showDrawerLayout(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,(Toolbar) findViewById(R.id.toolbar) , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    //initialize navigation menu
    private void navigationMenuInit(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pending_task_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                return true;
            case R.id.completed:
                startActivity(new Intent(this,CompletedTodos.class));
                return true;
            case R.id.settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pending_todos) {
            startActivity(new Intent(this,MainActivity.class));
        } else if (id == R.id.completed_todos) {
            startActivity(new Intent(this,CompletedTodos.class));
        } else if (id == R.id.tags) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.facebook) {

        } else if (id == R.id.github) {

        }else if(id==R.id.rate_us){

        }else if(id==R.id.share){

        }else if(id==R.id.more_apps){

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
