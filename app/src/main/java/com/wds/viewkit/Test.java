package com.wds.viewkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (C), 2016-2020, 未来酒店
 * File: Test.java
 * Author: wds_sun
 * Date: 2020/10/14 4:09 PM
 * Description:
 */
public class Test {
    public static void main(String[] args) {

//        System.out.println(theTargetNumberIndex.toString());

        int[] nums={1,2,3,4,5,6,7,8,9,10};
        int sum = 12;

        method1(nums,sum);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("");
        threadLocal.get();


    }


    public static void method1(int[] nums, int sum) {

        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length-1; i++) {
          int a=nums[i];
          for (int j=i+1;j<nums.length-1;j++){
              if((a+nums[j])==sum) {
                  System.out.println("a------"+a);
                  System.out.println("b------"+nums[j]);
                  map.put(a,nums[j]);
              }
          }
        }

        for(HashMap.Entry<Integer,Integer > entry:map.entrySet()) {
            System.out.println(entry.getKey() + " + " +entry.getValue() + " = " + sum);
        }
    }


    //复杂度为O(n)
    public static List<Integer> getTheTargetNumberIndex(List<Integer> source , Integer target){
        //存放结果数组,当没找到时，返回{- 1，-1}
        List<Integer> resultList = new ArrayList<>();
//        resultList .add(-1);
//        resultList .add(-1);
        //对数据进行校验
        if(source == null || target == null){
            return resultList;
        }
        //把数组放到map里面，因为map查的速度快，底层是哈希算法，复杂度 --- 1
        Map<Integer,Integer> sourceMap = new HashMap<Integer,Integer>();
        for (Integer i = 0; i < source.size() -1 ; i++) {
            //保证当数组里面有相同数字，比如target = 8, source{1,3,4,4,5},那么4，4就满足。避免map的key唯一的问题
            if (sourceMap.size() > 0 && sourceMap.containsKey(source.get(i)) && target == source.get(i) * 2) {
                resultList.add(i);
                resultList.add(sourceMap.get(source.get(i)));
                return resultList;
            }else{
                sourceMap.put(source.get(i),i);
            }
        }

        //利用target - source[i]获得新的newTarget，然后判断newTarget是否在map的key值里面存在
        for (Integer i = 0; i < source.size() -1 ; i++) {
            Integer templateResult = target - source.get(i);
            if (sourceMap.containsKey(templateResult) && target != templateResult * 2) {
                resultList.add(i);
                resultList.add(sourceMap.get(templateResult));
                //找一对数据
//                return resultList;
            }
        }

        return resultList;
    }
}

