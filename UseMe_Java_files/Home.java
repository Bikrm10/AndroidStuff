package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    Button bn1;
    Toolbar tool;
NavigationView navigation;
DrawerLayout drawerLayout;
ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bn1 = findViewById(R.id.button);
        tool =findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.nav_drawer);
        navigation = findViewById(R.id.navigation);
        setSupportActionBar(tool);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Login.class);
                startActivity(i);
            }
        });
        toggle = new ActionBarDrawerToggle(this,drawerLayout,tool,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id==R.id.logout){
//            Toast.makeText(this,""+item.getTitle(),Toast.LENGTH_SHORT).show();
//        }
//        if(id==R.id.logout){
//            startActivity(new Intent(this,Login.class));
//        }
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return true;
    }
}