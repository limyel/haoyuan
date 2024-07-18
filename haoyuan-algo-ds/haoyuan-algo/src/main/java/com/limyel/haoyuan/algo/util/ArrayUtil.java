package com.limyel.haoyuan.algo.util;

import java.util.Random;

public class ArrayUtil {

    public static Integer[] generateOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    public static <E> void swap(E[] arr, int index1, int index2) {
        E tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

}
