package com.ruslan.tinkoffcurrencyconverter.data.remote;

import com.ruslan.tinkoffcurrencyconverter.App;
import com.ruslan.tinkoffcurrencyconverter.api.CurrencyConverterApi;
import com.ruslan.tinkoffcurrencyconverter.data.DataSource;
import com.ruslan.tinkoffcurrencyconverter.data.local.ConversionResult;
import com.ruslan.tinkoffcurrencyconverter.data.local.Conversions;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RemoteDataSource implements DataSource {

    @Inject
    CurrencyConverterApi mCurrencyConverterApi;

    public RemoteDataSource() {
        App.getComponent().inject(this);
    }

    @Override
    public Flowable<List<Currency>> getCurrencies() {
        return mCurrencyConverterApi.getAllCurrencies()
                .map(currenciesResult -> currenciesResult.getCurrenciesList().getCurrencies());
    }

    public Flowable<List<ConversionResult>> convertCurrencies(String convertString) {
        return mCurrencyConverterApi.convert(convertString, "ultra")
                .map(Conversions::getConversionResults);
    }
}
