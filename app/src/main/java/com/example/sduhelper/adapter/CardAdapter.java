package com.example.sduhelper.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sduhelper.R;

import java.util.List;

/**
 * Created by 顾文涛 on 2017/2/18.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<CardItem> cardItems;

    public CardAdapter(List<CardItem> cardItems){
        this.cardItems=cardItems;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imageView_account);
            textView= (TextView) itemView.findViewById(R.id.textView5);

        }


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;



    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardItem item=cardItems.get(position);
        holder.imageView.setImageResource(item.getImgId());
        holder.textView.setText(item.getName());


    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }
}
