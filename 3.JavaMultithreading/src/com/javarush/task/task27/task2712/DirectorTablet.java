package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by GETMAN on 12.03.2017.
 */
public class DirectorTablet {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    public void printAdvertisementProfit(){
        double total=0;
        for(Map.Entry<Date,Long> dayPr: StatisticManager.getInstance().dailyAdProfit().entrySet()) {
            double am = dayPr.getValue()/100.0;
            ConsoleHelper.writeMessage(String.format("%s - %.2f", dateFormat.format(dayPr.getKey()), am));
            total+=am;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total));
    }

    public void printCookWorkloading(){
        Map< Date, Map < String, Integer > > map = StatisticManager.getInstance().getTotalCooksTime();
        for (Map.Entry<Date, Map<String, Integer>> e : map.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%1$td-%1$tb-%1$tY", e.getKey()));
            for (Map.Entry<String,Integer> ee : e.getValue().entrySet())
            {
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d min", ee.getKey(), ee.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet(){

    }
    public void printArchivedVideoSet(){

    }
}
