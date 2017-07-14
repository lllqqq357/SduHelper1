package com.example.sduhelper;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sduhelper.adapter.CardAdapter;
import com.example.sduhelper.adapter.CardItem;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends BaseActivity {
    RecyclerView recyclerView;

    List<CardItem> cardItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.CreditTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        setTitle("校园卡");

        initData();

        recyclerView= (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);


        CardAdapter adapter=new CardAdapter(cardItems);
        recyclerView.setAdapter(adapter);




    }

    private void initData(){
        CardItem item1=new CardItem("转账充值",R.drawable.ic_account_inflate);
        cardItems.add(item1);
        //CardItem item2=new CardItem("2",R.drawable.ic_account_inflate);
        //cardItems.add(item2);
        //CardItem item3=new CardItem("3",R.drawable.ic_account_inflate);
        //cardItems.add(item3);

    }


}
