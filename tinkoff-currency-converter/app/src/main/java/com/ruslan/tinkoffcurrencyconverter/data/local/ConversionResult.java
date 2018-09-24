package com.ruslan.tinkoffcurrencyconverter.data.local;

/**
 * Класс, описывающий один результат конвертирования.
 */
public class ConversionResult {

    private String mConversionString;
    private double mResult;

    public String getConversionString() {
        return mConversionString;
    }

    public void setConversionString(String conversionString) {
        mConversionString = conversionString;
    }

    public double getResult() {
        return mResult;
    }

    public void setResult(double result) {
        mResult = result;
    }
}
