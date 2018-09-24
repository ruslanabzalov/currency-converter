package com.ruslan.tinkoffcurrencyconverter.data.local;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConversionsDeserializer implements JsonDeserializer<Conversions> {

    @Override
    public Conversions deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Set<String> keys = jsonObject.keySet();
        Conversions conversions = new Conversions();
        List<ConversionResult> conversionResults = new ArrayList<>();
        ConversionResult conversionResult;
        for (String key : keys) {
            conversionResult = new ConversionResult();
            conversionResult.setConversionString(key);
            conversionResult.setResult(jsonObject.get(key).getAsDouble());
            conversionResults.add(conversionResult);
        }
        conversions.setConversionResults(conversionResults);
        return conversions;
    }
}
