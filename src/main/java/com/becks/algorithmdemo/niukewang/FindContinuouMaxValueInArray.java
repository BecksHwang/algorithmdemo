package com.becks.algorithmdemo.niukewang;

/**
 * @ClassName FindContinuouMaxValueInArray
 * @Description 查找一个数组中连续子数组元素相加的最大值，数组有正数有负数。解题核心：逐一求和，判断相加值是否大于当前元素值，取大值。
 * @Author Becks
 * @Date 2021/7/17
 **/
public class FindContinuouMaxValueInArray {

    public int findContinuouMaxValueInArray(int[] array){
        if (array == null || array.length <= 0){
            return -1;
        }
        int maxValue = 0;
        for(int i = 0; i < array.length; i++){
            if(maxValue + array[i] > array[i]){
                maxValue += array[i];
            }else{
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    public static void main(String[] args){
        int[] array = {2,-1,-7,5,-2,-3,4,5,-3,3};
        System.out.println(new FindContinuouMaxValueInArray().findContinuouMaxValueInArray(array));
    }


}
