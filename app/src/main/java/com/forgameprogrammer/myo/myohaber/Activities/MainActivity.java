package com.forgameprogrammer.myo.myohaber.Activities;

import android.content.Intent;
import com.forgameprogrammer.myo.myohaber.Fragments.DuyuruFragment;
import com.forgameprogrammer.myo.myohaber.Fragments.ProgramFragment;
import com.forgameprogrammer.myo.myohaber.Fragments.YemekFragment;
import com.forgameprogrammer.myo.myohaber.Services.RegistrationIntentService;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements DuyuruFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener
{

    FrameLayout frameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.forgameprogrammer.myo.myohaber.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.forgameprogrammer.myo.myohaber.R.id.toolbar);
        setSupportActionBar(toolbar);

        frameFragment = (FrameLayout) findViewById(com.forgameprogrammer.myo.myohaber.R.id.frameFragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(com.forgameprogrammer.myo.myohaber.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.forgameprogrammer.myo.myohaber.R.string.navigation_drawer_open, com.forgameprogrammer.myo.myohaber.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.forgameprogrammer.myo.myohaber.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentYukle(new DuyuruFragment());
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

    }

    public void fragmentYukle(Fragment fragment)
    {
        if (fragment != null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(com.forgameprogrammer.myo.myohaber.R.id.frameFragment, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.forgameprogrammer.myo.myohaber.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.forgameprogrammer.myo.myohaber.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.forgameprogrammer.myo.myohaber.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.forgameprogrammer.myo.myohaber.R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        switch (id)
        {
            case com.forgameprogrammer.myo.myohaber.R.id.nav_dersprogram:
                fragment = new ProgramFragment();
                break;
            case com.forgameprogrammer.myo.myohaber.R.id.nav_duyuru:
                fragment = new DuyuruFragment();
                break;
            case com.forgameprogrammer.myo.myohaber.R.id.nav_yemekler:
                fragment = new YemekFragment();
                break;
            case com.forgameprogrammer.myo.myohaber.R.id.nav_ulas:

                break;
        }
        fragmentYukle(fragment);
        return true;
    }
}
