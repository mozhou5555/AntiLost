package com.tripleZ.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 闹钟接收器
 * @author Jing
 *
 */
public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("=====触发了闹钟接收器");
//		Intent service = new Intent(context, VibrateService.class);  
//        context.startService(service);  
	}

}
