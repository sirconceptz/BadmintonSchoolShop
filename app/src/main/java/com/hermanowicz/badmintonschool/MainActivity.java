package com.hermanowicz.badmintonschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hermanowicz.badmintonschool.fragments.AboutUsFragment;
import com.hermanowicz.badmintonschool.fragments.ContactFragment;
import com.hermanowicz.badmintonschool.fragments.NewsFragment;
import com.hermanowicz.badmintonschool.fragments.SalesFragment;
import com.hermanowicz.badmintonschool.fragments.SettingsFragment;
import com.hermanowicz.badmintonschool.fragments.ShopFragment;

public class MainActivity extends AppCompatActivity {

    final private Fragment shopFragment = new ShopFragment();
    final private Fragment salesFragment = new SalesFragment();
    final private Fragment newsFragment = new NewsFragment();
    final private Fragment chatFragment = new ContactFragment();
    final private Fragment aboutUsFragment = new AboutUsFragment();
    final private Fragment settingsFragment = new SettingsFragment();
    final private FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setActionBar();
        setFragmentManager();
        setListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fragmentManager.beginTransaction()
                .hide(newsFragment)
                .hide(chatFragment)
                .hide(shopFragment)
                .hide(aboutUsFragment)
                .hide(salesFragment)
                .show(settingsFragment)
                .commit();
            return true;
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        bottomNavView = findViewById(R.id.bottomNavView);
    }

    private void setActionBar() {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_settings);
        }
    }

    private void setFragmentManager() {
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, shopFragment)
                .add(R.id.fragmentContainer, newsFragment)
                .add(R.id.fragmentContainer, chatFragment)
                .add(R.id.fragmentContainer, aboutUsFragment)
                .add(R.id.fragmentContainer, settingsFragment)
                .add(R.id.fragmentContainer, salesFragment)
                .hide(salesFragment)
                .hide(shopFragment)
                .hide(chatFragment)
                .hide(aboutUsFragment)
                .hide(settingsFragment)
                .commit();
    }

    private void onClickBottomNavView(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_shop:
                fragmentManager.beginTransaction()
                        .hide(newsFragment)
                        .hide(chatFragment)
                        .hide(aboutUsFragment)
                        .hide(settingsFragment)
                        .hide(salesFragment)
                        .show(shopFragment)
                        .commit();
                item.setChecked(true);
                break;
            case R.id.button_sales:
                fragmentManager.beginTransaction()
                        .hide(newsFragment)
                        .hide(chatFragment)
                        .hide(shopFragment)
                        .hide(settingsFragment)
                        .hide(salesFragment)
                        .hide(aboutUsFragment)
                        .show(salesFragment)
                        .commit();
                item.setChecked(true);
                break;
            case R.id.button_news:
                fragmentManager.beginTransaction()
                        .hide(shopFragment)
                        .hide(chatFragment)
                        .hide(aboutUsFragment)
                        .hide(settingsFragment)
                        .hide(salesFragment)
                        .show(newsFragment)
                        .commit();
                item.setChecked(true);
                break;
            case R.id.button_chat:
                fragmentManager.beginTransaction()
                        .hide(newsFragment)
                        .hide(shopFragment)
                        .hide(aboutUsFragment)
                        .hide(settingsFragment)
                        .hide(salesFragment)
                        .show(chatFragment)
                        .commit();
                item.setChecked(true);
                break;
            case R.id.button_about_us:
                fragmentManager.beginTransaction()
                        .hide(newsFragment)
                        .hide(chatFragment)
                        .hide(shopFragment)
                        .hide(settingsFragment)
                        .hide(salesFragment)
                        .show(aboutUsFragment)
                        .commit();
                item.setChecked(true);
                break;
        }
    }

    private void setListeners() {
        bottomNavView.setOnNavigationItemSelectedListener(item -> {
            onClickBottomNavView(item);
            return true;
        });
    }
}