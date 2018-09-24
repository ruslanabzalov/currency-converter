package com.ruslan.tinkoffcurrencyconverter.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ruslan.tinkoffcurrencyconverter.api.CurrencyConverterApi;
import com.ruslan.tinkoffcurrencyconverter.data.local.Conversions;
import com.ruslan.tinkoffcurrencyconverter.data.local.ConversionsDeserializer;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.CurrenciesDeserializer;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.CurrenciesResult;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Модуль Dagger, предоставляющий объекты для работы с сетью.
 */
@Module
public class NetworkModule {

    /**
     * Метод, предоставляющий пользовательский OkHttp-клиент.
     * @return Экземпляр OkHttp-клиента.
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Метод, предоставляющий пользовательский объект Gson.
     * @return Экземпляр Gson.
     */
    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(CurrenciesResult.class, new CurrenciesDeserializer())
                .registerTypeAdapter(Conversions.class, new ConversionsDeserializer())
                .create();
    }

    /**
     * Метод, предоставляющий пользовательский объект Retrofit.
     * @param okHttpClient OkHttp-клиент.
     * @param gson Экземпляр Gson.
     * @return Экземпляр Retrofit.
     */
    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://free.currencyconverterapi.com/api/v6/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * Метод, предоставляющий готовый объект для работы с API.
     * @param retrofit Экземпляр Retrofit.
     * @return Готовый объект для работы с API.
     */
    @Provides
    @Singleton
    public CurrencyConverterApi provideCurrencyConverterApiService(Retrofit retrofit) {
        return retrofit.create(CurrencyConverterApi.class);
    }
}
