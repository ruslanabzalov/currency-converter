package com.ruslan.tinkoffcurrencyconverter.history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruslan.tinkoffcurrencyconverter.R;
import com.ruslan.tinkoffcurrencyconverter.data.local.history.History;

import java.util.List;

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> mHistories;

    HistoryAdapter(List<History> histories) {
        mHistories = histories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = inflater.inflate(R.layout.list_item_history, viewGroup, false);
        return new HistoryViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder historyViewHolder, int i) {
        historyViewHolder.bind(mHistories.get(i));
    }

    @Override
    public int getItemCount() {
        return mHistories.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mHistoryFromTextView;
        private TextView mHistoryToTextView;
        private TextView mHistoryResultTextView;

        private History mHistory;

        HistoryViewHolder(View rootView) {
            super(rootView);
            mHistoryFromTextView = itemView.findViewById(R.id.history_from_text_view);
            mHistoryToTextView = itemView.findViewById(R.id.history_to_text_view);
            mHistoryResultTextView = itemView.findViewById(R.id.conversion_result_text_view);
        }

        void bind(History history) {
            mHistory = history;
            mHistoryFromTextView.setText(mHistory.getCurrencyFromName());
            mHistoryToTextView.setText(mHistory.getCurrencyToName());
            mHistoryResultTextView.setText(mHistory.getConvertingResult() + "");
        }
    }
}
