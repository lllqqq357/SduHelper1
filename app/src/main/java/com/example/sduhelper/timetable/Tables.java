package com.example.sduhelper.timetable;

/**
 * Created by 顾文涛 on 2017/2/20.
 */

public class Tables {


    private String name;
    private String location;
    private int day;
    private int index;


    public Tables(String name,String location,int day,int index){
        this.name=name;
        this.day=day;
        this.index=index;
        this.location=location;

    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getIndex() {
        return index;
    }



    public String getLocation() {
        return location;
    }

}
