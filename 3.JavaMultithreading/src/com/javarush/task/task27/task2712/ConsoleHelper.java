package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GETMAN on 09.03.2017.
 */
public class ConsoleHelper {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String result = bufferedReader.readLine();
        return result;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите строку — название блюда. Или введите ‘exit‘ для завершения заказа.");

        while(true){
            String string = readString();
            if (string.equals("exit")){
                break;
            }
            else{
                try{
                    dishes.add(Dish.valueOf(string));
                }
                catch (IllegalArgumentException e){
                    writeMessage("такого блюда нет");
                    continue;
                }
            }
        }
        return dishes;
    }
}
