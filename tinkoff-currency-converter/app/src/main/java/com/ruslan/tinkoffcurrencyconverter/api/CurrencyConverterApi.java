package com.ruslan.tinkoffcurrencyconverter.api;

import com.ruslan.tinkoffcurrencyconverter.data.local.Conversions;
import com.ruslan.tinkoffcurrencyconverter.data.local.currencies.CurrenciesResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Интерфейс, описывающий API сервиса для конвертирования валюты.
 */
public interface CurrencyConverterApi {

    /**
     * Метод получения списка всех конвертируемых валют.
     * @return Список конвертируемых валют.
     */
    @GET("currencies")
    Flowable<CurrenciesResult> getAllCurrencies();

    /**
     * Метод конвертации валют.
     * @param conversionType Тип конвертирования.
     * @param compactType
     * @return Результат конвертации.
     */
    @GET("convert")
    Flowable<Conversions> convert(
            @Query("q") String conversionType, @Query("compact") String compactType
    );
}
