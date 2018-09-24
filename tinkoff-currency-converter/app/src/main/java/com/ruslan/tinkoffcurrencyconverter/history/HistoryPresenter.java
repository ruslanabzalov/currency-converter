package com.ruslan.tinkoffcurrencyconverter.history;

import com.ruslan.tinkoffcurrencyconverter.App;
import com.ruslan.tinkoffcurrencyconverter.data.Repository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryPresenter implements HistoryContract.HistoryPresenter {

    @Inject
    Repository mRepository;

    private HistoryContract.HistoryView mHistoryView;
    private Disposable mDisposable;

    HistoryPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void attachView(HistoryContract.HistoryView historyView) {
        mHistoryView = historyView;
    }

    @Override
    public void detachView() {
        if (mHistoryView != null) {
            mHistoryView = null;
        }
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void loadHistory() {
        mDisposable = mRepository.getHistories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(histories -> {
                    if (histories.size() != 0) {
                        mHistoryView.showHistoryList(histories);
                    }
                })
                .doOnError(throwable -> mHistoryView.showErrorMessage(throwable))
                .subscribe();
    }
}
