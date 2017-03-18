package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.util.Date;
import java.util.List;

/**
 * Created by GETMAN on 11.03.2017.
 */
public class CookedOrderEventDataRow implements EventDataRow{
    private Date currentDate;
    private String tabletName; /*имя планшета*/
    private String cookName; /*имя повара*/
    private int cookingTimeSeconds; /*время приготовления заказа в секундах*/
    private List<Dish> cookingDishs; /*список блюд для приготовления*/

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = cookingDishs;
        currentDate = new Date();
    }

    public String getTabletName() {
        return tabletName;
    }

    public String getCookName() {
        return cookName;
    }

    public List<Dish> getCookingDishs() {
        return cookingDishs;
    }

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }
}
