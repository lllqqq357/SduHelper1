package com.example.sduhelper.Network;

import com.example.sduhelper.Network.Now.Start;
import com.example.sduhelper.Network.Pm.PmTwoPointFive;
import com.example.sduhelper.Network.SchoolBus.Bus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;



/**
 * Created by 顾文涛 on 2017/1/24.
 */
//TODO
public interface WeatherService {
    @GET("now.json?key=fsiff1qpylqkq8mj&location=jinan&language=zh-Hans&unit=c")
    Call<Start> getWeather();

    @GET("daily.json?key=fsiff1qpylqkq8mj&location=jinan&language=zh-Hans&unit=c&start=0&days=3")
    Call<com.example.sduhelper.Network.Next.Start> getNextWeather();

    @GET("pm2_5.json?city=jinan&token=5j1znBVAsnSf5xQyNQyq&stations=no")
    Call<PmTwoPointFive> getPM25();



    @GET("schoolbus/")
    Call<List<Bus>> getBus(@Query("act") int i,@Query("start") String start,@Query("end") String end,@Query("isWeekend") int isWeekend);




}
