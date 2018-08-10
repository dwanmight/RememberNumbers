package com.junior.dwan.remembernumbers.data.models;

public class GameInfo {

    private int mLifesCount;
    private int mScoresCount;
    private int mCorrectAnswersCount;

    public GameInfo(int lifesCount, int scoresCount) {
        this.mLifesCount = lifesCount;
        this.mScoresCount = scoresCount;
        mCorrectAnswersCount = 0;
    }


    public void setLifesCount(int count) {
        this.mLifesCount = count;
    }

    public int getLifeCount() {
        return mLifesCount;
    }

    public int getScoreCount() {
        return mScoresCount;
    }

    public int getCorrectAnswersCount() {
        return mCorrectAnswersCount;
    }

    public void incrementScore() {
        this.mScoresCount++;
    }

    public void incrementCorrectAnswers() {
        this.mCorrectAnswersCount++;
    }
}
