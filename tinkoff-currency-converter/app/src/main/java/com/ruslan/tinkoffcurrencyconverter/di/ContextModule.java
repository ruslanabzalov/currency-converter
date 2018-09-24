package com.ruslan.tinkoffcurrencyconverter.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Класс, предоставляющий контекст приложения.
 */
@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    /**
     * Метод, предоставляющий контекст приложения.
     * @return Контекст приложения.
     */
    @Provides
    @Singleton
    public Context provideAppContext() {
        return mContext.getApplicationContext();
    }
}
