package com.ruslan.tinkoffcurrencyconverter.data.local.currencies;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Класс, описывающий конкретную валюту.
 */
@Entity(tableName = "currencies")
public class Currency {

    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    private String mId;

    @ColumnInfo(name = "name")
    private String mCurrencyName;

    @ColumnInfo(name = "symbol")
    private String mCurrencySymbol;

    @NonNull
    public String getId() {
        return mId;
    }

    public void setId(@NonNull String id) {
        mId = id;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        mCurrencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return mCurrencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        mCurrencySymbol = currencySymbol;
    }
}
