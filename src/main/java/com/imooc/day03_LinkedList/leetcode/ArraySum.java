package com.imooc.day03_LinkedList.leetcode;

public class ArraySum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int sum = sum(0, arr);
        System.out.println(sum);
    }

    public static int sum(int n, int[] arr) {
        if (n == arr.length)
            return 0;

        return arr[n] + sum(n + 1, arr);
    }
}
