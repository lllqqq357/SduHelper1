package com.example.sduhelper.Network.SchoolBus;

/**
 * Created by 顾文涛 on 2017/2/25.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bus {

    @SerializedName("s")
    @Expose
    private String s;
    @SerializedName("e")
    @Expose
    private String e;
    @SerializedName("t")
    @Expose
    private String t;

    public Bus(){

    }

    public Bus(String s,String e,String t){
        this.s=s;
        this.e=e;
        this.t=t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

}