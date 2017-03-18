package com.javarush.task.task29.task2909.car;

/**
 * Created by GETMAN on 27.02.2017.
 */
public class Sedan extends Car {

    public Sedan(int numberOfPassengers) {
        super(SEDAN, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        final int MAX_SEDAN_SPEED = 120;
        return MAX_SEDAN_SPEED;
    }
}
