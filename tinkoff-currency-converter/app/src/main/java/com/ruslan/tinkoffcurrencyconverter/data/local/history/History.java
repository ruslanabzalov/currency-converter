package com.ruslan.tinkoffcurrencyconverter.data.local.history;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "history")
public class History {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "from")
    private String mCurrencyFromName;

    @ColumnInfo(name = "to")
    private String mCurrencyToName;

    @ColumnInfo(name = "result")
    private double mConvertingResult;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getCurrencyFromName() {
        return mCurrencyFromName;
    }

    public void setCurrencyFromName(String currencyFromName) {
        mCurrencyFromName = currencyFromName;
    }

    public String getCurrencyToName() {
        return mCurrencyToName;
    }

    public void setCurrencyToName(String currencyToName) {
        mCurrencyToName = currencyToName;
    }

    public double getConvertingResult() {
        return mConvertingResult;
    }

    public void setConvertingResult(double convertingResult) {
        mConvertingResult = convertingResult;
    }
}
