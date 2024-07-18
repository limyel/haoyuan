package com.limyel.haoyuan.algo.search;

import com.limyel.haoyuan.algo.util.ArrayUtil;

/**
 * 线性搜索
 */
public class LinearSearch {

    private LinearSearch() {}

    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 10000000;
        Integer[] data = ArrayUtil.generateOrderedArray(n);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            int index = LinearSearch.search(data, n);
        }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }

}
