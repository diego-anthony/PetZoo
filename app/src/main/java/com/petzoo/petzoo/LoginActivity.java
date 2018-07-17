package com.petzoo.petzoo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.petzoo.petzoo.helpers.PreferencesHelper;

public class LoginActivity extends AppCompatActivity {

    PreferencesHelper _prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _prefHelper = new PreferencesHelper(this);
        SetInitial();
    }

    void  SetInitial()
    {
        if(_prefHelper.getIsLogin())
        {
            IntentMain();
        }
        else
        {
            AttachLoginFragment();
        }
    }

    void AttachLoginFragment()
    {
        Fragment fragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.div_login,fragment)
                .commit();
    }

    void IntentMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0)
        {
            fragmentManager.popBackStack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}
