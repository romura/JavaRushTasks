package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        Integer i;
        if (!s.startsWith("0")){
            i = Integer.parseInt(s, 10);
        }
        else{
            if (s.startsWith("0x")){
                i = Integer.parseInt(s.replaceFirst("0x", ""), 16);
            }
            else if (s.startsWith("0b")){
                i = Integer.parseInt(s.replaceFirst("0b", ""), 2);
            }
            else i = Integer.parseInt(s, 8);
        }
        s = String.valueOf(i);
        return s;
    }
}
