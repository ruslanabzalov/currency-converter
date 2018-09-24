package com.ruslan.tinkoffcurrencyconverter.data.local.currencies;

import java.util.List;

public class CurrenciesList {

    private List<Currency> mCurrencies;

    public List<Currency> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        mCurrencies = currencies;
    }
}
