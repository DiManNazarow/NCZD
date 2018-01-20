package ru.mbg.nczd.feature.news.models;

import ru.mbg.nczd.feature.BaseModel;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class News extends BaseModel {

    public String title;

    public String content;

    public String data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
