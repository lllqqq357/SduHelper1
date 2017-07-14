package com.example.sduhelper.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sduhelper.R;
import com.example.sduhelper.timetable.Tables;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

/**
 * Created by 顾文涛 on 2017/2/20.
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {
    List<Tables> classes;
    Context context;

    public LessonAdapter(Context context, List<Tables> classes){
        this.classes=classes;
        this.context=context;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
       TextView lesson;
        TextView location;
        CardView cardView;
        LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            lesson= (TextView) itemView.findViewById(R.id.lesson);
            location= (TextView) itemView.findViewById(R.id.classrom);
            cardView= (CardView) itemView.findViewById(R.id.card_lesson);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.LL);


        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lesson_item,parent,false);
        LessonAdapter.ViewHolder holder=new LessonAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tables t= classes.get(position);
        if (t.getDay()==0){
            holder.cardView.setVisibility(View.GONE);
        }
        else {
            holder.linearLayout.setBackgroundColor(getRandomColor());
            if (t.getName().length() > 9) {
                holder.lesson.setText(t.getName().substring(0, 8));
            } else {
                holder.lesson.setText(t.getName());
            }

            if (t.getLocation().startsWith("兴")) {
                holder.location.setText(t.getLocation().substring(3));
            } else {
                holder.location.setText(t.getLocation());
            }

        }

    }


    @Override
    public int getItemCount() {
        return classes.size();
    }

    public int getRandomColor(){
        int i= Math.abs(new Random().nextInt())%7;
        int[] color={
                context.getResources().getColor(R.color.C1),
                context.getResources().getColor(R.color.C2),
                context.getResources().getColor(R.color.C3),
                context.getResources().getColor(R.color.C4),
                context.getResources().getColor(R.color.C5),
                context.getResources().getColor(R.color.C6),
                context.getResources().getColor(R.color.C7),


        };

        return color[i];
    }
}
