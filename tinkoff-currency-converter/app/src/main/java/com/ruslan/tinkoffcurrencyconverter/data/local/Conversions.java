package com.ruslan.tinkoffcurrencyconverter.data.local;

import java.util.List;

/**
 * Класс, описывающий список результатов конвертирования валют.
 */
public class Conversions {

    private List<ConversionResult> mConversionResults;

    public List<ConversionResult> getConversionResults() {
        return mConversionResults;
    }

    public void setConversionResults(List<ConversionResult> conversionResults) {
        mConversionResults = conversionResults;
    }
}
