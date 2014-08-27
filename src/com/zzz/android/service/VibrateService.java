package com.zzz.android.service;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.zzz.android.BaseApplication;
import com.zzz.android.util.DateUtil;
import com.zzz.android.util.LogUtil;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class VibrateService extends Service{
	private Vibrator vibrator;
	private static final int VIBREATE_ON = 1;
	private static final int VIBREATE_DOWN = 2;
	private LogUtil lu = new LogUtil();
	private SensorManager sm;
	private WakeLock mWakeLock;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v("SERVICE", "=====服务OnCreat======");
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
		if(isServiceRunning(this, "com.zzz.android.service.VibrateService")){
			Log.v("SERVICE", "=====服务OnStart======");
//			if(DateUtil.compareHHmmInString(DateUtil.getMinuteOnlyStr(new Date()), "21:28") == 0){
//				//系统时间到了预定的时间后
				PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
				mWakeLock = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "VibrateService");// CPU保存运行
				if (null != mWakeLock){  
					mWakeLock.acquire();  
	            }
			 
	    		lu.setFilename(DateUtil.getSecondAnotherStr(new Date()) + ".txt");
//				LogUtil.writeLog(getActivity(), lu, "--运动开始--");
				//创建一个SensorManager来获取系统的传感器服务 
		        sm = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE); 
		        //选取加速度感应器 
		        int sensorType = Sensor.TYPE_LINEAR_ACCELERATION; 
				/* 
		         * 最常用的一个方法 注册事件 
		         * 参数1 ：SensorEventListener监听器 
		         * 参数2 ：Sensor 一个服务可能有多个Sensor实现，此处调用getDefaultSensor获取默认的Sensor 
		         * 参数3 ：模式 可选数据变化的刷新频率 
		         * */
		        sm.registerListener(myAccelerometerListener,sm.getDefaultSensor(sensorType),SensorManager.SENSOR_DELAY_NORMAL); 
		}else{
			Log.v("SERVICE", "=====服务正在运行，无需再次启动======");
		}
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
		if(vibrator != null){
			vibrator.cancel();
		}
		sm.unregisterListener(myAccelerometerListener);
		BaseApplication.stopPollingService();
		mWakeLock.release();
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
	
	private SensorEventListener myAccelerometerListener = new SensorEventListener(){ 
        long lastUpdate = 0;
        long lastUpdate2 = 0;
        float x,y,z, lastX = 0,lastY = 0, lastZ = 0;
        float distanceX = 0;
        float distanceY = 0;
        float distanceZ = 0;
        float distance = 0;
        float a = 0;
        //复写onSensorChanged方法 
        public void onSensorChanged(SensorEvent sensorEvent){ 
            if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){ 
                 x = sensorEvent.values[0]; 
                 y = sensorEvent.values[1]; 
                 z = sensorEvent.values[2]; 
//                LogUtil.writeLog(getActivity(), lu,x + "  " + y + "  " + z);
//                System.out.println("===坐标:   "+ x+"   "+y+"   "+z);
                
                long currentTime = System.currentTimeMillis();
                	long diffTime = currentTime - lastUpdate;
                    float rate = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000;
                    distanceX = (float) (distanceX + (Math.abs(x)* ((double)(diffTime) / 1000) * ((double)(diffTime) / 1000)));
                    distanceY = (float) (distanceY + (Math.abs(y)* ((double)(diffTime) / 1000)*((double)(diffTime) / 1000)));
                    distanceZ = (float) (distanceZ + (Math.abs(z)* ((double)(diffTime) / 1000)*((double)(diffTime) / 1000)));
//                    System.out.println("======"+ Math.abs(x)+"   " +Math.abs(y) +"   " + Math.abs(z)+ "    "+((double)(diffTime) / 1000)+"    "+distanceX);
                    a = a + (float) Math.sqrt((double)(Math.abs(x*x) + Math.abs(y*y) + Math.abs(z*z)));
                    distance = (float) (distance + (Math.abs(a)* ((double)(diffTime) / 1000)*((double)(diffTime) / 1000)));
                    lastX = x;
                    lastY = y;
                    lastZ = z;
                    lastUpdate = currentTime;
                    	
                    if(currentTime - lastUpdate2 > 5 * 1000){
                    	System.out.println("=====大致距离:" + distanceX + "   "+ distanceY +"    "+ distanceZ+ "   "+ (distanceX + distanceY + distanceZ));
                    	LogUtil.writeLog(VibrateService.this, lu, "大致距离x:" + distanceX + "  y:" + distanceY + " z:" + distanceZ +"  总计:"+(distanceX + distanceY +distanceZ));
                    	System.out.println("======="+a);
                    	LogUtil.writeLog(VibrateService.this, lu, "整体加速度算的距离:" + a);
                    	if((distanceX + distanceY + distanceZ) > 5 && (distanceX + distanceY + distanceZ) < 1000){
                    		vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    		long [] pattern = {1000,300,1000,300};   // 停止 开启 停止 开启
                    		vibrator.vibrate(pattern, 2); //震动启动， -1表示只有1次 ，2表示无限恶心你
                    	}
                    	lastUpdate2 = currentTime;
                    	distanceX = 0;
                    	distanceY = 0;
                    	distanceZ = 0;
                    }
            } 
        } 
        //复写onAccuracyChanged方法 
        public void onAccuracyChanged(Sensor sensor , int accuracy){ 
        	
        } 
    }; 
    
    /**
     * 用来判断服务是否运行.
     * @param context
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context mContext,String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE); 
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size()>0)) {
            return false;
        }
        for (int i=0; i<serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
