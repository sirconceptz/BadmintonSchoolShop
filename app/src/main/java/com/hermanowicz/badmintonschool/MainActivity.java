package com.hermanowicz.badmintonschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hermanowicz.badmintonschool.fragments.AboutUsFragment;
import com.hermanowicz.badmintonschool.fragments.ContactFragment;
import com.hermanowicz.badmintonschool.fragments.NewsFragment;
import com.hermanowicz.badmintonschool.fragments.SalesFragment;
import com.hermanowicz.badmintonschool.fragments.SettingsFragment;
import com.hermanowicz.badmintonschool.fragments.ShopFragment;
import com.hermanowicz.badmintonschool.interfaces.KeyEventListener;

public class MainActivity extends AppCompatActivity {

    final private Fragment shopFragment = new ShopFragment();
    final private Fragment salesFragment = new SalesFragment();
    final private Fragment newsFragment = new NewsFragment();
    final private Fragment contactFragment = new ContactFragment();
    final private Fragment aboutUsFragment = new AboutUsFragment();
    final private Fragment settingsFragment = new SettingsFragment();
    final private FragmentManager fragmentManager = getSupportFragmentManager();

    private Fragment currentFragment = newsFragment;
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
                .hide(contactFragment)
                .hide(shopFragment)
                .hide(aboutUsFragment)
                .hide(salesFragment)
                .show(settingsFragment)
                .commit();
            return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(currentFragment != null && currentFragment instanceof KeyEventListener) {
            ((KeyEventListener) currentFragment).onKeyDown(keyCode, event);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
                .add(R.id.fragmentContainer, contactFragment)
                .add(R.id.fragmentContainer, aboutUsFragment)
                .add(R.id.fragmentContainer, settingsFragment)
                .add(R.id.fragmentContainer, salesFragment)
                .hide(salesFragment)
                .hide(shopFragment)
                .hide(contactFragment)
                .hide(aboutUsFragment)
                .hide(settingsFragment)
                .commit();
    }

    private void onClickBottomNavView(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_shop:
                setCurrentFragment(shopFragment);
                item.setChecked(true);
                break;
            case R.id.button_sales:
                setCurrentFragment(salesFragment);
                item.setChecked(true);
                break;
            case R.id.button_news:
                setCurrentFragment(newsFragment);
                item.setChecked(true);
                break;
            case R.id.button_contact:
                setCurrentFragment(contactFragment);
                item.setChecked(true);
                break;
            case R.id.button_about_us:
                setCurrentFragment(aboutUsFragment);
                item.setChecked(true);
                break;
        }
    }

    private void setCurrentFragment(@NonNull Fragment currentFragment){
        this.currentFragment = currentFragment;
        fragmentManager.beginTransaction()
                .hide(newsFragment)
                .hide(contactFragment)
                .hide(shopFragment)
                .hide(settingsFragment)
                .hide(salesFragment)
                .hide(aboutUsFragment)
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(currentFragment)
                .commit();
    }

    private void setListeners() {
        bottomNavView.setOnNavigationItemSelectedListener(item -> {
            onClickBottomNavView(item);
            return true;
        });
    }
}