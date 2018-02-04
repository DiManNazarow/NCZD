package ru.mbg.nczd.utils;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;

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

    public static boolean isPhone(String text){
        return text.matches("\\d+(?:\\.\\d+)?");
    }

    public static abstract class TextChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public static View.OnTouchListener sEmptyTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    };

}
