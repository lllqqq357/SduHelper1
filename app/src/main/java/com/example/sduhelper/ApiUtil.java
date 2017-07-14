package com.example.sduhelper;

import com.example.sduhelper.Network.Now.Start;
import com.example.sduhelper.Network.RetrofitClient;
import com.example.sduhelper.Network.WeatherService;

/**
 * Created by 顾文涛 on 2017/1/24.
 */

public class ApiUtil {

    public static final String BASE_URL_1 = "https://api.thinkpage.cn/v3/weather/";
    public static final String BASE_URL_2 = "http://www.pm25.in/api/querys/";
    public static final String BASE_URL_3 = "https://202.194.14.215/Api/";




    public static WeatherService getWeatherService() {
        return RetrofitClient.getClient(BASE_URL_1).create(WeatherService.class);
    }
    public static WeatherService getPmService(){
        return RetrofitClient.getClient(BASE_URL_2).create(WeatherService.class);
    }

    public static WeatherService getBusService(){
        return RetrofitClient.getClient(BASE_URL_3).create(WeatherService.class);
    }



}
