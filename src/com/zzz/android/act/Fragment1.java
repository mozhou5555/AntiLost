package com.zzz.android.act;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.zzz.android.R;
import com.zzz.android.service.VibrateService;
import com.zzz.android.util.DateUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Fragment1 extends Fragment{
	private View fragment1;
	private Button btnSwitch;
	private ProgressBar pbForgetful;
	private TextView tvRuntime;
	private TextView tvGraduateTime;
	private Date runTime;
	private Date graduateTime;
	private static final int UPDATE_TIME = 1;
	private boolean status;	//false:关闭状态   true:开启状态
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragment1 = inflater.inflate(R.layout.fragment1, container, false);
		initWidget();
		initLogic();
		return fragment1;
	}
	
	private void initWidget(){
		btnSwitch = (Button) fragment1.findViewById(R.id.fragment1_btn_switch);
		pbForgetful = (ProgressBar) fragment1.findViewById(R.id.fragment1_pb_forgetfulbar);
    	tvRuntime = (TextView) fragment1.findViewById(R.id.fragment1_tv_runtime);
    	tvGraduateTime = (TextView) fragment1.findViewById(R.id.fragment1_tv_graduate_time);
		
		onBtnClickListener obc = new onBtnClickListener();
		btnSwitch.setOnClickListener(obc);
	}
	
	private void initLogic(){
		runTime = new Date();//应当获取保存数据，还没有，暂时为当前时间
    	graduateTime = new Date();//应当获取保存数据，还没有，暂时为当前时间
    	Timer timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000);
	}
	
	private class RemindTask extends TimerTask {
        public void run() {
        	myHandler.sendEmptyMessage(UPDATE_TIME);
        }
    }
    
	private class onBtnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.fragment1_btn_switch:
				if(status){	//关闭服务
					btnSwitch.setText(getResources().getString(R.string.switch1));
					status = false;
					Intent intent = new Intent(getActivity(), VibrateService.class);
					getActivity().stopService(intent);
				}else{	//启动服务
					btnSwitch.setText(getResources().getString(R.string.switch2));
					status = true;
					Intent intent = new Intent(getActivity(), VibrateService.class);
					getActivity().startService(intent);
					
				}
				break;

			default:
				break;
			}
		}
	}
	
	protected Handler myHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TIME:
				tvRuntime.setText(plusTime());
	        	tvGraduateTime.setText(minusTime());
				break;
				
			default:
				break;
			}
		}
	};
	
	private String plusTime(){
    	runTime = DateUtil.addOneSecond(runTime);
    	String time = DateUtil.getSecondOnlyStr(runTime);
		return time;
    }
    
    private String minusTime(){
    	graduateTime = DateUtil.minusOneSecond(graduateTime);
    	String time = DateUtil.getSecondOnlyStr(graduateTime);
		return time;
    }
}
