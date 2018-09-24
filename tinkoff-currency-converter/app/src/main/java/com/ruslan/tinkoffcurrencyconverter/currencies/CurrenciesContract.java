package com.ruslan.tinkoffcurrencyconverter.currencies;

import com.ruslan.tinkoffcurrencyconverter.BaseContract;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;

import java.util.List;

public interface CurrenciesContract {

    interface CurrenciesView extends BaseContract.BaseView {

        void showCurrencies(List<Currency> currencies);

        void backToMainFragment(Currency currency);

        void showProgressBar();

        void hideProgressBar();
    }

    interface CurrenciesPresenter extends BaseContract.BasePresenter<CurrenciesView> {

        void loadCurrencies();
    }
}
