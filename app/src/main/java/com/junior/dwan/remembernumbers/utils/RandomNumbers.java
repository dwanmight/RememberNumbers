package com.junior.dwan.remembernumbers.utils;

import java.util.Random;

/**
 * Created by Might on 04.11.2016.
 */

public class RandomNumbers {
    private Random mRandom;

    public RandomNumbers() {
        mRandom = new Random();
    }

    public int getRandom3X(int minimum, int maximum) {
        return mRandom.nextInt((maximum - minimum) + 1) + minimum;
    }
}
