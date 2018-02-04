package ru.mbg.nczd.feature.news.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class NewsDeserializer implements JsonDeserializer<List<News>> {
    @Override
    public List<News> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject)json;
        JsonArray news = object.getAsJsonArray("data");
        List<News> newsList = new ArrayList<>();
        for (JsonElement element : news){
            JsonObject jo = (JsonObject)element;
            News n = new News();
            //n.setData(jo.get("data").getAsString());
            n.setTitle(jo.get("Title").getAsString());
            n.setText(jo.get("Text").getAsString());
            if (!jo.get("Imgs").isJsonNull()){
                JsonArray imgs = jo.getAsJsonArray("Imgs");
                List<String> images = new ArrayList<>();
                for (JsonElement e : imgs){
                    images.add(e.toString().replaceAll("\"", ""));
                }
                n.setImages(images);
            }
            if (!jo.get("PageLink").isJsonNull()) {
                n.setPageLink(jo.get("PageLink").getAsString());
            }
            newsList.add(n);
        }
        return newsList;
    }
}
