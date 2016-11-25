package com.junior.dwan.remembernumbers.data.managers;

import android.content.Context;

import com.junior.dwan.remembernumbers.utils.UserQuestionUtils;

/**
 * Created by Might on 04.11.2016.
 */

public class DataManager {

    private PreferencesManager mPreferencesManager;
    private static DataManager sDataManager;
    private Context mContext;
    UserQuestionUtils mUserQuestionUtils;

    private DataManager(Context context) {
        this.mUserQuestionUtils = new UserQuestionUtils();
        this.mContext = context;
        this.mPreferencesManager = new PreferencesManager();
    }

    public static DataManager get(Context c) {
        if (sDataManager == null) {
            sDataManager = new DataManager(c.getApplicationContext());
        }
        return sDataManager;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

    public void setQuestion(int question) {
        mUserQuestionUtils.setTextQuestion(question);
    }

    public String getQuestion() {
        return mUserQuestionUtils.getTextQuestion();
    }

    public void setScore(int score) {
        mUserQuestionUtils.setScore(score);
    }

    public int getScore() {
        return mUserQuestionUtils.getScore();
    }

    public void setLife(int life) {
        mUserQuestionUtils.setLife(life);
    }

    public int getLife() {
        return mUserQuestionUtils.getLife();
    }
}
