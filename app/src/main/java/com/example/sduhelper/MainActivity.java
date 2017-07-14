package com.example.sduhelper;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sduhelper.Fragment.MainFragment;
import com.example.sduhelper.Fragment.WeatherFragment;
import com.example.sduhelper.Network.Now.Start;

import com.example.sduhelper.Network.Pm.PmTwoPointFive;
import com.example.sduhelper.Network.WeatherService;
import com.example.sduhelper.tool.ActivityCollector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;


@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends BaseActivity {
    //定义一个Fragemnt,用于切换Fragment
    FragmentManager fragmentManager;
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    FrameLayout frameLayout;
    ActionBarDrawerToggle toggle;
    FloatingActionButton fab;

    private WeatherService weatherService;

    ImageView image_weather;
    ImageView image_back;
    TextView textView_log_in;
    View header;

    String weather = "李狗蛋";
    String temperture;

    ArrayList<String> day_one=new ArrayList<String>();
    ArrayList<String> day_two=new ArrayList<String>();
    ArrayList<String> day_three=new ArrayList<String>();

    SharedPreferences.Editor preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getSupportFragmentManager();
        appBarLayout= (AppBarLayout) findViewById(R.id.appbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar.setElevation(0.0f);
        //appBarLayout.set
        //获取一个FrameLayout，用于替换一个fragment

        preferences=getSharedPreferences("account",MODE_PRIVATE).edit();

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);






        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
                //Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                //startActivity(intent);
            }
        });
        //TODO:上面的李狗蛋
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //TODO:侧滑栏的点击事件
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
        //用于显示彩色图标
        //navigationView.setItemIconTintList(null);
        //天气网络请求
        header=navigationView.getHeaderView(0);
        weatherService= ApiUtil.getWeatherService();

        image_weather= (ImageView) header.findViewById(R.id.imageView_wea);
        textView_log_in= (TextView) header.findViewById(R.id.log_in);


        load_image();
        load_next_weather();



        if (savedInstanceState == null) showHome();

    }





    private void load_pm(){

    }

    private void load_next_weather(){
        weatherService.getNextWeather().enqueue(new Callback<com.example.sduhelper.Network.Next.Start>() {
            @Override
            public void onResponse(Call<com.example.sduhelper.Network.Next.Start> call, Response<com.example.sduhelper.Network.Next.Start> response) {
                if(response.isSuccessful()){

                    String text_date1=response.body().getResults().get(0).getDaily().get(0).getDate();
                    String text_day1=response.body().getResults().get(0).getDaily().get(0).getTextDay();
                    String text_night1=response.body().getResults().get(0).getDaily().get(0).getTextNight();
                    String text_low1=response.body().getResults().get(0).getDaily().get(0).getLow();
                    String text_high1=response.body().getResults().get(0).getDaily().get(0).getHigh();
                    day_one.add(text_date1);
                    day_one.add(text_day1);
                    day_one.add(text_night1);
                    day_one.add(text_low1);
                    day_one.add(text_high1);

                    String text_date2=response.body().getResults().get(0).getDaily().get(1).getDate();
                    String text_day2=response.body().getResults().get(0).getDaily().get(1).getTextDay();
                    String text_night2=response.body().getResults().get(0).getDaily().get(1).getTextNight();
                    String text_low2=response.body().getResults().get(0).getDaily().get(1).getLow();
                    String text_high2=response.body().getResults().get(0).getDaily().get(1).getHigh();
                    day_two.add(text_date2);
                    day_two.add(text_day2);
                    day_two.add(text_night2);
                    day_two.add(text_low2);
                    day_two.add(text_high2);

                    String text_date3=response.body().getResults().get(0).getDaily().get(2).getDate();
                    String text_day3=response.body().getResults().get(0).getDaily().get(2).getTextDay();
                    String text_night3=response.body().getResults().get(0).getDaily().get(2).getTextNight();
                    String text_low3=response.body().getResults().get(0).getDaily().get(2).getLow();
                    String text_high3=response.body().getResults().get(0).getDaily().get(2).getHigh();
                    day_three.add(text_date3);
                    day_three.add(text_day3);
                    day_three.add(text_night3);
                    day_three.add(text_low3);
                    day_three.add(text_high3);


                    //Toast.makeText(getApplicationContext(),text_day1+"转"+text_night1,Toast.LENGTH_SHORT).show();


                }

                else {



                }
            }

            @Override
            public void onFailure(Call<com.example.sduhelper.Network.Next.Start> call, Throwable t) {
                Log.w("weather:","error");
            }
        });

    }



    private void load_image(){
        weatherService.getWeather().enqueue(new Callback<Start>() {
            @Override
            public void onResponse(Call<Start> call, Response<Start> response) {

                //Log.e("rrr",wea);
                //String tem=response.body().get(0).getResults().getNow().getTemperature();

                if(response.isSuccessful()) {
                    weather=response.body().getResults().get(0).getNow().getText();
                    temperture=response.body().getResults().get(0).getNow().getTemperature();

                    //Toast.makeText(getApplicationContext(),weather+temperture,Toast.LENGTH_SHORT).show();

                    switch (weather){
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
                        case "未知":{
                            image_weather.setImageResource(R.drawable.weather_na);
                            break;
                        }



                    }

                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    Toast.makeText(getApplicationContext(),statusCode+":同学，没开网吧",Toast.LENGTH_SHORT).show();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<Start> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"同学，我也不知道哪里炸了",Toast.LENGTH_SHORT).show();
            }
        });




    }

    //显示主页面为Web页面
    private void showHome() {
        selectDrawerItem(navigationView.getMenu().getItem(0));
        //drawer.openDrawer(GravityCompat.START);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            preferences.putString("account","1")
                    .putString("password","")
                    .apply();

            Toast.makeText(getApplicationContext(), "注销成功", Toast.LENGTH_SHORT).show();


            return true;
        }else if(id==R.id.action_quit){
            ActivityCollector.finishAll();
            return true;
        }

        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        toggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }


    /**
     * @param menuItem
     * 通过侧边栏的菜单点击，切换不同的Fragment
     *
     */

    private void selectDrawerItem(MenuItem menuItem) {
        boolean specialToolbarBehaviour = false;
        Class fragmentClass;
        //TODO
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:

                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;
            case R.id.nav_gallery:
                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;
            case R.id.nav_slideshow:
                fragmentClass = WeatherFragment.class;
                fab.setVisibility(View.GONE);
                specialToolbarBehaviour = true;

                //getSupportActionBar().hide();
                setTitle("天气");


                break;
            case R.id.nav_manage:
                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;
            case R.id.nav_setting:
                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;
            case R.id.nav_send:
                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;
            case R.id.nav_about:
                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;
            case R.id.nav_share:
                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;

            default:
                //TODO
                fragmentClass = MainFragment.class;
                fab.setVisibility(View.VISIBLE);


                break;
        }

        try {
            //TODO:获取Fragment并实例化，将content_frame的布局替换为Fragment，并设置相应的标题
            Bundle bundle=new Bundle();
            bundle.putString("weather",weather);
            bundle.putString("temperature",temperture);
            bundle.putStringArrayList("day_one",day_one);
            bundle.putStringArrayList("day_two",day_two);
            bundle.putStringArrayList("day_three",day_three);

            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setToolbarElevation(specialToolbarBehaviour);

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setToolbarElevation(boolean specialToolbarBehaviour) {
        if (specialToolbarBehaviour) {
            appBarLayout.setElevation(0.0f);

        } else {
            appBarLayout.setElevation(getResources().getDimension(R.dimen.elevation_toolbar));

        }
    }




}

