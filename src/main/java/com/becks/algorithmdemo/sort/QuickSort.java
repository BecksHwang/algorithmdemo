package com.becks.algorithmdemo.sort;

import java.util.*;
/**
 * @ClassName QuickSort
 * @Description 快排算法
 * @Author Becks
 * @Date 2021/7/16
 **/
public class QuickSort {

    public void quickSort(int[] array, int left, int right){
        if(left >= right){
            return;
        }

        int i = left;
        int j = right;
        int pivot = array[i];

        while(i < j){
            while(i < j && array[j] >= pivot){
                j--;
            }
            if(j > i){
                array[i] = array[j];
                i ++;
            }

            while(i < j && array[i] <= pivot){
                i++;
            }
            if(i < j){
                array[j] = array[i];
                j--;
            }
            array[i] = pivot;
        }

        quickSort(array, left, i - 1);
        quickSort(array, i + 1 , right);
    }

    public static void main(String[] args){
        int[] array = {3,4,6,2,1,5,7,9,0,8};
        System.out.println("Before:" + Arrays.toString(array));
        new QuickSort().quickSort(array,0, array.length - 1);
        System.out.println("After : " + Arrays.toString(array));
    }

}
