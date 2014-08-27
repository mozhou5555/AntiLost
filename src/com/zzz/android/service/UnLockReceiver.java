package com.zzz.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 解锁屏幕广播
 * @author Jing
 *
 */
public class UnLockReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("=====进入了解锁事件");
		Intent service = new Intent(context, VibrateService.class);  
        context.startService(service);  
	}

}
