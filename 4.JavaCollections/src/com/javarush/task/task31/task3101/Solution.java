package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    static List<File> listOfFiles = new ArrayList<>();

    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        File resultFile = new File(resultFileAbsolutePath.getParent() + "/" + "allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, resultFile);

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        addFilesToList(path);

        List<File> bufferListOfFiles = new ArrayList<>();
        for (File file : listOfFiles){
            bufferListOfFiles.add(file);
        }

        for (File file : listOfFiles){
            if (file.length() > 50){
                deleteFile(file);
                bufferListOfFiles.remove(file);
            }
        }
        listOfFiles = bufferListOfFiles;

        Collections.sort(listOfFiles);

        for (File file : listOfFiles){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while(reader.ready()){
                    bufferedWriter.write(reader.read());
                }
                bufferedWriter.write(System.lineSeparator());
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    public static void addFilesToList(File path){
        if (!path.exists() && path == null){
            return;
        }

        if (path.isFile()){
            listOfFiles.add(path);
        }
        else{
            for(File files : path.listFiles()){
                addFilesToList(files);
            }
        }
    }
}
