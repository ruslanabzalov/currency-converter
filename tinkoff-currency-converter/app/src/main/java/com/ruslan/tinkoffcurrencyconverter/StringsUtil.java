package com.ruslan.tinkoffcurrencyconverter;

public final class StringsUtil {

    private StringsUtil() {}

    public static String makeCurrencyMainText(String currencyId, String currencySymbol) {
        if (currencySymbol == null) {
            return currencyId;
        } else {
            return String.format("%s, %s", currencyId, currencySymbol);
        }
    }

    public static String createCurrenciesRequestString(String currencyFrom, String currencyTo) {
        return String.format("%s_%s", currencyFrom, currencyTo);
    }
}
