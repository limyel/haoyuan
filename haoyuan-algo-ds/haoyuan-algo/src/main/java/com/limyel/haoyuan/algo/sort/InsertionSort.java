package com.limyel.haoyuan.algo.sort;

import com.limyel.haoyuan.algo.util.ArrayUtil;
import com.limyel.haoyuan.algo.util.SortingHelper;

/**
 * 插入排序
 * 时间复杂度：O(n^2)，对于有序数组，复杂度则是 O(n)
 */
public abstract class InsertionSort {

    public static <E extends Comparable<E>> void sortV1(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j].compareTo(arr[j-1]) < 0; j--) {
                ArrayUtil.swap(arr, j, j-1);
            }
        }
    }

    public static <E extends Comparable<E>> void sortV2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E tmp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j-1].compareTo(tmp) > 0; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = ArrayUtil.generateRandomArray(n, n);

        SortingHelper.sortTest("Insertion", arr);
    }

}
