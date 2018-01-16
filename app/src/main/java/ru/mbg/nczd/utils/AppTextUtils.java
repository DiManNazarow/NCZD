package ru.mbg.nczd.utils;

/**
 * Created by Дмитрий on 17.01.2018.
 */

public class AppTextUtils {

    public static boolean isEmpty(CharSequence text){
        return android.text.TextUtils.isEmpty(text) || text.toString().toLowerCase().contentEquals("null");
    }

    public static boolean isEmpty(String text){
        return android.text.TextUtils.isEmpty(text) || text.toLowerCase().contentEquals("null");
    }

}
