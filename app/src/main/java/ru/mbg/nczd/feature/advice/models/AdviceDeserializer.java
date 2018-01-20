package ru.mbg.nczd.feature.advice.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.mbg.nczd.feature.news.models.News;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class AdviceDeserializer implements JsonDeserializer<List<Advice>> {
    @Override
    public List<Advice> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject)json;
        JsonArray news = object.getAsJsonArray("advices");
        List<Advice> adviceList = new ArrayList<>();
        for (JsonElement element : news){
            JsonObject jo = (JsonObject)element;
            Advice n = new Advice();
            n.setData(jo.get("data").getAsString());
            n.setTitle(jo.get("title").getAsString());
            n.setSource(jo.get("source").getAsString());
            adviceList.add(n);
        }
        return adviceList;
    }
}
