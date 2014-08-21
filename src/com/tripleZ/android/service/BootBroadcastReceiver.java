package com.tripleZ.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * 开机自动启动的广播接收器
 * @author Jing
 *
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//后边的XXX.class就是要启动的服务  
        Intent service = new Intent(context, VibrateService.class);  
        context.startService(service);  
        Log.v("TAG", "开机自动服务自动启动....阿斯达斯的.");  
	}
	
}
