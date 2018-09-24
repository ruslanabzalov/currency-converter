package com.ruslan.tinkoffcurrencyconverter.history;

import com.ruslan.tinkoffcurrencyconverter.BaseContract;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.History;

import java.util.List;

public interface HistoryContract {

    interface HistoryView extends BaseContract.BaseView {

        void showHistoryList(List<History> histories);
    }

    interface HistoryPresenter extends BaseContract.BasePresenter<HistoryView> {

        void loadHistory();
    }
}
