package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i <=10; i++){
            map.put(String.valueOf(i), i*100);//напишите тут ваш код
        }
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        HashMap<String, Integer> bufferMap = new HashMap<>(map);
        for (Map.Entry<String, Integer> m : bufferMap.entrySet()){
            if (m.getValue() < 500) map.remove(m.getKey());//напишите тут ваш код
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();
        removeItemFromMap(map);

    }
}