package com.tripleZ.android.act;

import com.tripleZ.android.R;

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
		initLogic();
		
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
	
	private void initLogic(){
		tpStartTime.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				
			}
		});
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
