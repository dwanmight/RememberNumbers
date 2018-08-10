package com.junior.dwan.remembernumbers.ui.windows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.junior.dwan.remembernumbers.utils.IntentUtils;

/**
 * Created by Might on 27.11.2016.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goToGame();
    }

    private void goToGame() {
        IntentUtils.startFinish(this,32);
        finish();
    }
}
