package com.example.sduhelper.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.sduhelper.Network.SchoolBus.Bus;
import com.example.sduhelper.R;

import java.util.LinkedList;

/**
 * Created by 本人 on 2017/7/13.
 */

public class BusAdapter extends BaseAdapter {
    private LinkedList<Bus> mDate;
    private Context mContext;

    public BusAdapter(LinkedList<Bus>mDate, Context mContext){
        this.mDate=mDate;
        this.mContext=mContext;
    }

    public int getCount(){
        return mDate.size();
    }

    public Object getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView= LayoutInflater.from(mContext).inflate(R.layout.bus_accept,parent,false);
        TextView tv_start=(TextView)convertView.findViewById(R.id.start1);
        TextView tv_end=(TextView)convertView.findViewById(R.id.end1);
        TextView tv_time=(TextView)convertView.findViewById(R.id.time1);

        tv_start.setText(mDate.get(position).getS());
        tv_end.setText(mDate.get(position).getE());
        tv_time.setText(mDate.get(position).getT());

        return convertView;
    }

}
