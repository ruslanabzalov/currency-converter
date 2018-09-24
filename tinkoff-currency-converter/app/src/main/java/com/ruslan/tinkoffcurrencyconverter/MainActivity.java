package com.ruslan.tinkoffcurrencyconverter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ruslan.tinkoffcurrencyconverter.converter.ConverterFragment;

import java.util.Objects;

/**
 * Класс, описывающий главную активность приложения.
 */
public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        if (mFragmentManager.findFragmentById(R.id.main_activity_fragment_container) == null) {
            Fragment mainFragment = new ConverterFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.main_activity_fragment_container, mainFragment)
                    .commit();
        }
        mFragmentManager.addOnBackStackChangedListener(this::checkUpButton);
        checkUpButton();
    }

    @Override
    public boolean onSupportNavigateUp() {
        mFragmentManager.popBackStack();
        return true;
    }

    /**
     * Метод проверки BackStack'а фрагментов.
     */
    private void checkUpButton() {
        Objects.requireNonNull(getSupportActionBar(), "ActionBar must not be null!")
                .setDisplayHomeAsUpEnabled(mFragmentManager.getBackStackEntryCount() > 0);
    }
}
