package com.javarush.task.task29.task2909.car;

/**
 * Created by GETMAN on 27.02.2017.
 */
public class Cabriolet extends Car {

    public Cabriolet(int numberOfPassengers) {
        super(CABRIOLET, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        final int MAX_CABRIOLET_SPEED = 90;
        return MAX_CABRIOLET_SPEED;
    }
}
