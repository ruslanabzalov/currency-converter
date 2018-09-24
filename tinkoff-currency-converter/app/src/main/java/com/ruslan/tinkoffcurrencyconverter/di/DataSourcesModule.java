package com.ruslan.tinkoffcurrencyconverter.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ruslan.tinkoffcurrencyconverter.data.Repository;
import com.ruslan.tinkoffcurrencyconverter.data.local.AppDatabase;
import com.ruslan.tinkoffcurrencyconverter.data.local.LocalDataSource;
import com.ruslan.tinkoffcurrencyconverter.data.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourcesModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Context applicationContext) {
        return Room.databaseBuilder(applicationContext, AppDatabase.class, "converter-database")
                .build();
    }

    @Provides
    @Singleton
    public RemoteDataSource provideRemoteDataSource() {
        return new RemoteDataSource();
    }

    @Provides
    @Singleton
    public LocalDataSource provideLocalDataSource() {
        return new LocalDataSource();
    }

    @Provides
    @Singleton
    public Repository providesRepository() {
        return new Repository();
    }
}
