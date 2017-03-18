package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try{
            String s = args[0];
            int i = getOsn(s);
            System.out.println(i == 0 ? "incorrect" : i);
        }
        catch (Exception e){
            System.out.println("incorrect");
        }


    }

    public static int getOsn(String s){
        int result = 0;
        for (int i = 2; i <=36; i++){
            try {
                BigDecimal bi = new BigDecimal(new BigInteger(s, i));
                result = i;
                break;
            }
            catch (Exception e){
                continue;
            }
        }
        return result;
    }
}