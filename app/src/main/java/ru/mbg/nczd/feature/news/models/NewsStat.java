package ru.mbg.nczd.feature.news.models;

import ru.mbg.nczd.feature.BaseModel;

/**
 * Created by Дмитрий on 02.02.2018.
 */

public class NewsStat extends BaseModel {

    private int totalPages;

    private int postPerPage;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPostPerPage() {
        return postPerPage;
    }

    public void setPostPerPage(int postPerPage) {
        this.postPerPage = postPerPage;
    }
}
