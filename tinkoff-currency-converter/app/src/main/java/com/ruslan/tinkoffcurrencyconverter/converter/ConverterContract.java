package com.ruslan.tinkoffcurrencyconverter.converter;

import com.ruslan.tinkoffcurrencyconverter.BaseContract;
import com.ruslan.tinkoffcurrencyconverter.data.local.ConversionResult;

/**
 * Интерфейс, описывающий контракт между ConverterView и ConverterPresenter.
 */
public interface ConverterContract {

    /**
     * Интерфейс описывающий ConverterView.
     */
    interface ConverterView extends BaseContract.BaseView {

        void showCurrenciesListUi(String currencyType);

        void showConvertingResult(double conversionResult);
    }

    /**
     * Интерфейс, описывающий ConverterPresenter.
     */
    interface ConverterPresenter extends BaseContract.BasePresenter<ConverterView> {

        void convert(String convertingString);
    }
}
