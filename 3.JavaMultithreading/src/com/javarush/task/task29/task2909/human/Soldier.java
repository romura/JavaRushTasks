package com.javarush.task.task29.task2909.human;

/**
 * Created by GETMAN on 26.02.2017.
 */
public class Soldier extends Human{


    public Soldier(String name, int age) {
        super(name, age);
    }

    public void fight() {
    }

    @Override
    public void live() {
        this.fight();
    }
}

