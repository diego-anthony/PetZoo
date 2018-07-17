package com.petzoo.petzoo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.TextView;

import com.petzoo.petzoo.db.DBHelper;
import com.petzoo.petzoo.helpers.PreferencesHelper;
import com.petzoo.petzoo.models.Usuario;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MapsFragment.OnFragmentInteractionListener {

    TextView txt_name,txt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        choseFragment(R.id.nav_maps_fragment);

        View headerView = navigationView.getHeaderView(0);
        txt_name = headerView.findViewById(R.id.txt_name);
        txt_email = headerView.findViewById(R.id.txt_email);

        PreferencesHelper preferencesHelper= new PreferencesHelper(this);
        String nameUser = preferencesHelper.getName();
        txt_name.setText(nameUser.toString());
        txt_email.setText(preferencesHelper.getEmail().toString());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // test commit
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        return choseFragment(id);
    }

    private boolean choseFragment(int id) {
        Fragment fragment = null;
        if (id == R.id.nav_maps_fragment) {
            fragment = new MapsFragment();
        }
        else if(id == R.id.nav_close_sesion){
            PreferencesHelper preferencesHelper = new PreferencesHelper(this);
            preferencesHelper.putIsLogin(false);
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(id == R.id.nav_new_alert_fragment){
            fragment = new NuevaAlertaFragment();
        }
        if (fragment != null)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


        @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
