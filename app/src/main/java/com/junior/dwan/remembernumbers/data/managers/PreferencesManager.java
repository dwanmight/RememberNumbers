package com.junior.dwan.remembernumbers.data.managers;

import android.content.SharedPreferences;

import com.junior.dwan.remembernumbers.utils.RememberNumbersApplication;

/**
 * Created by Might on 04.11.2016.
 */

public class PreferencesManager {
    private SharedPreferences mSharedPreferences;

    public PreferencesManager() {
        this.mSharedPreferences= RememberNumbersApplication.getSharedPreferences();
    }
}
