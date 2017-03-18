package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GETMAN on 11.03.2017.
 */
public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage storage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(Cook cook){
        cooks.add(cook);
    }

    public void register(EventDataRow data){
        storage.put(data);
    }

    public TreeMap<Date,Long> dailyAdProfit() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        TreeMap<Date,Long> dp = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> el = storage.get().get(EventType.SELECTED_VIDEOS);
        for(EventDataRow x: el) {
            Date xd = null;
            try {
                xd = dateFormat.parse(dateFormat.format(x.getDate()));
            } catch (ParseException e){}
            if (dp.containsKey(xd)) {
                dp.put(xd,dp.get(xd)+((VideoSelectedEventDataRow)x).getAmount());
            } else
                dp.put(xd,((VideoSelectedEventDataRow)x).getAmount());
        }
        return dp;
    }

    private Date dateWithoutTime(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Map< Date, Map < String, Integer > > getTotalCooksTime(){
        Map < EventType, List < EventDataRow > > map = storage.get();
        Map<Date, Map<String, Integer>> resultMap = new TreeMap<>(new Comparator<Date>()
        {
            @Override
            public int compare(Date o1, Date o2)
            {
                return o2.compareTo(o1);
            }
        });
        if (map!= null){
            List <EventDataRow> listEvent = map.get(EventType.COOKED_ORDER);
            for (EventDataRow v : listEvent){
                CookedOrderEventDataRow vi = (CookedOrderEventDataRow) v;
                int time = vi.getTime();
                if (time == 0)continue;
                if (time % 60 == 0) time = time / 60; else time = time / 60 + 1;
                Calendar cal = Calendar.getInstance();
                cal.setTime(vi.getDate());
                GregorianCalendar g = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                if (resultMap.containsKey(g.getTime())){
                    if(resultMap.get(g.getTime()).containsKey(vi.getCookName())){
                        int tmp = resultMap.get(g.getTime()).get(vi.getCookName())+time;
                        resultMap.get(g.getTime()).remove(vi.getCookName());
                        resultMap.get(g.getTime()).put(vi.getCookName(),tmp);
                    }
                    else resultMap.get(g.getTime()).put(vi.getCookName(),time);
                }
                else{
                    resultMap.put(g.getTime(), new TreeMap<String, Integer>(new Comparator<String>()
                    {
                        @Override
                        public int compare(String o1, String o2)
                        {
                            return o1.compareToIgnoreCase(o2);
                        }
                    }));
                    resultMap.get(g.getTime()).put(vi.getCookName(),time);
                }
            }
        }
        return resultMap;
    }

    private class StatisticStorage{

        private Map <EventType, List<EventDataRow>> storage = new HashMap<>();

        public Map<EventType, List<EventDataRow>> get() {
            return storage;
        }

        private StatisticStorage() {
            for (EventType eventType : EventType.values()){
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            EventType eventType = data.getType();
            List<EventDataRow> list = storage.get(eventType);
            list.add(data);
        }
    }
}
