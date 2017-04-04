package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> resultList = new ArrayList<>();
        File rootDirectory = new File(root);

        Stack<File> stack = new Stack<>();
        stack.push(rootDirectory);

        while (!stack.isEmpty()){
            File file = stack.pop();

            if (file.isFile()){
                resultList.add(file.getAbsolutePath());
            }
            else{
                for (File f : file.listFiles()){
                    stack.push(f);
                }
            }
        }

        return resultList;

    }

    public static void main(String[] args) {
        
    }
}
