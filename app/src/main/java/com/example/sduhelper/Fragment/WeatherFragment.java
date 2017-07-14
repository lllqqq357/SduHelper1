package com.example.sduhelper.Fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sduhelper.ApiUtil;
import com.example.sduhelper.Network.Now.Start;
import com.example.sduhelper.Network.WeatherService;
import com.example.sduhelper.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 顾文涛 on 2017/1/24.
 */

public class WeatherFragment extends Fragment {
    private ImageView image_weather;
    private TextView temper;
    private TextView weather;

    private TextView date_one;
    private TextView one_day_temp;
    private TextView one_night_temp;
    private ImageView one_day_pic;
    private ImageView one_night_pic;

    private TextView date_two;
    private TextView two_day_temp;
    private TextView two_night_temp;
    private ImageView two_day_pic;
    private ImageView two_night_pic;

    private TextView date_thr;
    private TextView thr_day_temp;
    private TextView thr_night_temp;
    private ImageView thr_day_pic;
    private ImageView thr_night_pic;


    String weath="---";

    ArrayList<String> day_one=new ArrayList<String>();
    ArrayList<String> day_two=new ArrayList<String>();
    ArrayList<String> day_three=new ArrayList<String>();

    private WeatherService weatherService;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_fragmeny, container, false);
        temper= (TextView) rootView.findViewById(R.id.temperature);
        weather=(TextView) rootView.findViewById(R.id.weather);
        image_weather= (ImageView) rootView.findViewById(R.id.imageView_weather);

        date_one=(TextView) rootView.findViewById(R.id.date_one);
        one_day_temp=(TextView) rootView.findViewById(R.id.one_day_temp);
        one_night_temp=(TextView) rootView.findViewById(R.id.one_night_tem);
        one_day_pic=(ImageView)rootView.findViewById(R.id.one_day);
        one_night_pic=(ImageView)rootView.findViewById(R.id.one_night);

        date_two=(TextView) rootView.findViewById(R.id.date_two);
        two_day_temp=(TextView) rootView.findViewById(R.id.two_day_tem);
        two_night_temp=(TextView) rootView.findViewById(R.id.two_night_tem);
        two_day_pic=(ImageView)rootView.findViewById(R.id.two_day);
        two_night_pic=(ImageView)rootView.findViewById(R.id.two_night);

        date_thr=(TextView) rootView.findViewById(R.id.date_three);
        thr_day_temp=(TextView) rootView.findViewById(R.id.three_day_tem);
        thr_night_temp=(TextView) rootView.findViewById(R.id.three_night_tem);
        thr_day_pic=(ImageView)rootView.findViewById(R.id.three_day);
        thr_night_pic=(ImageView)rootView.findViewById(R.id.three_night);


        Bundle data=getArguments();

        if(data.getString("weather").startsWith("李狗")){

        } else{
            day_one = data.getStringArrayList("day_one");
            day_two = data.getStringArrayList("day_two");
            day_three = data.getStringArrayList("day_three");


            weath = data.getString("weather");
            temper.setText(data.getString("temperature") + "°");
            weather.setText(weath);

            date_one.setText(day_one.get(0).substring(5));
            one_day_temp.setText(day_one.get(4) + "°C");
            one_night_temp.setText(day_one.get(3) + "°C");
            one_day_pic.setImageResource(loadPic(day_one.get(1)));
            one_night_pic.setImageResource(loadPic(day_one.get(2)));

            date_two.setText(day_two.get(0).substring(5));
            two_day_temp.setText(day_two.get(4) + "°C");
            two_night_temp.setText(day_two.get(3) + "°C");
            two_day_pic.setImageResource(loadPic(day_two.get(1)));
            two_night_pic.setImageResource(loadPic(day_two.get(2)));

            date_thr.setText(day_three.get(0).substring(5));
            thr_day_temp.setText(day_three.get(4) + "°C");
            thr_night_temp.setText(day_three.get(3) + "°C");
            thr_day_pic.setImageResource(loadPic(day_three.get(1)));
            thr_night_pic.setImageResource(loadPic(day_three.get(2)));


            loadAnswers();
        }


        return rootView;
    }





    public int loadPic(String wea) {

        switch (wea) {
            case "晴": {
                return R.drawable.weather2;

            }
            case "多云": {
                return R.drawable.weather7;
                //textView_log_in.setText("多云");
                //Toast.makeText(getApplicationContext(),wea,Toast.LENGTH_SHORT).show();

            }
            case "晴间多云": {
                return R.drawable.weather7;

                //textView_log_in.setText("多云");
                //Toast.makeText(getApplicationContext(),wea,Toast.LENGTH_SHORT).show();
            }
            case "大部多云": {
                return R.drawable.weather7;

                //textView_log_in.setText("多云");
                //Toast.makeText(getApplicationContext(),wea,Toast.LENGTH_SHORT).show();
            }
            case "阴": {
                return R.drawable.weather9;

            }
            case "阵雨": {
                return R.drawable.weather14;

            }
            case "雷阵雨": {
                return R.drawable.weather14;

            }
            case "雷阵雨伴有冰雹": {
                return R.drawable.weather14;

            }
            case "小雨": {
                return R.drawable.weather14;

            }
            case "中雨": {
                return R.drawable.weather14;


            }
            case "大雨": {
                return R.drawable.weather14;

            }
            case "冻雨": {
                return R.drawable.weather14;

            }
            case "雨夹雪": {
                return R.drawable.weather14;

            }
            case "暴雨": {
                return R.drawable.weather17;

            }
            case "大暴雨": {
                return R.drawable.weather17;

            }
            case "特大暴雨": {
                return R.drawable.weather17;

            }
            case "阵雪": {
                return R.drawable.weather23;

            }
            case "小雪": {
                return R.drawable.weather23;

            }
            case "中雪": {
                return R.drawable.weather23;

            }
            case "大雪": {
                return R.drawable.weather23;

            }
            case "暴雪": {
                return R.drawable.weather23;

            }
            case "浮尘": {
                return R.drawable.weather27;

            }
            case "扬沙": {
                return R.drawable.weather27;

            }
            case "沙尘暴": {
                return R.drawable.weather27;

            }
            case "强沙尘暴": {
                return R.drawable.weather27;

            }
            case "雾": {
                return R.drawable.weather30;

            }
            case "霾": {
                return R.drawable.weather30;

            }
            case "风": {
                return R.drawable.weather32;

            }
            case "大风": {
                return R.drawable.weather32;

            }
            case "飓风": {
                return R.drawable.weather32;

            }
            case "热带风暴": {
                return R.drawable.weather32;

            }
            case "龙卷风": {
                return R.drawable.weather32;

            }
            case "冷": {
                return R.drawable.weather23;

            }
            case "热": {
                return R.drawable.weather2;

            }
            default: {
                return R.drawable.weather_na;

            }


        }
    }


    public void loadAnswers() {
                    switch (weath){
                        case "晴":{
                            image_weather.setImageResource(R.drawable.weather2);
                            break;
                        }
                        case "多云":{
                            image_weather.setImageResource(R.drawable.weather7);
                            //textView_log_in.setText("多云");
                            //Toast.makeText(getApplicationContext(),wea,Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "晴间多云":{
                            image_weather.setImageResource(R.drawable.weather7);
                            break;
                            //textView_log_in.setText("多云");
                            //Toast.makeText(getApplicationContext(),wea,Toast.LENGTH_SHORT).show();
                        }
                        case "大部多云":{
                            image_weather.setImageResource(R.drawable.weather7);
                            break;
                            //textView_log_in.setText("多云");
                            //Toast.makeText(getApplicationContext(),wea,Toast.LENGTH_SHORT).show();
                        }
                        case "阴":{
                            image_weather.setImageResource(R.drawable.weather9);
                            break;
                        }
                        case "阵雨":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;
                        }
                        case "雷阵雨":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;
                        }
                        case "雷阵雨伴有冰雹":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;
                        }
                        case "小雨":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;
                        }
                        case "中雨":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;

                        }
                        case "大雨":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;
                        }
                        case "冻雨":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;
                        }
                        case "雨夹雪":{
                            image_weather.setImageResource(R.drawable.weather14);
                            break;
                        }
                        case "暴雨":{
                            image_weather.setImageResource(R.drawable.weather17);
                            break;
                        }
                        case "大暴雨":{
                            image_weather.setImageResource(R.drawable.weather17);
                            break;
                        }
                        case "特大暴雨":{
                            image_weather.setImageResource(R.drawable.weather17);
                            break;
                        }
                        case "阵雪":{
                            image_weather.setImageResource(R.drawable.weather23);
                            break;
                        }
                        case "小雪":{
                            image_weather.setImageResource(R.drawable.weather23);
                            break;
                        }
                        case "中雪":{
                            image_weather.setImageResource(R.drawable.weather23);
                            break;
                        }
                        case "大雪":{
                            image_weather.setImageResource(R.drawable.weather23);
                            break;
                        }
                        case "暴雪":{
                            image_weather.setImageResource(R.drawable.weather23);
                            break;
                        }
                        case "浮尘":{
                            image_weather.setImageResource(R.drawable.weather27);
                            break;
                        }
                        case "扬沙":{
                            image_weather.setImageResource(R.drawable.weather27);
                            break;
                        }
                        case "沙尘暴":{
                            image_weather.setImageResource(R.drawable.weather27);
                            break;
                        }
                        case "强沙尘暴":{
                            image_weather.setImageResource(R.drawable.weather27);
                            break;
                        }
                        case "雾":{
                            image_weather.setImageResource(R.drawable.weather30);
                            break;
                        }
                        case "霾":{
                            image_weather.setImageResource(R.drawable.weather30);
                            break;
                        }
                        case "风":{
                            image_weather.setImageResource(R.drawable.weather32);
                            break;
                        }
                        case "大风":{
                            image_weather.setImageResource(R.drawable.weather32);
                            break;
                        }
                        case "飓风":{
                            image_weather.setImageResource(R.drawable.weather32);
                            break;
                        }
                        case "热带风暴":{
                            image_weather.setImageResource(R.drawable.weather32);
                            break;
                        }
                        case "龙卷风":{
                            image_weather.setImageResource(R.drawable.weather32);
                            break;
                        }
                        case "冷":{
                            image_weather.setImageResource(R.drawable.weather23);
                            break;
                        }
                        case "热":{
                            image_weather.setImageResource(R.drawable.weather2);
                            break;
                        }
                        default: {
                            image_weather.setImageResource(R.drawable.weather_na);
                            break;
                        }


                    }



    }
}
