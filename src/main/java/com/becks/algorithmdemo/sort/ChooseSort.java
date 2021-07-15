package com.becks.algorithmdemo.sort;

import java.util.*;
/**
 * @ClassName ChooseSort
 * @Description 选择排序
 * @Author Becks
 * @Date 2021/7/15
 **/
public class ChooseSort {

    public void chooseSort(int[] array){
        if(array == null || array.length < 1){
            return;
        }
        System.out.println("Before:" + Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if(array[i] > array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println("Before:" + Arrays.toString(array));
    }

    public static void main(String[] args){
        int[] array = {2,4,5,1,3,8,7,6,0,9};
        new ChooseSort().chooseSort(array);
    }

}
