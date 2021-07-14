package com.becks.algorithmdemo.sort;

import java.util.Arrays;

/**
 * @ClassName BubbleSort
 * @Description 冒泡排序
 * @Author Becks
 * @Date 2021/7/14
 **/
public class BubbleSort {

    public void bubbleSort(int[] array){
        System.out.println("Before:" + Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("After :" + Arrays.toString(array));
    }

    public static void main(String[] args){
        int[] array = {3,4,6,1,2,7,8,5,9,0};
        new BubbleSort().bubbleSort(array);
    }

}
