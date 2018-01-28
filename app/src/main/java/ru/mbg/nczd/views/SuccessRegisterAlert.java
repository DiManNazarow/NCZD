package ru.mbg.nczd.views;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 28.01.2018.
 */

public class SuccessRegisterAlert {

    public static void show(Activity activity, View.OnClickListener fillButtonListener, View.OnClickListener passListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_success_register, null);
        builder.setView(view);
        Button fillInfoButton = view.findViewById(R.id.fill_info_button);
        fillInfoButton.setOnClickListener(fillButtonListener);
        TextView passButton = view.findViewById(R.id.pass_button);
        passButton.setOnClickListener(passListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();
    }

}
