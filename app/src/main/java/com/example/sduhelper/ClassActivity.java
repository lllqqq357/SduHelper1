package com.example.sduhelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sduhelper.adapter.LessonAdapter;
import com.example.sduhelper.cookie.CookieJarImpl;
import com.example.sduhelper.cookie.PersistentCookieStore;
import com.example.sduhelper.timetable.Tables;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClassActivity extends BaseActivity {
    private OkHttpClient.Builder builder;
    private OkHttpClient okHttpClient;
    private PersistentCookieStore persistentCookieStore;

    private SharedPreferences sharedPreferences;


    String session;
    String body;

    List<Tables> classes=new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ClassTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("课程表");

        sharedPreferences=getSharedPreferences("account",MODE_PRIVATE);

        builder = new OkHttpClient.Builder();
        persistentCookieStore = new PersistentCookieStore(getApplicationContext());
        CookieJarImpl cookieJarImpl = new CookieJarImpl(persistentCookieStore);
        builder.cookieJar(cookieJarImpl);
        okHttpClient = builder.build();
        getLogin();



        recyclerView= (RecyclerView) findViewById(R.id.re_class);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),7));
        recyclerView.setHasFixedSize(true);





    }











    public void parseBody(String body){
        for(int j=0;j<35;j++){
            Tables tables=new Tables("test","test",0,0);
            classes.add(tables);
        }
        Document doc= Jsoup.parse(body);
        Element table=doc.getElementById("ysjddDataTableId");
        Elements details=table.getElementsByTag("tr");
        int n=details.size();
        Log.v("class num",""+n);
        for(int i=1;i<n;i++){
           String lesson=details.get(i).child(2).text();
            String location=details.get(i).child(11).text();
            int day=Integer.parseInt(details.get(i).child(9).text());
            int index=Integer.parseInt(details.get(i).child(10).text());



            Tables tables=new Tables(lesson,location,day,index);
            classes.set((index-1)*7+day-1,tables);

        }




    }




    public void getLogin(){
         login().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);


    }
    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.w("RX", "onCompleted");
            getTimeable();
        }

        @Override
        public void onError(Throwable e) {
            Log.i("RX","error");
        }

        @Override
        public void onNext(String s) {
            Log.w("RX","next");
            Log.w("RX","display--"+Thread.currentThread().getName());
            session=s;
            return;
        }
    };

    public Observable<String> login(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    RequestBody formBody = new FormBody.Builder()
                            .add("j_username", sharedPreferences.getString("account",""))
                            .add("j_password",sharedPreferences.getString("password",""))
                            .build();
                    Request request = new Request.Builder()
                            .url("http://bkjws.sdu.edu.cn/b/ajaxLogin")
                            .header("User-Agent", "Mozilla/5.0 (Linux; Android 1.6; Nexus 6P Build/NMF26F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36")
                            .post(formBody)
                            .build();

                    Response response=okHttpClient.newCall(request).execute();
                    String s=response.header("Set-Cookie");
                    subscriber.onNext(s);
                    subscriber.onCompleted();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).observeOn(Schedulers.io());
    }












    public void getTimeable(){
        timeable(session).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber1);
    }

    Subscriber<String> subscriber1 = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.w("RX", "onCompleted");
            parseBody(body);


            LessonAdapter adapter=new LessonAdapter(getApplicationContext(),classes);
            recyclerView.setAdapter(adapter);


        }

        @Override
        public void onError(Throwable e) {
            Log.i("RX","error");
        }

        @Override
        public void onNext(String s) {
            Log.w("RX","next");
            Log.w("RX","display--"+Thread.currentThread().getName());
            body=s;
            return;
        }
    };


    public Observable<String> timeable(final String s){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {

                    Request request = new Request.Builder()
                            .url("http://bkjws.sdu.edu.cn/f/xk/xs/bxqkb")
                            .header("Cookie",s)
                            .header("User-Agent", "Mozilla/5.0 (Linux; Android 7.1.1; Nexus 6P Build/NMF26F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36")
                            .build();

                    Response response=okHttpClient.newCall(request).execute();
                    String s=response.body().string();
                    subscriber.onNext(s);
                    subscriber.onCompleted();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).observeOn(Schedulers.io());
    }

}
