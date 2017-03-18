package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by GETMAN on 09.03.2017.
 */
public class Order {
    final Tablet tablet;
    private final int number;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.number = tablet.number;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public int getTotalCookingTime(){
        int cookingTime = 0;
        try {
            for (Dish dish : dishes){
                cookingTime += dish.getDuration();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookingTime;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) return "";
        else {
            return "Your order: " + dishes.toString() + " of " + tablet.toString();
        }
    }
}
