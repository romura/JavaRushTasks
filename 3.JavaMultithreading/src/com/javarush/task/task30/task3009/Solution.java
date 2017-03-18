package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number){
        Set<Integer> set = new HashSet<>();

        for(int i = 2; i <= 36; i++){
            int num;
            try {
                num = Integer.parseInt(number);
            }
            catch (Exception e){
                continue;
            }
            String n;
            n = Integer.toUnsignedString(num, i);
            if (isPalindrome(n)){
                set.add(i);
            }
        }

        return set;
    }

    public static boolean isPalindrome(String n)
    {
        return n.equals(new StringBuilder(n).reverse().toString());
    }
}