package com.javarush.task.task09.task0918;

/* 
Все свои, даже исключения
*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Solution {
    public static void main(String[] args) {
    }
    
    static class MyException extends NoSuchElementException {
    }

    static class MyException2 extends IndexOutOfBoundsException {

    }

    static class MyException3 extends ClassNotFoundException {
    }

    static class MyException4 extends FileNotFoundException{
    }
}

