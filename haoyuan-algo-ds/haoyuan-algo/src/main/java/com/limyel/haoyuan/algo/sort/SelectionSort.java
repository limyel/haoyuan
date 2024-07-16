package com.limyel.haoyuan.algo.sort;

import com.limyel.haoyuan.algo.util.ArrayGenerator;
import com.limyel.haoyuan.algo.util.SortingHelper;

import java.util.Arrays;

/**
 * 选择排序，每次选出最小的一个元素
 * 时间复杂度：n^2
 */
public abstract class SelectionSort {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        // 最小值索引
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    private static <E> void swap(E[] arr, int index1, int index2) {
        E tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] array = ArrayGenerator.generateRandomArray(n, n);

        SortingHelper.sortTest("SelectionSort", array);
    }

}
