package com.junior.dwan.remembernumbers.utils;

import java.util.Random;

/**
 * Created by Might on 04.11.2016.
 */

public class test {
    public static void main(String[] args) {
        Random random=new Random();
        for (int i = 0; i < 10; i++) {
            int n=random.nextInt(1000);
            System.out.println(n);
        }

    }
}
