package com.becks.algorithmdemo.niukewang;

/**
 * @ClassName StairsProblem
 * @Description 爬楼梯问题：只能一次跨1步或者一次跨3步进行爬楼梯，问N阶楼梯有几种方式可以爬完；例如n=1，只能1步爬完；
 * 例如n=2，可以11爬完；例如n=3，可以111或者3爬完；例如n=4，可以31、13、1111三种方式爬完。
 * @Author Becks
 * @Date 2021/5/25
 **/
public class StairsProblem {
   /**
    * @Author Becks
    * @Description 采用倒推思路处理爬楼梯问题，例如：想知道爬5阶楼有多少种方法，只需要知道爬2阶楼梯和爬4阶楼梯有多少种方法，
    * 为什么是爬2阶楼梯和爬4阶楼梯的方法呢？因为爬5阶楼梯，最后一步要么是爬1阶要么是爬3阶，爬1阶的话前面还有4阶要爬，爬3阶的话前面有2阶要爬。
    * @Date 2021/5/25
    * @Param n:楼梯阶数
    * @Return int:爬楼梯方法数
    **/
    public int waysOfClimbStairs(int n){
        if(n <= 0){
            return 0;
        }
        switch (n){
            case 1:
                return 1;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return waysOfClimbStairs(n-3) + waysOfClimbStairs(n-1);
        }
    }

    public static void main(String[] args) {
        StairsProblem stairsProblem = new StairsProblem();
        System.out.println(stairsProblem.waysOfClimbStairs(1));
        System.out.println(stairsProblem.waysOfClimbStairs(2));
        System.out.println(stairsProblem.waysOfClimbStairs(3));
        System.out.println(stairsProblem.waysOfClimbStairs(4));
        System.out.println(stairsProblem.waysOfClimbStairs(5));
        System.out.println(stairsProblem.waysOfClimbStairs(6));
    }


}
