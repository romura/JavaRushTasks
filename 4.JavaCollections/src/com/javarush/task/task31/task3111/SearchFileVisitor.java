package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private List<Path> foundFiles = new ArrayList<>();
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize = -1;

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean conditions = true;

        if (partOfName != null && !file.getFileName().toString().contains(partOfName)){
            conditions = false;
        }

        String content = new String(Files.readAllBytes(file));
        if(partOfContent != null && !content.contains(partOfContent)){
            conditions = false;
        }

        if (minSize != 0 && Files.size(file) <= minSize){
            conditions = false;
        }

        if (maxSize != -1 && Files.size(file) >= maxSize){
            conditions = false;
        }

        if (conditions){
            foundFiles.add(file);
        }

        return FileVisitResult.CONTINUE;
    }
}
