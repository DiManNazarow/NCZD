package ru.mbg.nczd.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 29.01.2018.
 */

public class DateUtils {

    public static final String DEFAULT_DATE_PATTERN = "dd.MM.yyyy";

    public static Date getDateFromString(String dateText, String pattern) {
        try {
            DateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
            return format.parse(dateText);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getSimpleReceptionInfoDate(Context context, String date){
        Date d = getDateFromString(date, DEFAULT_DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return String.format(Locale.getDefault(), "%d %s", calendar.get(Calendar.DAY_OF_MONTH), getMonthName(d, context));
    }

    public static String getMonthName(Date date, Context context){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] months = context.getResources().getStringArray(R.array.months);
        return months[calendar.get(Calendar.MONTH)];
    }

    public static String getStringDateEditText(){
        Calendar calendar = Calendar.getInstance();
        return String.format(Locale.getDefault(), "%s, %d.%d.%d", getDayName(getDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getStringTimeEditText(){
        Calendar calendar = Calendar.getInstance();
        return String.format(Locale.getDefault(), "%d:%d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    public static void showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener callback){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, callback, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public static void showTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener callback){
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, callback, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    public static String getStringDate(){
        Calendar calendar = Calendar.getInstance();
        return getStringDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getStringDate(int year, int month, int day){
        return String.format(Locale.getDefault(), "%d.%d.%d", year, month+1, day);
    }

    public static String getDayName(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static Date getDate(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static String getStringDateEditText(int year, int month, int day){
        return String.format(Locale.getDefault(), "%s, %d.%d.%d", getDayName(getDate(year, month, day)), day, month + 1, year);
    }

    public static String getStringTimeEditText(int hourOfDay, int minute){
        return String.format(Locale.getDefault(), "%d:%d", hourOfDay, minute);
    }

}
