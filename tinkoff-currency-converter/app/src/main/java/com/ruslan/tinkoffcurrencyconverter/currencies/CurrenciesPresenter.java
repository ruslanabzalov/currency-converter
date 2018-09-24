package com.ruslan.tinkoffcurrencyconverter.currencies;

import com.ruslan.tinkoffcurrencyconverter.App;
import com.ruslan.tinkoffcurrencyconverter.data.Repository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CurrenciesPresenter implements CurrenciesContract.CurrenciesPresenter {

    private CurrenciesContract.CurrenciesView mCurrenciesView;

    private Disposable mDisposable;

    @Inject
    Repository mRepository;

    CurrenciesPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void attachView(CurrenciesContract.CurrenciesView currenciesView) {
        mCurrenciesView = currenciesView;
    }

    @Override
    public void detachView() {
        if (mCurrenciesView != null) {
            mCurrenciesView = null;
        }
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void loadCurrencies() {
        if (mCurrenciesView != null) {
            mCurrenciesView.showProgressBar();
        }
        mDisposable = mRepository.getCurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(currencies -> {
                    if (currencies.size() != 0) {
                        mCurrenciesView.hideProgressBar();
                        mCurrenciesView.showCurrencies(currencies);
                    }
                })
                .doOnError(throwable -> mCurrenciesView.showErrorMessage(throwable))
                .subscribe();
    }
}
