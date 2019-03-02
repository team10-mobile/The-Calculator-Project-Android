package com.group10.calculator;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    // created action bar in activity main
    private void CreatedActionBar()
    {

        NavigationView navigationView = findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(this);
        mDrawerToggle =
                new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.DrawerLayout);
        CreatedActionBar();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        FragmentManager manager = getSupportFragmentManager();
        switch (menuItem.getItemId())
        {

            case R.id.txt_Calculator:
                // to do error must implement OnFragmentInteractionListener
                // i can't fix :(
                //fragment = new CalculatorFragment();
                Toast.makeText(MainActivity.this,"Calculator perform",Toast.LENGTH_SHORT).show();
                break;
        }
        //manager.beginTransaction().replace(R.id.DrawerLayout,fragment).commit();
        return true;
    }
}
