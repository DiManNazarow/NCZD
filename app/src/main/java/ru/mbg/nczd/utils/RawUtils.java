package ru.mbg.nczd.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class RawUtils {

    public static String getStringRaw(Context context, int resId){
        InputStream inputStream = context.getResources().openRawResource(resId);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder text = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null){
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e){
            return null;
        }
        return text.toString();
    }

}
