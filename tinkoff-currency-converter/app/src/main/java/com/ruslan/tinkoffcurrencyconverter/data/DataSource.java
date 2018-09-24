package com.ruslan.tinkoffcurrencyconverter.data;

import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSource {

    Flowable<List<Currency>> getCurrencies();
}
