package com.example.sduhelper.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sduhelper.BusActivity;
import com.example.sduhelper.CardActivity;
import com.example.sduhelper.ClassActivity;
import com.example.sduhelper.LoginActivity;
import com.example.sduhelper.R;

/**
 * Created by 顾文涛 on 2017/1/17.
 */

public class MainFragment extends Fragment implements View.OnClickListener{
    CardView cardView;
    CardView cardView2;
    CardView cardView3;
    ImageView imageView;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        cardView=(CardView) rootView.findViewById(R.id.card_bus);
        cardView.setOnClickListener(this);
        cardView2=(CardView) rootView.findViewById(R.id.card_class);
        cardView2.setOnClickListener(this);
        cardView3=(CardView) rootView.findViewById(R.id.card_credit);
        cardView3.setOnClickListener(this);

       sharedPreferences=this.getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);





        return rootView;
    }

    @Override
    public void onClick(View v) {
        int cardId=v.getId();
        switch (cardId){
            case R.id.card_bus:
                Intent intent1=new Intent(getContext(), BusActivity.class);
                startActivity(intent1);
                break;
            case R.id.card_class:
                if (sharedPreferences.getString("account","1").startsWith("1")){
                    Intent intent=new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    break;


                }else{
                    Intent intent2=new Intent(getContext(), ClassActivity.class);
                    startActivity(intent2);
                    break;
                }


            case R.id.card_credit:
                Intent intent3=new Intent(getContext(), CardActivity.class);
                startActivity(intent3);
                break;




        }

    }
}
