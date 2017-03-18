package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by GETMAN on 05.03.2017.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        boolean result = false;
        if (f.isDirectory()){
            result = true;
        }
        else{
            String fileName = f.getName().toLowerCase();
            result = (fileName.endsWith(".htm") || fileName.endsWith(".html"));
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
