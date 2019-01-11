package com.imooc.day01_Array;

import java.util.Arrays;

/**
 * 数组最大的优点：有索引，查询速度快
 */
public class Array {

    public static void main(String[] args) {
        int[] arrays = new int[8];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = i;
        }
        System.out.println(Arrays.toString(arrays));

        int[] scores = new int[]{68, 45, 90};
        scores[0]=100;
        System.out.println(Arrays.toString(scores));
        for (int score : scores) {
            System.out.println(score);
        }
    }
}
