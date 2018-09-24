package com.ruslan.tinkoffcurrencyconverter.converter;

import com.ruslan.tinkoffcurrencyconverter.App;
import com.ruslan.tinkoffcurrencyconverter.data.Repository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ConverterPresenter implements ConverterContract.ConverterPresenter {

    @Inject
    Repository mRepository;

    private ConverterContract.ConverterView mConverterView;
    private Disposable mDisposable;

    ConverterPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void attachView(ConverterContract.ConverterView converterView) {
        mConverterView = converterView;
    }

    @Override
    public void detachView() {
        if (mConverterView != null) {
            mConverterView = null;
        }
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void convert(String convertingString) {
        mDisposable = mRepository.convertCurrencies(convertingString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(conversionResults -> {
                    if (mConverterView != null) {
                        mConverterView.showConvertingResult(conversionResults.get(0).getResult());
                    }
                })
                .doOnError(throwable -> {
                    if (mConverterView != null) {
                        mConverterView.showErrorMessage(throwable);
                    }
                })
                .subscribe();
    }
}
