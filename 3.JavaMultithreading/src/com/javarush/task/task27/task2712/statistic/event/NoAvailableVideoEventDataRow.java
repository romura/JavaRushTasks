package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by GETMAN on 11.03.2017.
 */
public class NoAvailableVideoEventDataRow implements EventDataRow{
    private Date currentDate;
    private int totalDuration; /*время приготовления заказа в секундах*/

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
