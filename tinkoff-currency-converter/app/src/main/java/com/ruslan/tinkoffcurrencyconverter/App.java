package com.ruslan.tinkoffcurrencyconverter;

import android.app.Application;

import com.ruslan.tinkoffcurrencyconverter.di.AppComponent;
import com.ruslan.tinkoffcurrencyconverter.di.ContextModule;
import com.ruslan.tinkoffcurrencyconverter.di.DaggerAppComponent;
import com.ruslan.tinkoffcurrencyconverter.di.DataSourcesModule;
import com.ruslan.tinkoffcurrencyconverter.di.NetworkModule;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .networkModule(new NetworkModule())
                .dataSourcesModule(new DataSourcesModule())
                .build();
    }
}
