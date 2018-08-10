package com.junior.dwan.remembernumbers.utils;

import android.content.Context;
import android.content.Intent;

import com.junior.dwan.remembernumbers.ui.windows.GameActivity;
import com.junior.dwan.remembernumbers.ui.windows.finish.ui.GameFinishActivity;

public class IntentUtils {

    public static void startGame(Context context) {
        Intent intent = new Intent(context, GameActivity.class);
        context.startActivity(intent);
    }

    public static void startFinish(Context context, int scoreCount) {
        Intent intent = new Intent(context, GameFinishActivity.class);
        intent.putExtra(ConstantsManager.EXTRA_SCORE, scoreCount);
        context.startActivity(intent);
    }
}
