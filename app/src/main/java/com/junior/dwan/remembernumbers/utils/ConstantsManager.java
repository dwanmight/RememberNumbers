package com.junior.dwan.remembernumbers.utils;

/**
 * Created by Might on 25.11.2016.
 */

public interface ConstantsManager {
    //Handler messages
    int STATUS_HIDE = 0;
    int STATUS_VISIBLE = 1;
    int STATUS_FON_DEFAULT = 2;

    //show score or life
    int SHOW_SCORE = 10;
    int SHOW_LIFE = 20;

    //intent extxras
    String EXTRA_SCORE = "EXTRA_SCORE";

    //SHARED PREFERENCES
    String PREF_HIGHSCORE="PREF_HIGHSCORE";
}
