package com.ruslan.tinkoffcurrencyconverter.data.local;

import com.ruslan.tinkoffcurrencyconverter.App;
import com.ruslan.tinkoffcurrencyconverter.data.DataSource;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.CurrencyDao;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.History;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.HistoryDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalDataSource implements DataSource {

    @Inject
    AppDatabase mAppDatabase;

    private CurrencyDao mCurrencyDao;
    private HistoryDao mHistoryDao;

    public LocalDataSource() {
        App.getComponent().inject(this);
        mCurrencyDao = mAppDatabase.currencyDao();
        mHistoryDao = mAppDatabase.historyDao();
    }

    public Flowable<List<Currency>> getCurrencies() {
        return mCurrencyDao.getAllCurrencies();
    }

    public void saveCurrencies(List<Currency> currencies) {
        mCurrencyDao.insertCurrencies(currencies);
    }

    public Flowable<List<History>> getHistories() {
        return mHistoryDao.getAllHistory();
    }

    public void saveHistory(String conversionString, double result) {
        History history = new History();
        history.setCurrencyFromName(conversionString.substring(0, 3));
        history.setCurrencyToName(conversionString.substring(4, 7));
        history.setConvertingResult(result);
        mHistoryDao.insert(history);
    }

    public Single<Double> getLastFromToHistory(String from, String to) {
        return mHistoryDao.getLastFromToHistory(from, to);
    }
}
