package com.tripleZ.android.act;

import java.util.Calendar;

import com.tripleZ.android.R;
import com.tripleZ.android.service.AlarmReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimeSetActivity extends ActionBarActivity{
	private TimePicker tpStartTime;
	private TimePicker tpEndTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeset);
		initWidget();
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(getResources().getString(R.string.set_time));
	}
	
	private void initWidget(){
		tpStartTime = (TimePicker) findViewById(R.id.timeset_tp_starttime);
		tpEndTime = (TimePicker) findViewById(R.id.timeset_tp_endtime);
		tpStartTime.setIs24HourView(true);
		tpEndTime.setIs24HourView(true);
	}
	
	/**
	 * 设置时间功能
	 */
	private void setTime(){
		Calendar c = Calendar.getInstance();//获取日期对象    
        c.setTimeInMillis(System.currentTimeMillis());        //设置Calendar对象
        c.set(Calendar.HOUR, tpStartTime.getCurrentHour());        //设置闹钟小时数
        c.set(Calendar.MINUTE, tpStartTime.getCurrentMinute());            //设置闹钟的分钟数
        c.set(Calendar.SECOND, 0);                //设置闹钟的秒数
        c.set(Calendar.MILLISECOND, 0);            //设置闹钟的毫秒数
        
//		AlarmManager am =  (AlarmManager) getSystemService(this.ALARM_SERVICE); 
//		Intent intent = new Intent(TimeSetActivity.this, AlarmReceiver.class);
//		PendingIntent pi = PendingIntent.getBroadcast(TimeSetActivity.this, 0, intent, 0);
		System.out.println("======当前时间:"+System.currentTimeMillis()+"   设置时间:"+c.getTimeInMillis());
//		am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), (24* 60 *60 *1000),pi); //重复闹钟，周期1天
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//初始化actionbar
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu2, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
