package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by GETMAN on 10.03.2017.
 */
public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order)arg;
        int coockingTime = order.getTotalCookingTime();
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.tablet.toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        ConsoleHelper.writeMessage("Start cooking - " + arg +" , cooking time " + coockingTime + "min");
        setChanged();
        notifyObservers(arg);
    }
}
