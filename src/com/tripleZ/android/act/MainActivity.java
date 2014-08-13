package com.tripleZ.android.act;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.tripleZ.android.R;
import com.tripleZ.android.util.DateUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private ProgressBar pb_forgetful;
	private TextView tv_runtime;
	private TextView tv_graduate_time;
	private Date run_time;
	private Date graduate_time;
	private static final int UPDATE_TIME = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initWidget();
        initLogic();
    }
    
    private void initWidget(){
    	pb_forgetful = (ProgressBar) findViewById(R.id.main_pb_forgetfulbar);
    	tv_runtime = (TextView) findViewById(R.id.main_tv_runtime);
    	tv_graduate_time = (TextView) findViewById(R.id.main_tv_graduate_time);
    }
    
    private void initLogic(){
    	pb_forgetful.setProgress(50);//随意设定一个进度条的值
    	run_time = new Date();//应当获取保存数据，还没有，暂时为当前时间
    	graduate_time = new Date();//应当获取保存数据，还没有，暂时为当前时间
    	Timer timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
    private class RemindTask extends TimerTask {
        public void run() {
        	myHandler.sendEmptyMessage(UPDATE_TIME);
        }
    }
    
    private String plusTime(){
    	run_time = DateUtil.addOneSecond(run_time);
    	String time = DateUtil.getSecondOnlyStr(run_time);
		return time;
    }
    
    private String minusTime(){
    	graduate_time = DateUtil.minusOneSecond(graduate_time);
    	String time = DateUtil.getSecondOnlyStr(graduate_time);
		return time;
    }
    
    protected Handler myHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TIME:
				tv_runtime.setText(plusTime());
	        	tv_graduate_time.setText(minusTime());
				break;

			default:
				break;
			}
		}
	};
}
