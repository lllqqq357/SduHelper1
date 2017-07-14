package com.example.sduhelper.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 顾文涛 on 2017/1/18.
 */

public class LocalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceive",
                Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
