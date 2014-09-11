package com.zzz.android;

import java.io.File;

import com.zzz.android.service.VibrateService;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Environment;
import android.os.SystemClock;

public class BaseApplication extends Application{
	private static BaseApplication instance;
	
	@Override
	public void onCreate() {
		super.onCreate();  
		instance = this;
//		System.out.println("=======注册广播");
		File destDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/antilost/");
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		startPollingService();
	}
	
	public static BaseApplication getInstance() {
        return instance;
	}
	
	//开启轮询服务  
    public static void startPollingService() {  
    	System.out.println("=====开启了轮询服务!");
        //获取AlarmManager系统服务  
        AlarmManager manager = (AlarmManager) getInstance().getSystemService(ALARM_SERVICE);
          
        //包装需要执行Service的Intent  
        Intent intent = new Intent(getInstance(), VibrateService.class);  
        PendingIntent pendingIntent = PendingIntent.getService(getInstance(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);  
          
        //触发服务的起始时间  
        long triggerAtTime = SystemClock.elapsedRealtime();  
          
        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service  
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, 60 * 1000, pendingIntent);
    }
    
    //停止轮询服务  
    public static void stopPollingService() {
    	System.out.println("=====结束了轮询服务");
        AlarmManager manager = (AlarmManager) getInstance().getSystemService(getInstance().ALARM_SERVICE);  
        Intent intent = new Intent(getInstance(), VibrateService.class);  
        PendingIntent pendingIntent = PendingIntent.getService(getInstance(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);  
        //取消正在执行的服务  
        manager.cancel(pendingIntent);  
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
