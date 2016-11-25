package com.junior.dwan.remembernumbers.utils;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Might on 25.11.2016.
 */

public class NumberUtils {

    public static void streamNumbersPanel(TextView tvAnswer,View v) {
        TextView textView = (TextView) v;
        String working = tvAnswer.getText().toString();
        String text = textView.getText().toString();
        if (working.equals("0")) {
            tvAnswer.setText(text);
        } else
            tvAnswer.setText(working + text);
    }
}
