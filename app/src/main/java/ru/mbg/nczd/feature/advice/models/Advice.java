package ru.mbg.nczd.feature.advice.models;

import ru.mbg.nczd.feature.BaseModel;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class Advice extends BaseModel {

    private String data;

    private String title;

    private String source;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
