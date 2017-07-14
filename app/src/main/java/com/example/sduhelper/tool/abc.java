package com.example.sduhelper.tool;

import com.example.sduhelper.Network.SchoolBus.Bus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 本人 on 2017/7/13.
 */

public class abc {
    public static void main(String args[]){
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://202.194.14.215/Api/schoolbus/?act=1&start=中心校区&end=兴隆山校区&isWeekend=1");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();
            //读取获取的输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String response = new String();
            String line;

            while ((line = reader.readLine()) != null)
                response += line;
            response.replace('\"', '\'');
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(response).getAsJsonArray();

            Gson gson = new Gson();
            ArrayList<Bus> busArrayList = new ArrayList<>();


            //     ListView listView=(ListView)findViewById(R.id.bus1);






            for (JsonElement bus : jsonArray) {
                Bus bus1 = gson.fromJson(bus, Bus.class);
                busArrayList.add(bus1);
                // mDate.add(new Bus("起点::->" + bus1.getS(), ",终点::->" + bus1.getE(),",发车时间::" + bus1.getT()));
                System.out.println("起点：" + bus1.getS() + ",终点：" + bus1.getE() + ",发车时间：" + bus1.getT());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
