package com.junior.dwan.remembernumbers.utils;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.junior.dwan.remembernumbers.R;
import com.junior.dwan.remembernumbers.ui.activity.GameActivity;

/**
 * Created by Might on 25.11.2016.
 */

public class GameUtils {

    public static void streamNumbersPanel(TextView tvAnswer, View v) {
        TextView textView = (TextView) v;
        String working = tvAnswer.getText().toString();
        String text = textView.getText().toString();
        if (working.equals("0")) {
            tvAnswer.setText(text);
        } else
            tvAnswer.setText(working + text);
    }



    public static int checkLife(boolean result, int life) {
        if (result) {
            life--;
        }
        return life;
    }

    public static boolean checkForGameOver(int life) {
        if (life > 1) {
            return true;
        } else
            return false;
    }
}
