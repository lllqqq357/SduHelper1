package com.example.sduhelper.Network.Next;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Daily {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("text_day")
    @Expose
    private String textDay;
    @SerializedName("code_day")
    @Expose
    private String codeDay;
    @SerializedName("text_night")
    @Expose
    private String textNight;
    @SerializedName("code_night")
    @Expose
    private String codeNight;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("precip")
    @Expose
    private String precip;
    @SerializedName("wind_direction")
    @Expose
    private String windDirection;
    @SerializedName("wind_direction_degree")
    @Expose
    private String windDirectionDegree;
    @SerializedName("wind_speed")
    @Expose
    private String windSpeed;
    @SerializedName("wind_scale")
    @Expose
    private String windScale;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getCodeDay() {
        return codeDay;
    }

    public void setCodeDay(String codeDay) {
        this.codeDay = codeDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public String getCodeNight() {
        return codeNight;
    }

    public void setCodeNight(String codeNight) {
        this.codeNight = codeNight;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindDirectionDegree() {
        return windDirectionDegree;
    }

    public void setWindDirectionDegree(String windDirectionDegree) {
        this.windDirectionDegree = windDirectionDegree;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindScale() {
        return windScale;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

}