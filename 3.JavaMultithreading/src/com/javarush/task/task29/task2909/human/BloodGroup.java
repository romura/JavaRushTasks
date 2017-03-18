package com.javarush.task.task29.task2909.human;

/**
 * Created by GETMAN on 27.02.2017.
 */
public class BloodGroup {
    private final int code;

    private BloodGroup(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BloodGroup first(){
        BloodGroup firstBloodGroop = new BloodGroup(1);
        return firstBloodGroop;
    }

    public static BloodGroup second(){
        BloodGroup secondBloodGroop = new BloodGroup(2);
        return secondBloodGroop;
    }

    public static BloodGroup third(){
        BloodGroup thirdBloodGroop = new BloodGroup(3);
        return thirdBloodGroop;
    }

    public static BloodGroup fourth(){
        BloodGroup fourthBloodGroop = new BloodGroup(4);
        return fourthBloodGroop;
    }
}
