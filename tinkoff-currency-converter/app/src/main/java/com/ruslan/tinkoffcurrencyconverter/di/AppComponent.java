package com.ruslan.tinkoffcurrencyconverter.di;

import com.ruslan.tinkoffcurrencyconverter.converter.ConverterPresenter;
import com.ruslan.tinkoffcurrencyconverter.currencies.CurrenciesPresenter;
import com.ruslan.tinkoffcurrencyconverter.data.Repository;
import com.ruslan.tinkoffcurrencyconverter.data.local.LocalDataSource;
import com.ruslan.tinkoffcurrencyconverter.data.remote.RemoteDataSource;
import com.ruslan.tinkoffcurrencyconverter.history.HistoryPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ContextModule.class, NetworkModule.class, DataSourcesModule.class})
@Singleton
public interface AppComponent {

    void inject(RemoteDataSource remoteDataSource);

    void inject(Repository repository);

    void inject(ConverterPresenter converterPresenter);

    void inject(CurrenciesPresenter currenciesPresenter);

    void inject(LocalDataSource localDataSource);

    void inject(HistoryPresenter historyPresenter);
}
