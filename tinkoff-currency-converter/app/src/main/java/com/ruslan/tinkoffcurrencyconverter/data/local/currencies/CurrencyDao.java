package com.ruslan.tinkoffcurrencyconverter.data.local.currencies;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrencies(List<Currency> currencies);

    @Query("SELECT * FROM currencies")
    Flowable<List<Currency>> getAllCurrencies();

    @Query("DELETE FROM currencies")
    void clearTable();
}
