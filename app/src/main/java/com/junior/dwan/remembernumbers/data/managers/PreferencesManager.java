package com.junior.dwan.remembernumbers.data.managers;

import android.content.SharedPreferences;

import com.junior.dwan.remembernumbers.utils.ContsantsManager;
import com.junior.dwan.remembernumbers.utils.RememberNumbersApplication;

/**
 * Created by Might on 04.11.2016.
 */

public class PreferencesManager {
    private SharedPreferences mSharedPreferences;

    public PreferencesManager() {
        this.mSharedPreferences= RememberNumbersApplication.getSharedPreferences();
    }

    public void saveHighScore(int score){
        mSharedPreferences.edit().putInt(ContsantsManager.PREF_HIGHSCORE,score).apply();
    }

    public int loadHighScore(){
        return mSharedPreferences.getInt(ContsantsManager.PREF_HIGHSCORE,0);
    }
}
