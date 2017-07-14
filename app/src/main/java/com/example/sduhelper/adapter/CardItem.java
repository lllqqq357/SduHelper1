package com.example.sduhelper.adapter;

/**
 * Created by 顾文涛 on 2017/2/18.
 */

public class CardItem {
    private int imgId;
    private String item;
    public CardItem(String item,int imgId){
        this.imgId=imgId;
        this.item=item;
    }
    public String getName(){
        return item;
    }
    public int getImgId(){
        return imgId;
    }
}
