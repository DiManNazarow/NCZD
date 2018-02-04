package ru.mbg.nczd.feature.news.models;

import java.util.List;

import ru.mbg.nczd.feature.BaseModel;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class News extends BaseModel {

    public String title;

    public String text;

    public String pageLink;

    public List<String> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPageLink() {
        return pageLink;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
