package com.ruslan.tinkoffcurrencyconverter.data.local.currencies;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CurrenciesDeserializer implements JsonDeserializer<CurrenciesResult> {

    @Override
    public CurrenciesResult deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context) throws JsonParseException {
        JsonObject rootJsonObject = json.getAsJsonObject();
        rootJsonObject = rootJsonObject.getAsJsonObject("results");
        Set<String> currencyObjectNames = rootJsonObject.keySet();
        List<Currency> currencies = new ArrayList<>();
        Currency currency;
        for (String currencyObjectName : currencyObjectNames) {
            JsonObject currencyObject = rootJsonObject.getAsJsonObject(currencyObjectName);
            currency = new Currency();
            currency.setId(currencyObject.get("id").getAsString());
            currency.setCurrencyName(currencyObject.get("currencyName").getAsString());
            if (currencyObject.get("currencySymbol") != null) {
                currency.setCurrencySymbol(currencyObject.get("currencySymbol").getAsString());
            }
            currencies.add(currency);
        }
        CurrenciesList currenciesList = new CurrenciesList();
        currenciesList.setCurrencies(currencies);
        CurrenciesResult currenciesResult = new CurrenciesResult();
        currenciesResult.setCurrenciesList(currenciesList);
        return currenciesResult;
    }
}
