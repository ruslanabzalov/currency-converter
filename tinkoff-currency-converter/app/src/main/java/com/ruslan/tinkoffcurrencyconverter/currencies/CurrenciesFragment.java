package com.ruslan.tinkoffcurrencyconverter.currencies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ruslan.tinkoffcurrencyconverter.R;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.Currency;

import java.util.List;
import java.util.Objects;

/**
 * Фрагмент, отображающий список валют.
 */
public class CurrenciesFragment extends Fragment implements CurrenciesContract.CurrenciesView {

    private static final String RESULT_EXTRA = "result";

    private CurrenciesContract.CurrenciesPresenter mCurrenciesPresenter;

    private ProgressBar mCurrenciesProgressBar;
    private RecyclerView mCurrenciesRecyclerView;

    public static String getResultFromIntent(Intent data) {
        return data.getStringExtra(RESULT_EXTRA);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getActivity()).setTitle(R.string.currencies_fragment_title);

        mCurrenciesPresenter = new CurrenciesPresenter();
        mCurrenciesPresenter.attachView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_currencies, container, false);
        initViews(rootView);
        return rootView;
    }

    /**
     * Метод инициализации элементов типа <code>View</code>.
     * @param rootView Корневой элемент типа <code>View</code>.
     */
    private void initViews(@NonNull View rootView) {
        mCurrenciesProgressBar = rootView.findViewById(R.id.currencies_progress_bar);
        mCurrenciesRecyclerView = rootView.findViewById(R.id.currencies_recycler_view);
        mCurrenciesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isNetworkAvailableAndConnected()) {
            mCurrenciesPresenter.loadCurrencies();
        } else {
            Toast.makeText(getActivity(), "Возможно, нет подключения к интернету.",
                    Toast.LENGTH_SHORT).show();
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
        }
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCurrenciesPresenter.detachView();
    }

    @Override
    public void showCurrencies(List<Currency> currencies) {
        CurrenciesAdapter currenciesAdapter =
                new CurrenciesAdapter(currencies, this::backToMainFragment);
        mCurrenciesRecyclerView.setAdapter(currenciesAdapter);
    }

    @Override
    public void backToMainFragment(Currency currency) {
        String currencyId = currency.getId();
        Intent result = new Intent();
        result.putExtra(RESULT_EXTRA, currencyId);
        Objects.requireNonNull(getTargetFragment(), "Target fragment must not be null!")
                .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, result);
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showProgressBar() {
        mCurrenciesProgressBar.setVisibility(View.VISIBLE);
        mCurrenciesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        mCurrenciesProgressBar.setVisibility(View.GONE);
        mCurrenciesRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        Log.e("LOL", throwable.getMessage(), throwable);
        // TODO: Добавить SnackBar, который выводит сообщение об ошибке.
    }
}
