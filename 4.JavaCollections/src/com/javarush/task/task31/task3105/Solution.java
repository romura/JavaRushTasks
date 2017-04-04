package com.javarush.task.task31.task3105;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String stringFileToArchive = args[0];
        String archive = args[1];
        Map<String, byte[]> map = new HashMap<>();

        FileInputStream fileInputStream = new FileInputStream(archive);
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
        ZipEntry zipEntry;
        while((zipEntry = zipInputStream.getNextEntry()) != null){
            String name = zipEntry.getName();
            int entrySize = (int)zipEntry.getSize();
            int counter = 0;
            byte[] array = new byte[entrySize];

            while (counter < entrySize){
                array[counter] = (byte)zipInputStream.read();
                counter++;
            }

            map.put(name, array);
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
        fileInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(archive);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        ZipEntry newEntry = new ZipEntry("new" + File.separator + Paths.get(stringFileToArchive).getFileName().toString());
        zipOutputStream.putNextEntry(newEntry);
        Files.copy(Paths.get(stringFileToArchive), zipOutputStream);
        zipOutputStream.closeEntry();

        for(Map.Entry<String, byte[]> mapEntry : map.entrySet()){
            if (!mapEntry.getKey().equals(Paths.get(stringFileToArchive).getFileName().toString())) {
                String name = mapEntry.getKey();
                zipEntry = new ZipEntry(name);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(mapEntry.getValue());
                zipOutputStream.closeEntry();
            }
        }

        zipOutputStream.close();
        fileOutputStream.close();

    }
}
