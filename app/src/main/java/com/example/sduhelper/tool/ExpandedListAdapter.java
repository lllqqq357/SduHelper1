package com.example.sduhelper.tool;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.sduhelper.R;

/**
 * Created by 顾文涛 on 2017/1/17.
 */

public class ExpandedListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private String position;
    private String[] school=new String[5];
    private String item;
    private Context _context;



    public ExpandedListAdapter(Context context,String[] school,String position,String item) {
        this._context = context;
        this.position=position;
        this.item=item;

        this.school=school;

    }


    //private String[] position=new String[]{"出发地","目的地"};
    //private String[] item=new String[]{"请选择你的起点校区","请选择你的终点校区"};
    //private String[][] school=new String[][]{
     //       {"中心校区","兴隆山校区","洪家楼校区","千佛山校区","趵突泉校区"},
      //      {"中心校区","兴隆山校区","洪家楼校区","千佛山校区","趵突泉校区"}

    //};


    @Override
    public int getGroupCount() {
        return 1;
    }



    @Override
    public Object getGroup(int groupPosition) {


        return position;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return school[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_parent, null);
        }

        TextView textView= (TextView) convertView.findViewById(R.id.textView);
        textView.setText(position);
        TextView textView2= (TextView) convertView.findViewById(R.id.textView3);
        textView2.setText(item);




        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child, null);
        }

        TextView textView= (TextView) convertView.findViewById(R.id.textView4);
        textView.setText(school[childPosition]);





        return convertView;


    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 5;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {



        return true;
    }


}
