package com.limyel.haoyuan.algo.sort;

import com.limyel.haoyuan.algo.util.ArrayUtil;
import com.limyel.haoyuan.algo.util.SortingHelper;

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
            ArrayUtil.swap(arr, minIndex, i);
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] array = ArrayUtil.generateRandomArray(n, n);

        SortingHelper.sortTest("SelectionSort", array);
    }

}
