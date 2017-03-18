package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        int minIndex = 0;
        int maxIndex = 0;
        int maxLength;
        int minLength;
        for (int i = 0; i < 10; i++){
            list.add(br.readLine());
        }

        maxLength = list.get(0).length();
        minLength = list.get(0).length();

        for (int i = 1; i < list.size(); i++){
            if (list.get(i).length() > maxLength){
                maxLength = list.get(i).length();
                maxIndex = i;
            }
            if (list.get(i).length() < minLength){
                minLength = list.get(i).length();
                minIndex = i;
            }
        }

        System.out.println(minIndex < maxIndex ? list.get(minIndex) : list.get(maxIndex));
    }
}
