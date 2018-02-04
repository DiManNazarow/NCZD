package ru.mbg.nczd.network;

import java.util.Locale;

/**
 * Created by Дмитрий on 02.02.2018.
 */

public class NetworkUtils {

    public static final String BASE_URL = "http://www.nczd.ru";

    public static final String EXTERNAL_MASK = "%s%s";

    public static final String INTERNAL_MASK = "%s/?%s=%s&%s=%s";

    public static String buildNewsDetailsLink(String pageLink){
        return String.format(Locale.getDefault(), EXTERNAL_MASK, BASE_URL, pageLink);
    }

    public static String buildNewsImageLink(String imageLink){
        return String.format(Locale.getDefault(), EXTERNAL_MASK, BASE_URL, imageLink);
    }
}
