package com.javarush.task.task06.task0606;

import java.io.*;
import java.util.Scanner;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int buf;
        while (number > 0){
            buf = number % 10;
            if ((buf % 2) == 0) even++;
            else odd++;
            number /= 10;//напишите тут ваш код
        }
        System.out.println("Even: "+ even + " Odd: " + odd);
    }
}
