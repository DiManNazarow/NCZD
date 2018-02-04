package ru.mbg.nczd.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 04.02.2018.
 */

public class GuiUtils {

    public static void showAlertMessage(@StringRes int titleStringId, @StringRes int messageStringId, @StringRes int negativeButtonText, @StringRes int positiveButtonText, DialogInterface.OnClickListener onNegativeButtonClick, DialogInterface.OnClickListener onPositiveButtonClick, Context context){
        showAlertMessage(context.getString(titleStringId), context.getString(messageStringId), context.getString(negativeButtonText), context.getString(positiveButtonText), onNegativeButtonClick, onPositiveButtonClick, context);
    }

    public static void showAlertMessage(@StringRes int titleStringId, @StringRes int messageStringId, String negativeButtonText, String positiveButtonText, DialogInterface.OnClickListener onNegativeButtonClick, DialogInterface.OnClickListener onPositiveButtonClick, Context context){
        showAlertMessage(context.getString(titleStringId), context.getString(messageStringId), negativeButtonText, positiveButtonText, onNegativeButtonClick, onPositiveButtonClick, context);
    }

    public static void showAlertMessage(String title, String message, String negativeButtonText, String positiveButtonText, DialogInterface.OnClickListener onNegativeButtonClick, DialogInterface.OnClickListener onPositiveButtonClick, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton(negativeButtonText, onNegativeButtonClick);
        builder.setPositiveButton(positiveButtonText, onPositiveButtonClick);
        builder.create().show();
    }

    public static void showOkMessage(@StringRes int titleStringId, @StringRes int messageStringId, Context context){
        showOkMessage(context.getString(titleStringId), context.getString(messageStringId), context);
    }

    public static void showOkMessage(String title, String message, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("ОК", null);
        builder.create().show();
    }

}
