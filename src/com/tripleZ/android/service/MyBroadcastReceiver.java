package com.tripleZ.android.service;

import com.tripleZ.android.BaseApplication;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {
	boolean isServiceRunning = false;

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("=======进入了广播接收器");
		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
			// 检查Service状态
			ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
				if ("com.tripleZ.android.service.VibrateService".equals(service.service.getClassName())) {
					System.out.println("======------我的服务正在启动，不重启");
					isServiceRunning = true;
				}
			}
			if (!isServiceRunning) {
				System.out.println("======重启了服务");
				Intent i = new Intent(context, VibrateService.class);
				context.startService(i);
			}
		}
	}

}
