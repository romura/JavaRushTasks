package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int i;
        int sum = 0;
        int count = 0;
        do{
            i = sc.nextInt();
            if (i != -1){
                sum += i;
                count++;//напишите тут ваш код
            }
        }
        while(i != -1);
        double result = (double)sum / count;
        System.out.println(result);
    }
}

