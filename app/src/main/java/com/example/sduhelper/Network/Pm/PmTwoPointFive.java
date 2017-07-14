package com.example.sduhelper.Network.Pm;

/**
 * Created by 顾文涛 on 2017/1/29.
 */



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PmTwoPointFive {

    @SerializedName("aqi")
    @Expose
    private Integer aqi;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("pm2_5")
    @Expose
    private Integer pm25;
    @SerializedName("pm2_5_24h")
    @Expose
    private Integer pm2524h;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("primary_pollutant")
    @Expose
    private String primaryPollutant;
    @SerializedName("time_point")
    @Expose
    private String timePoint;

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public Integer getPm2524h() {
        return pm2524h;
    }

    public void setPm2524h(Integer pm2524h) {
        this.pm2524h = pm2524h;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

}
