package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        int count = 1;
        int bufferCount = 1;

        for (int i = 0; i < 10; i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        for (int i = 1; i < list.size(); i++){
            if (list.get(i) == list.get(i - 1)){
                bufferCount++;
                if (count < bufferCount) count = bufferCount;
            }
            else bufferCount = 1;
        }

        System.out.println(count);

    }
}