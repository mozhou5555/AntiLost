package com.tripleZ.android;

import java.io.File;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;

public class BaseApplication extends Application{
	private static BaseApplication instance;
	
	@Override
	public void onCreate() {
		super.onCreate();
//		System.out.println("=======注册广播");
//		IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);   
//	    MyBroadcastReceiver receiver = new MyBroadcastReceiver();   
//	    registerReceiver(receiver, filter);   
		File destDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/antilost/");
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
	}
	
	public static BaseApplication getInstance() {
        return instance;
}
	
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
}
