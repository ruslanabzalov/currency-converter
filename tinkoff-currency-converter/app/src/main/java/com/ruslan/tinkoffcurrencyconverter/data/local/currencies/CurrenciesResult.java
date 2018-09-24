package com.ruslan.tinkoffcurrencyconverter.data.local.currencies;

import com.google.gson.annotations.SerializedName;

public class CurrenciesResult {

    @SerializedName("results")
    private CurrenciesList mCurrenciesList;

    public CurrenciesList getCurrenciesList() {
        return mCurrenciesList;
    }

    public void setCurrenciesList(CurrenciesList currenciesList) {
        mCurrenciesList = currenciesList;
    }
}
