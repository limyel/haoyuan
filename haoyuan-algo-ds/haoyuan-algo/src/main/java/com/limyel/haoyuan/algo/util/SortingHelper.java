package com.limyel.haoyuan.algo.util;

import com.limyel.haoyuan.algo.sort.SelectionSort;
import com.limyel.haoyuan.algo.sort.InsertionSort;

public abstract class SortingHelper {

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortname, E[] arr) {
        long startTime = System.nanoTime();
        if (sortname.equals("SelectionSort")) {
            SelectionSort.sort(arr);
        } else if (sortname.equals("Insertion")) {
            InsertionSort.sortV2(arr);
        }
        long endTime = System.nanoTime();

        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException(sortname + " failed.");
        }

        System.out.printf("%s, n = %d : %f s\n", sortname, arr.length, (endTime - startTime) / 1000000000.0);
    }


}
