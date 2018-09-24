package com.ruslan.tinkoffcurrencyconverter.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ruslan.tinkoffcurrencyconverter.R;

import java.util.Objects;

/**
 * Активность, отображающая историю конвертаций.
 */
public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle(R.string.history_title);
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar, "ActionBar must not be null!")
                .setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.history_activity_fragment_container) == null) {
            Fragment historyFragment = new HistoryFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.history_activity_fragment_container, historyFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
