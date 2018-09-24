package com.ruslan.tinkoffcurrencyconverter.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.CurrencyDao;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.History;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.HistoryDao;

@Database(entities = {Currency.class, History.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CurrencyDao currencyDao();

    public abstract HistoryDao historyDao();
}
