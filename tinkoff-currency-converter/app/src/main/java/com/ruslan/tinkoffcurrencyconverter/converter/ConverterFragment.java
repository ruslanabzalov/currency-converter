package com.ruslan.tinkoffcurrencyconverter.converter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ruslan.tinkoffcurrencyconverter.R;
import com.ruslan.tinkoffcurrencyconverter.StringsUtil;
import com.ruslan.tinkoffcurrencyconverter.currencies.CurrenciesFragment;
import com.ruslan.tinkoffcurrencyconverter.history.HistoryActivity;

import java.util.Locale;
import java.util.Objects;

/**
 * Класс, описывающий главный фрагмент для конвертирования валюты.
 */
public class ConverterFragment extends Fragment implements ConverterContract.ConverterView {

    private static final String STATE_CURRENCY_FROM = "currency_from";
    private static final String STATE_CURRENCY_TO = "currency_to";
    private static final String STATE_IS_RESULT_VISIBLE = "is_result_visible";
    private static final String STATE_RESULT = "result";

    private static final String TAG_CONVERTER_FRAGMENT = "ConverterFragment";

    private static final int CURRENCY_FROM_FRAGMENT_REQUEST_CODE = 777;
    private static final int CURRENCY_TO_FRAGMENT_REQUEST_CODE = 888;

    private FragmentManager mFragmentManager;

    private TextView mCurrencyFromTextView;
    private TextView mCurrencyToTextView;
    private TextView mCurrencyConversionResultLabel;
    private TextView mCurrencyConversionResultTextView;
    private Button mConvertButton;

    private ConverterContract.ConverterPresenter mConverterPresenter;

    private String mCurrencyFromId;
    private String mCurrencyToId;
    private boolean mIsResultVisible;
    private double mResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mFragmentManager = Objects.requireNonNull(getActivity(), "Activity must not be null!")
                .getSupportFragmentManager();

        if (savedInstanceState != null) {
            mCurrencyFromId = savedInstanceState.getString(STATE_CURRENCY_FROM, null);
            mCurrencyToId = savedInstanceState.getString(STATE_CURRENCY_TO, null);
            mIsResultVisible = savedInstanceState.getBoolean(STATE_IS_RESULT_VISIBLE, false);
            if (mIsResultVisible) {
                mResult = savedInstanceState.getDouble(STATE_RESULT, 0.0);
            }
        }

        mConverterPresenter = new ConverterPresenter();
        mConverterPresenter.attachView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_converter, container, false);
        Objects.requireNonNull(getActivity(), "Activity must not be null!")
                .setTitle(R.string.main_activity_title);
        initViews(rootView);
        return rootView;
    }

    /**
     * Метод инициализации всех элементов типа <code>View</code>.
     * @param rootView Корневой элемент типа <code>View</code>.
     */
    private void initViews(@NonNull View rootView) {
        mCurrencyFromTextView = rootView.findViewById(R.id.currency_from_text_view);
        mCurrencyFromTextView.setOnClickListener(view -> showCurrenciesListUi("from"));
        if (mCurrencyFromId != null) {
            mCurrencyFromTextView.setText(mCurrencyFromId);
        }
        mCurrencyToTextView = rootView.findViewById(R.id.currency_to_text_view);
        mCurrencyToTextView.setOnClickListener(view -> showCurrenciesListUi("to"));
        if (mCurrencyToId != null) {
            mCurrencyToTextView.setText(mCurrencyToId);
        }
        mCurrencyConversionResultLabel =
                rootView.findViewById(R.id.currency_conversion_result_label);
        mCurrencyConversionResultTextView =
                rootView.findViewById(R.id.currency_conversion_result_text_view);
        mConvertButton = rootView.findViewById(R.id.convert_button);
        mConvertButton.setOnClickListener(view -> {
            if (mCurrencyFromId != null && mCurrencyToId != null) {
                String converterString =
                        StringsUtil.createCurrenciesRequestString(mCurrencyFromId, mCurrencyToId);
                if (isNetworkAvailableAndConnected()) {
                    mConverterPresenter.convert(converterString);
                } else {
                    Toast.makeText(getActivity(), "Возможно, нет подключения к интернету.",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Вы не выбрали параметры", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        if (mIsResultVisible) {
            mCurrencyConversionResultLabel.setVisibility(View.VISIBLE);
            mCurrencyConversionResultTextView.setVisibility(View.VISIBLE);
            mCurrencyConversionResultTextView
                    .setText(String.format(Locale.getDefault(), "%f", mResult));
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
        if (mConverterPresenter != null) {
            mConverterPresenter.detachView();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCurrencyFromId != null) {
            outState.putString(STATE_CURRENCY_FROM, mCurrencyFromId);
        }
        if (mCurrencyToId != null) {
            outState.putString(STATE_CURRENCY_TO, mCurrencyToId);
        }
        if (mIsResultVisible) {
            outState.putBoolean(STATE_IS_RESULT_VISIBLE, mIsResultVisible);
            outState.putDouble(STATE_RESULT,
                    Double.parseDouble(mCurrencyConversionResultTextView.getText().toString()));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CURRENCY_FROM_FRAGMENT_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    mCurrencyFromId = CurrenciesFragment.getResultFromIntent(data);
                }
                break;
            case CURRENCY_TO_FRAGMENT_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    mCurrencyToId = CurrenciesFragment.getResultFromIntent(data);
                }
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_converter, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.conversion_history_menu_item:
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showCurrenciesListUi(String currencyType) {
        mIsResultVisible = false;
        Fragment currenciesFragment = new CurrenciesFragment();
        if (currencyType.equals("from")) {
            currenciesFragment.setTargetFragment(this, CURRENCY_FROM_FRAGMENT_REQUEST_CODE);
        } else {
            currenciesFragment.setTargetFragment(this, CURRENCY_TO_FRAGMENT_REQUEST_CODE);
        }
        mFragmentManager.beginTransaction()
                .replace(R.id.main_activity_fragment_container, currenciesFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showConvertingResult(double conversionResult) {
        mIsResultVisible = true;
        mCurrencyConversionResultLabel.setVisibility(View.VISIBLE);
        mCurrencyConversionResultTextView.setVisibility(View.VISIBLE);
        mCurrencyConversionResultTextView
                .setText(String.format(Locale.getDefault(), "%f", conversionResult));
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        Log.i(TAG_CONVERTER_FRAGMENT, throwable.getMessage());
    }
}
