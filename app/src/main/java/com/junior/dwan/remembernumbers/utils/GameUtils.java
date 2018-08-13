package com.junior.dwan.remembernumbers.utils;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Might on 25.11.2016.
 */

public class GameUtils {

    public static void streamNumbersPanel(TextView answerTv, View v) {
        TextView textView = (TextView) v;
        String answer = answerTv.getText().toString();
        String text = textView.getText().toString();
        if (answer.equals("0")) {
            answerTv.setText(text);
        } else
            answerTv.setText(answer + text);
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
