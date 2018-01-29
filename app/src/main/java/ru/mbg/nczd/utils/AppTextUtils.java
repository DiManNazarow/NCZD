package ru.mbg.nczd.utils;

import android.text.Editable;
import android.text.TextWatcher;

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

    public static abstract class TextChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
