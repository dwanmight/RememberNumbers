package com.junior.dwan.remembernumbers.utils;

import android.content.Context;
import android.content.Intent;

import com.junior.dwan.remembernumbers.ui.activity.GameFinishActivity;

public class IntentUtils {

    public static void startFinish(Context context, int scoreCount) {
        Intent intent = new Intent(context, GameFinishActivity.class);
        intent.putExtra(ConstantsManager.EXTRA_SCORE, scoreCount);
        context.startActivity(intent);
    }
}
