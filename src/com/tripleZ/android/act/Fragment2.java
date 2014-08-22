package com.tripleZ.android.act;

import java.util.Date;

import com.tripleZ.android.R;
import com.tripleZ.android.util.DateUtil;
import com.tripleZ.android.util.LogUtil;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment2 extends Fragment{
	private View fragment2;
	private Button btnTp;
	private SensorManager sm;
	private boolean isRunning;
	private LogUtil lu = new LogUtil();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragment2 = inflater.inflate(R.layout.fragment2, container, false);
		initWidget();
		initLogic();
        
		return fragment2;
	}
	
	private void initWidget(){
		btnTp = (Button) fragment2.findViewById(R.id.fragment2_btn_tp);
		btnTp.setText("未启动-点击开始检测运动");
	}
	
	private SensorEventListener myAccelerometerListener = new SensorEventListener(){ 
        
        //复写onSensorChanged方法 
        public void onSensorChanged(SensorEvent sensorEvent){ 
            if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){ 
                float x = sensorEvent.values[0]; 
                float y = sensorEvent.values[1]; 
                float z = sensorEvent.values[2]; 
                LogUtil.writeLog(getActivity(), lu,x + "  " + y + "  " + z);
            } 
        } 
        //复写onAccuracyChanged方法 
        public void onAccuracyChanged(Sensor sensor , int accuracy){ 
        	LogUtil.writeLog(getActivity(), lu, "数据的精度发生变化");
        } 
    }; 
	
	private void initLogic(){
        
		btnTp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), TimeSetActivity.class);
//				startActivity(intent);
				if(!isRunning){
					lu.setFilename(DateUtil.getSecondAnotherStr(new Date()) + ".txt");
					LogUtil.writeLog(getActivity(), lu, "--运动开始--");
					//创建一个SensorManager来获取系统的传感器服务 
			        sm = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE); 
			        //选取加速度感应器 
			        int sensorType = Sensor.TYPE_ACCELEROMETER; 
					/* 
			         * 最常用的一个方法 注册事件 
			         * 参数1 ：SensorEventListener监听器 
			         * 参数2 ：Sensor 一个服务可能有多个Sensor实现，此处调用getDefaultSensor获取默认的Sensor 
			         * 参数3 ：模式 可选数据变化的刷新频率 
			         * */
			        sm.registerListener(myAccelerometerListener,sm.getDefaultSensor(sensorType),SensorManager.SENSOR_DELAY_NORMAL); 
			        isRunning = true;
			        btnTp.setText("已启动-点击停止检测运动");
			        Toast.makeText(getActivity(), "运动开始", Toast.LENGTH_SHORT).show();
				}else{
					isRunning = false;
					sm.unregisterListener(myAccelerometerListener);
					LogUtil.writeLog(getActivity(), lu, "--运动结束--");
					btnTp.setText("未启动-点击开始检测运动");
					Toast.makeText(getActivity(), "运动结束", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
