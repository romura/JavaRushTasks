package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fullPath = bufferedReader.readLine();
        Path directory = Paths.get(fullPath);

        if (!Files.isDirectory(directory)){
            System.out.println(fullPath + " - не папка");
        }
        else{
            MyFileVisitor myFileVisitor1 = new MyFileVisitor();
            Files.walkFileTree(directory, myFileVisitor1);
            System.out.println("Всего папок - " + (myFileVisitor1.numberOfSubdirectiries - 1));
            System.out.println("Всего файлов - " + myFileVisitor1.numberOfFiles);
            System.out.println("Общий размер - " + myFileVisitor1.size);
        }

    }

    static class MyFileVisitor extends SimpleFileVisitor<Path>{
        public int numberOfSubdirectiries;
        public int numberOfFiles;
        public int size;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            size += Files.size(file);
            numberOfFiles ++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            numberOfSubdirectiries++;
            return FileVisitResult.CONTINUE;
        }
    }
}
