package ru.mbg.nczd.feature.news.models;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Дмитрий on 02.02.2018.
 */

public class NewsStatDeserializer implements JsonDeserializer<NewsStat>{
    @Override
    public NewsStat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        NewsStat newsStat = new NewsStat();
        JsonObject jsonObject = (JsonObject)json;
        JsonObject stat = jsonObject.getAsJsonObject("data");
        newsStat.setTotalPages(stat.get("TotalPages").getAsInt());
        newsStat.setPostPerPage(stat.get("PostsPerPage").getAsInt());
        return newsStat;
    }
}
