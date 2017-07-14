package com.example.sduhelper;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.sduhelper.Network.SchoolBus.Bus;
import com.example.sduhelper.Network.WeatherService;
import com.example.sduhelper.tool.AnimatedExpandableListView;
import com.example.sduhelper.tool.ExpandedListAdapter;
import com.example.sduhelper.tool.LocalReceiver;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusActivity extends BaseActivity {




    private String position1="出发地";
    private String position2="目的地";
    private String item1="请选择你的起点校区";
    private String item2="请选择你的起点校区";


    public static String start ;
    public static String end;
    int isWeekend = 99;

    private String[] school=new String[]
           {"中心校区","兴隆山校区","洪家楼校区","千佛山校区","趵突泉校区"};

    AnimatedExpandableListView expandableListView1;
    ExpandableListAdapter expandableListAdapter1;
    AnimatedExpandableListView expandableListView2;
    ExpandableListAdapter expandableListAdapter2;

    ExpandableListAdapter expandableListAdapter3;

    TextView textTitle;
    TextView textItem;

    Button button;
    LinearLayout linearLayout;
    ToggleButton button_day;
    ToggleButton button_end;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.BusTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("校车查询");

        View converView= LayoutInflater.from(this).inflate(R.layout.list_parent,null);

        textTitle= (TextView) converView.findViewById(R.id.textView);
        textItem= (TextView) converView.findViewById(R.id.textView3);
        button= (Button) findViewById(R.id.bus_btn);
        button_day= (ToggleButton) findViewById(R.id.btn_weekday);
        button_end= (ToggleButton) findViewById(R.id.btn_weekend);
        linearLayout= (LinearLayout) findViewById(R.id.container);

        final int yellow=getResources().getColor(R.color.bus_color_bar);
        final int normal=getResources().getColor(R.color.normal);


        button_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(linearLayout,new Recolor());
                //button_day.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //button_day.setTextColor(getResources().getColor(R.color.white));
                //button_day.setVisibility(View.GONE);
                button_day.setBackgroundDrawable(new ColorDrawable(yellow));
                button_day.setChecked(true);

                if(button_end.isChecked()){

                    button_end.setBackgroundDrawable(new ColorDrawable(normal));
                }

                isWeekend=0;



            }
        });

        button_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(linearLayout,new Recolor());

                button_end.setBackgroundDrawable(new ColorDrawable(yellow));
                button_end.setChecked(true);

                if(button_day.isChecked()){

                    button_day.setBackgroundDrawable(new ColorDrawable(normal));
                }
                isWeekend=1;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (start==null || end==null || isWeekend==99 || start.equals(end)){

                    Toast.makeText(getApplicationContext(),"亲，你有什么东西忘记选了",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent=new Intent(BusActivity.this,Bus2Activity.class);
                    intent.putExtra("start",start);
                    intent.putExtra("end",end);
                    intent.putExtra("isWeekend",isWeekend);
                    Toast.makeText(getApplicationContext(),start+" "+end+" "+isWeekend,Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }


            }
        });


        expandableListView1= (AnimatedExpandableListView) findViewById(R.id.expand_list);
        expandableListAdapter1=new ExpandedListAdapter(this,school,position1,item1);


        expandableListView2= (AnimatedExpandableListView) findViewById(R.id.expand_list2);
        expandableListAdapter2=new ExpandedListAdapter(this,school,position2,item2);


        expandableListView1.setDividerHeight(0);
        expandableListView1.setGroupIndicator(null);
        expandableListView1.setAdapter(expandableListAdapter1);
        expandableListView1.expandGroup(0);


        expandableListView2.setDividerHeight(0);
        expandableListView2.setGroupIndicator(null);
        expandableListView2.setAdapter(expandableListAdapter2);
        expandableListView2.expandGroup(0);

        expandableListView1.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (expandableListView1.isGroupExpanded(groupPosition)) {
                    expandableListView1.collapseGroupWithAnimation(groupPosition);
                } else {
                    expandableListView1.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });

        expandableListView2.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (expandableListView2.isGroupExpanded(groupPosition)) {
                    expandableListView2.collapseGroupWithAnimation(groupPosition);
                } else {
                    expandableListView2.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });



        expandableListView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                start = school[childPosition];

                expandableListAdapter3=new ExpandedListAdapter(BusActivity.this,school,position1+":"+school[childPosition],"点击更改");

                expandableListView1.collapseGroupWithAnimation(0);


                expandableListView1.setAdapter(expandableListAdapter3);
                //
                return false;
            }
        });

        expandableListView2.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                end =school[childPosition];

                expandableListAdapter3=new ExpandedListAdapter(BusActivity.this,school,position2+":"+school[childPosition],"点击更改");

                expandableListView2.collapseGroupWithAnimation(0);
                expandableListView2.setAdapter(expandableListAdapter3);
                return false;
            }
        });



















    }







}
