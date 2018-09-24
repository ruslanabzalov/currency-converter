package com.ruslan.tinkoffcurrencyconverter.data;

import com.ruslan.tinkoffcurrencyconverter.App;
import com.ruslan.tinkoffcurrencyconverter.data.local.ConversionResult;
import com.ruslan.tinkoffcurrencyconverter.data.local.LocalDataSource;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.History;
import com.ruslan.tinkoffcurrencyconverter.data.remote.RemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class Repository {

    @Inject
    RemoteDataSource mRemoteDataSource;

    @Inject
    LocalDataSource mLocalDataSource;

    public Repository() {
        App.getComponent().inject(this);
    }

    public Flowable<List<Currency>> getCurrencies() {
        return mLocalDataSource.getCurrencies()
                .flatMap(currencies -> {
                    if (currencies.size() == 0) {
                        return getAndSaveRemoteCurrencies();
                    } else {
                        return Flowable.just(currencies);
                    }
                });
    }

    private Flowable<List<Currency>> getAndSaveRemoteCurrencies() {
        return mRemoteDataSource.getCurrencies()
                .doOnNext(currencies -> mLocalDataSource.saveCurrencies(currencies));
    }

    public Flowable<List<History>> getHistories() {
        return mLocalDataSource.getHistories();
    }

    public Single<Double> getLastFromToHistory(String from, String to) {
        return mLocalDataSource.getLastFromToHistory(from ,to);
    }

    public Flowable<List<ConversionResult>> convertCurrencies(String converterString) {
        return mRemoteDataSource.convertCurrencies(converterString)
                .doOnNext(conversionResults -> mLocalDataSource
                        .saveHistory(converterString, conversionResults.get(0).getResult()));
    }
}
