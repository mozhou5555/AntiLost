package com.tripleZ.android.service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.tripleZ.android.util.DateUtil;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;

public class VibrateService extends Service{
	private Vibrator vibrator;
	private Timer timer;
	private static final int VIBREATE_ON = 1;
	private static final int VIBREATE_DOWN = 2;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v("SERVICE", "=====服务OnCreat======");
//		timer = new Timer();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.v("SERVICE", "=====服务OnStart======");
//		if(DateUtil.compareHHmmInString(DateUtil.getMinuteOnlyStr(new Date()), "21:28") == 0){
//			//系统时间到了预定的时间后
////        timer.schedule(new RemindTask(), 0, 1000);
//			vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//    		long [] pattern = {1000,300,1000,300};   // 停止 开启 停止 开启
//    		vibrator.vibrate(pattern, -1); //震动启动， -1表示只有1次 ，2表示无限恶心你
//		}
	}
	
	private class RemindTask extends TimerTask {
        public void run() {
        	System.out.println("=====进入了计时器");
        	myHandler.sendEmptyMessage(VIBREATE_ON);
        }
    }
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.w("SERVICE", "=====服务OnDestory======被杀死");
//		myHandler.sendEmptyMessage(VIBREATE_DOWN);
//		timer.cancel();
		super.onDestroy();
	}
	
	protected Handler myHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case VIBREATE_ON:
				vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
	    		long [] pattern = {1000,300,1000,300};   // 停止 开启 停止 开启
	    		vibrator.vibrate(pattern, -1); //震动启动， -1表示只有1次 ，2表示无限恶心你
				break;
				
			case VIBREATE_DOWN:
	    		vibrator.cancel();
				break;
				
			default:
				break;
			}
		}
	};
}
