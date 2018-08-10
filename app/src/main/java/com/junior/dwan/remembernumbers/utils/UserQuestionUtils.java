package com.junior.dwan.remembernumbers.utils;

/**
 * Created by Might on 04.11.2016.
 */

public class UserQuestionUtils {
    private String mTextQuestion;

    private int mScore;
    private int mLife;

    public UserQuestionUtils() {
        mLife = 3;
        mScore = 0;
    }


    public void setTextQuestion(int textQuestion) {
        mTextQuestion = String.valueOf(textQuestion);
    }

    public String getTextQuestion() {
        return mTextQuestion;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public int getLife() {
        return mLife;
    }

    public void setLife(int life) {
        mLife = life;
    }

}
