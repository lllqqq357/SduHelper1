package com.example.sduhelper;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sduhelper.Network.SchoolBus.Bus;

import com.example.sduhelper.Network.WeatherService;
import com.example.sduhelper.tool.BusAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadFactory;


import okhttp3.OkHttpClient;


public class Bus2Activity extends BaseActivity {
    private WeatherService weatherService;
    OkHttpClient client = new OkHttpClient();



    private Context mContext;
    private BusAdapter mAdapter = null;
    private ListView list_bus;
    private List<Bus> mDate = null;


    private String start;
    private String end;
    private int isWeekend;

    TextView txt1;

    String body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.BusTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("校车查询");

        Intent intent = getIntent();
        start = intent.getStringExtra("start");
        end = intent.getStringExtra("end");
        isWeekend = intent.getIntExtra("isWeekend", 99);

        mContext = Bus2Activity.this;
        list_bus = (ListView) findViewById(R.id.bus1);
        mDate = new LinkedList<Bus>();

        GetBusTask mAuthtask1=new GetBusTask(start,end,isWeekend);
        mAuthtask1.execute((Void) null);




//        getTimeable();



    }












    //public void getTimeable() {
    //    WeatherService service= ApiUtil.getBusService();
    //   service.getBus(1,start,end,isWeekend).enqueue(new Callback<List<Bus>>() {
    //       @Override
    //       public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
    //           if (response.isSuccessful()){
    //              Log.e("Bus::->",response.body().get(0).getS());
    //          }else
    //              Log.e("Bus::->",response.message());
    //      }

    //      @Override
    //     public void onFailure(Call<List<Bus>> call, Throwable t) {
    //         Log.e("BUS",t.getMessage());
    //     }
    //  });


    //  }

    public class GetBusTask extends AsyncTask<Void, Void, Boolean> {
        private final String mStart;
        private final String mEnd;
        private final int mTime;

        GetBusTask(String start, String end, int time) {
            mStart = start;
            mEnd = end;
            mTime = time;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {
                URL url = new URL("http://202.194.14.215/Api/schoolbus/?act=1&start=" + start + "&end=" + end + "&isWeekend=" + isWeekend);
                HttpURLConnection connection = null;
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


                ListView listView = (ListView) findViewById(R.id.bus1);

                for (JsonElement bus : jsonArray) {
                    Bus bus1 = gson.fromJson(bus, Bus.class);
                    busArrayList.add(bus1);
                    mDate.add(new Bus("起点--->" + bus1.getS(),"终点--->" + bus1.getE(), "发车时间： " + bus1.getT()));
//                        txt1.setText("起点::->" + bus1.getS() + ",终点::->" + bus1.getE() + ",发车时间::" + bus1.getT());

                    Log.d("2333---------->", Thread.currentThread() + "起点::->" + bus1.getS() + ",终点::->" + bus1.getE() + ",发车时间::" + bus1.getT());
                    // System.out.println("起点：" + bus1.getS() + ",终点：" + bus1.getE() + ",发车时间：" + bus1.getT());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mDate == null)
                return false;
            else
                return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAdapter = new BusAdapter((LinkedList<Bus>) mDate, mContext);
            list_bus.setAdapter(mAdapter);

        }



    }
}






