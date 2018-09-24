package com.ruslan.tinkoffcurrencyconverter.currencies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruslan.tinkoffcurrencyconverter.R;
import com.ruslan.tinkoffcurrencyconverter.RecyclerViewItemClickListener;
import com.ruslan.tinkoffcurrencyconverter.StringsUtil;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;

import java.util.List;

class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder> {

    private List<Currency> mCurrencies;
    private RecyclerViewItemClickListener<Currency> mItemClickListener;

    CurrenciesAdapter(List<Currency> currencies,
                      RecyclerViewItemClickListener<Currency> itemClickListener) {
        mCurrencies = currencies;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CurrenciesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = inflater.inflate(R.layout.list_item_currency, viewGroup, false);
        return new CurrenciesViewHolder(rootView, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrenciesViewHolder currenciesViewHolder, int i) {
        currenciesViewHolder.bind(mCurrencies.get(i));
    }

    @Override
    public int getItemCount() {
        return mCurrencies.size();
    }

    static class CurrenciesViewHolder extends RecyclerView.ViewHolder {

        private TextView mMainCurrencyTextView;
        private TextView mCurrencyNameTextView;

        private Currency mCurrency;
        private RecyclerViewItemClickListener<Currency> mItemClickListener;

        CurrenciesViewHolder(@NonNull View itemView,
                             RecyclerViewItemClickListener<Currency> itemClickListener) {
            super(itemView);
            mMainCurrencyTextView = itemView.findViewById(R.id.main_currency_text_view);
            mCurrencyNameTextView = itemView.findViewById(R.id.currency_name_text_view);
            mItemClickListener = itemClickListener;
            itemView.setOnClickListener(view -> mItemClickListener.onItemClick(mCurrency));
        }

        void bind(Currency currency) {
            mCurrency = currency;
            String mainCurrencyText = StringsUtil
                    .makeCurrencyMainText(mCurrency.getId(), mCurrency.getCurrencySymbol());
            mMainCurrencyTextView.setText(mainCurrencyText);
            mCurrencyNameTextView.setText(mCurrency.getCurrencyName());
        }
    }
}
