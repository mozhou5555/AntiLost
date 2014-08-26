package com.zzz.android.act;

import java.util.Date;
import java.util.List;

import com.zzz.android.R;
import com.zzz.android.util.DateUtil;
import com.zzz.android.util.LogUtil;

import android.app.ActivityManager;
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
	private boolean isRunning;
	
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
	
	private void initLogic(){
        
		btnTp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), TimeSetActivity.class);
//				startActivity(intent);
				if(!isRunning){
					
				}else{
					btnTp.setText("未启动-点击开始检测运动");
					Toast.makeText(getActivity(), "运动结束", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
}
