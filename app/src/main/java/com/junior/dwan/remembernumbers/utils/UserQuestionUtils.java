package com.junior.dwan.remembernumbers.utils;

/**
 * Created by Might on 04.11.2016.
 */

public class UserQuestionUtils {
    private String mTextQuestion;

    private int mScore;

    public UserQuestionUtils() {
    }


    public void setTextQuestion(int textQuestion) {
        mTextQuestion = textQuestion+"";
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

}
