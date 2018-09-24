package com.ruslan.tinkoffcurrencyconverter.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruslan.tinkoffcurrencyconverter.R;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.History;

import java.util.List;

public class HistoryFragment extends Fragment implements HistoryContract.HistoryView {

    private HistoryContract.HistoryPresenter mHistoryPresenter;

    private TextView mHistoryIsEmptyTextView;
    private RecyclerView mHistoryRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHistoryPresenter = new HistoryPresenter();
        mHistoryPresenter.attachView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mHistoryPresenter.loadHistory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHistoryPresenter != null) {
            mHistoryPresenter.detachView();
        }
    }

    private void initViews(@NonNull View rootView) {
        mHistoryIsEmptyTextView = rootView.findViewById(R.id.history_is_empty_text_view);
        mHistoryRecyclerView = rootView.findViewById(R.id.history_recycler_view);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void showHistoryList(List<History> histories) {
        mHistoryIsEmptyTextView.setVisibility(View.GONE);
        mHistoryRecyclerView.setVisibility(View.VISIBLE);
        HistoryAdapter historyAdapter = new HistoryAdapter(histories);
        mHistoryRecyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        Log.e("POP", throwable.getMessage(), throwable);
    }
}
