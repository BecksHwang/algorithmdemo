package com.becks.algorithmdemo.sort;

import java.util.Arrays;

/**
 * @ClassName InsertSort
 * @Description 插入排序
 * @Author Becks
 * @Date 2021/7/14
 **/
public class InsertSort {

    public void insertSort(int[] array){
        System.out.println("Before:" + Arrays.toString(array));
        if(array == null || array.length <= 0){
            return;
        }

        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int temp = array[i];
            while(j > -1 && array[j] > temp){
                array[j + 1] = array[j];
                j--;
            }
             array[j + 1] = temp;
        }
        System.out.println("After :" + Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {2,4,5,1,3,8,7,6,9};
        new InsertSort().insertSort(array);
    }

}
