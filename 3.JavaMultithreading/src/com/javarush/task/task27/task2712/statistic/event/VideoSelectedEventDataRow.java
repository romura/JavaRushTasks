package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.ad.Advertisement;

import java.util.Date;
import java.util.List;

/**
 * Created by GETMAN on 11.03.2017.
 */
public class VideoSelectedEventDataRow implements EventDataRow{
    private Date currentDate;
    private List<Advertisement> optimalVideoSet; /*список видео-роликов, отобранных для показа*/
    private long amount; /*сумма денег в копейках*/
    private int totalDuration; /*общая продолжительность показа отобранных рекламных роликов*/

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        currentDate = new Date();
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
    }

    public List<Advertisement> getOptimalVideoSet() {
        return optimalVideoSet;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
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
