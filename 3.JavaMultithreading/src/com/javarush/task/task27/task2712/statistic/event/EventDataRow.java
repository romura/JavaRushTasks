package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by GETMAN on 11.03.2017.
 */
public interface EventDataRow {
    public EventType getType();

    Date getDate();

    int getTime();
}
